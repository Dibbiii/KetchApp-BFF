@echo off
setlocal

echo Building Docker image from Dockerfile...
docker build -t ketchapp-bff-api .

echo Stopping and removing existing Docker containers...
docker compose down --rmi all -v --remove-orphans

echo Starting Docker Compose...
docker compose up --build

endlocal
