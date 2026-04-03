#!/usr/bin/env bash
set -euo pipefail

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
COMPOSE_FILE="$PROJECT_ROOT/docker-compose.yml"
BACKUP_DIR="$PROJECT_ROOT/db-backup"
BACKUP_FILE="$BACKUP_DIR/kanboard-baseline.sql"

mkdir -p "$BACKUP_DIR"

IGNORE_TABLES=(
  "sessions"
  "remember_me"
  "last_logins"
  "password_reset"
  "user_has_notifications"
  "user_has_unread_notifications"
)

IGNORE_ARGS=""
for table in "${IGNORE_TABLES[@]}"; do
  IGNORE_ARGS+=" --ignore-table=kanboard.${table}"
done

echo "Creating clean baseline database dump..."

docker compose -f "$COMPOSE_FILE" exec -T db \
  sh -lc "mariadb-dump -uroot -p\"\$MARIADB_ROOT_PASSWORD\" \
    --databases kanboard \
    --add-drop-database \
    --single-transaction \
    --routines \
    --events \
    --triggers \
    ${IGNORE_ARGS}" \
  > "$BACKUP_FILE"

sed -i 's/SET AUTOCOMMIT=@OLD_AUTOCOMMIT;/SET AUTOCOMMIT=1;/g' "$BACKUP_FILE"

echo "Baseline dump created successfully: $BACKUP_FILE"