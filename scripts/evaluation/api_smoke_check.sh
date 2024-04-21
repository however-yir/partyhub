#!/usr/bin/env bash
set -euo pipefail

BASE_URL="${1:-http://localhost:8080}"

echo "[smoke] GET /api/branches"
curl -fsS "${BASE_URL}/api/branches?page=1&size=5" >/dev/null

echo "[smoke] GET /api/users"
curl -fsS "${BASE_URL}/api/users?page=1&size=5" >/dev/null

echo "[smoke] GET /api/messages"
curl -fsS "${BASE_URL}/api/messages?page=1&size=5" >/dev/null

echo "[smoke] GET /api/feedbacks"
curl -fsS "${BASE_URL}/api/feedbacks?page=1&size=5" >/dev/null

echo "[smoke] GET /api/posts"
curl -fsS "${BASE_URL}/api/posts?page=1&size=5" >/dev/null

echo "[smoke] GET /api/stars"
curl -fsS "${BASE_URL}/api/stars?page=1&size=5" >/dev/null

echo "[smoke] GET /api/stars/stats/overview"
curl -fsS "${BASE_URL}/api/stars/stats/overview?year=2024" >/dev/null

echo "[smoke] GET /api/stars/stats/rating-distribution"
curl -fsS "${BASE_URL}/api/stars/stats/rating-distribution?year=2024" >/dev/null

echo "[smoke] all passed"
