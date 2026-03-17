$projectRoot = Split-Path -Parent $PSScriptRoot
$backupDirectory = Join-Path $projectRoot "db-backup"
$backupFile = Join-Path $backupDirectory "kanboard-baseline.sql"

if (-Not (Test-Path $backupDirectory)) {
    New-Item -ItemType Directory -Path $backupDirectory | Out-Null
}

Write-Host "Creating baseline database dump..."

docker exec kanboard-db mariadb-dump -ukanboard -pkanboard-secret kanboard > $backupFile

if ($LASTEXITCODE -ne 0) {
    Write-Host "Baseline dump creation failed."
    exit 1
}

Write-Host "Baseline dump created successfully: $backupFile"