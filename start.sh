#!/bin/bash

# 1. Check if the user actually provided a port number
if [ -z "$1" ]; then
  echo "Error: Please provide a port number."
  echo "Usage: ./start.sh <PORT>"
  exit 1
fi

# 2. Set the port as an environment variable
export APP_PORT=$1

# 3. Start the architecture
echo "Starting High Availability Cluster on port $APP_PORT..."
docker compose up -d --build