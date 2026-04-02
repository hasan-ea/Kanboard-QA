@echo off
setlocal EnableDelayedExpansion

set "PROJECT_ROOT=%~dp0.."
set "BACKUP_DIR=%PROJECT_ROOT%\db-backup"
set "BACKUP_FILE=%BACKUP_DIR%\kanboard-baseline.sql"

if not exist "%BACKUP_DIR%" mkdir "%BACKUP_DIR%"

for /f "delims=" %%i in ('docker compose ps -q db') do set "DB_CONTAINER=%%i"

if "%DB_CONTAINER%"=="" (
  echo DB container is not running.
  exit /b 1
)

echo Waiting for DB readiness...
set /a COUNT=0
:wait_db
set /a COUNT+=1
docker compose exec -T db sh -lc "mariadb -uroot -proot-secret -e 'SELECT 1;'" >nul 2>&1
if errorlevel 1 (
  if %COUNT% GEQ 30 (
    echo DB did not become ready in time.
    exit /b 1
  )
  timeout /t 2 /nobreak >nul
  goto wait_db
)

echo Creating baseline dump inside DB container...
docker compose exec -T db sh -lc "mariadb-dump -uroot -proot-secret --databases kanboard --add-drop-database --single-transaction --routines --events --triggers > /tmp/kanboard-baseline.sql"
if errorlevel 1 (
  echo Baseline dump creation failed inside container.
  exit /b 1
)

echo Copying baseline dump to host...
docker cp %DB_CONTAINER%:/tmp/kanboard-baseline.sql "%BACKUP_FILE%"
if errorlevel 1 (
  echo Failed to copy baseline dump to host.
  docker compose exec -T db sh -lc "rm -f /tmp/kanboard-baseline.sql" >nul 2>&1
  exit /b 1
)

docker compose exec -T db sh -lc "rm -f /tmp/kanboard-baseline.sql" >nul 2>&1

echo Baseline dump created successfully: %BACKUP_FILE%
exit /b 0