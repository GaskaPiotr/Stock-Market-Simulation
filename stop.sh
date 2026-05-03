#!/bin/bash

echo "Stopping cluster and wiping database for a clean slate..."
docker compose down -v --remove-orphans -t 0
echo "System stopped and wiped clean!"