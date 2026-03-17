$projectRoot = Split-Path -Parent $PSScriptRoot
$backupFile = Join-Path $projectRoot "db-backup\kanboard-baseline.sql"

if (-Not (Test-Path $backupFile)) {
    Write-Host "Baseline SQL file not found: $backupFile"
    exit 1
}

Write-Host "Restoring baseline database from $backupFile ..."

Get-Content $backupFile | docker exec -i kanboard-db mariadb -ukanboard -pkanboard-secret kanboard

if ($LASTEXITCODE -ne 0) {
    Write-Host "Database restore failed."
    exit 1
}

Write-Host "Baseline restore completed successfully."