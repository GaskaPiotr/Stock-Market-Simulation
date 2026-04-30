@echo off

:: 1. Check if the user actually provided a port number
if "%~1"=="" (
    echo Error: Please provide a port number.
    echo Usage: start.bat ^<PORT^>
    exit /b 1
)

:: 2. Set the port as an environment variable
set APP_PORT=%~1

:: 3. Start the architecture
echo Starting High Availability Cluster on port %APP_PORT%...
docker compose up -d --build