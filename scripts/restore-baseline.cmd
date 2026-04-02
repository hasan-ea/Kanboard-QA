@echo off
setlocal EnableDelayedExpansion

set "PROJECT_ROOT=%~dp0.."
set "BACKUP_FILE=%PROJECT_ROOT%\db-backup\kanboard-baseline.sql"

if not exist "%BACKUP_FILE%" (
  echo Baseline SQL file not found: %BACKUP_FILE%
  exit /b 1
)

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

echo Copying baseline file into DB container...
docker cp "%BACKUP_FILE%" %DB_CONTAINER%:/tmp/kanboard-baseline.sql
if errorlevel 1 (
  echo Failed to copy baseline file into DB container.
  exit /b 1
)

echo Restoring baseline database...
docker compose exec -T db sh -lc "mariadb -uroot -proot-secret kanboard < /tmp/kanboard-baseline.sql"
if errorlevel 1 (
  echo Baseline restore failed.
  docker compose exec -T db sh -lc "rm -f /tmp/kanboard-baseline.sql" >nul 2>&1
  exit /b 1
)

docker compose exec -T db sh -lc "rm -f /tmp/kanboard-baseline.sql" >nul 2>&1

echo Baseline restore completed successfully.
exit /b 0