#!/usr/bin/env bash
set -euo pipefail

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
COMPOSE_FILE="$PROJECT_ROOT/docker-compose.yml"
BACKUP_DIR="$PROJECT_ROOT/db-backup"
BACKUP_FILE="$BACKUP_DIR/kanboard-baseline.sql"

mkdir -p "$BACKUP_DIR"

echo "Creating baseline database dump..."

docker compose -f "$COMPOSE_FILE" exec -T db \
  sh -lc 'mariadb-dump -uroot -p"$MYSQL_ROOT_PASSWORD" --databases kanboard --add-drop-database --single-transaction --routines --events --triggers' \
  > "$BACKUP_FILE"

echo "Baseline dump created successfully: $BACKUP_FILE"