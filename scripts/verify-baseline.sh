#!/usr/bin/env bash
set -euo pipefail

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
COMPOSE_FILE="$PROJECT_ROOT/docker-compose.yml"
BASE_URL="${BASE_URL:-http://localhost:8080}"

echo "Checking app reachability..."
curl -fsS "$BASE_URL/" >/dev/null

echo "Checking seeded users..."
USER_COUNT=$(docker compose -f "$COMPOSE_FILE" exec -T db sh -lc \
  'mariadb -N -h127.0.0.1 -uroot -p"$MARIADB_ROOT_PASSWORD" kanboard -e "SELECT COUNT(*) FROM users WHERE username IN (\"admin\",\"u_manager\",\"u_user\",\"u_pm\",\"u_mem\",\"u_view\",\"u_cust\");"' \
  | tr -d '\r')

if [[ "$USER_COUNT" != "7" ]]; then
  echo "Expected 7 seeded users, got $USER_COUNT"
  exit 1
fi

echo "Checking seeded projects..."
PROJECT_COUNT=$(docker compose -f "$COMPOSE_FILE" exec -T db sh -lc \
  'mariadb -N -h127.0.0.1 -uroot -p"$MARIADB_ROOT_PASSWORD" kanboard -e "SELECT COUNT(*) FROM projects WHERE name IN (\"TP1\",\"PP1\");"' \
  | tr -d '\r')

if [[ "$PROJECT_COUNT" != "2" ]]; then
  echo "Expected 2 seeded projects, got $PROJECT_COUNT"
  exit 1
fi

echo "Environment verification passed."