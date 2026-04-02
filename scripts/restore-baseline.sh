#!/usr/bin/env bash
set -euo pipefail

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
COMPOSE_FILE="$PROJECT_ROOT/docker-compose.yml"
BACKUP_FILE="$PROJECT_ROOT/db-backup/kanboard-baseline.sql"

if [[ ! -f "$BACKUP_FILE" ]]; then
  echo "Baseline SQL file not found: $BACKUP_FILE"
  exit 1
fi

echo "Recreating kanboard database..."

docker compose -f "$COMPOSE_FILE" exec -T db \
  sh -lc 'mariadb -h127.0.0.1 -uroot -p"$MARIADB_ROOT_PASSWORD" -e "
    DROP DATABASE IF EXISTS kanboard;
    CREATE DATABASE kanboard CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
  "'

echo "Restoring baseline database from: $BACKUP_FILE"

docker compose -f "$COMPOSE_FILE" exec -T db \
  sh -lc 'mariadb -h127.0.0.1 -uroot -p"$MARIADB_ROOT_PASSWORD" kanboard' < "$BACKUP_FILE"

echo "Baseline restore completed successfully."