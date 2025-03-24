@echo off
echo Starting Website and API in separate CMD windows...

:: Open first CMD for the 'website' directory
start cmd /k "cd website && mvnw spring-boot:run"

:: Open second CMD for the 'api' directory
start cmd /k "cd api && mvnw spring-boot:run"

echo Both projects are starting...
exit