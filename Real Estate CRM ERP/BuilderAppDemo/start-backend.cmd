@echo off
REM ==========================================================================
REM  Starts the Real Estate CRM/ERP backend API on http://localhost:8081
REM  Usage: run  start-backend.cmd  from this folder, or double-click it.
REM  Note: port 8081 is used because XAMPP/Apache occupies 8080.
REM ==========================================================================
setlocal

set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-25.0.3.9-hotspot"
set "PATH=%JAVA_HOME%\bin;%PATH%"

set "DB_URL=jdbc:mysql://localhost:3306/bcs_angular_uat?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"
set "DB_USERNAME=root"
set "DB_PASSWORD="
set "JWT_SECRET=change-this-to-a-long-random-secret-at-least-32-chars"
set "CORS_ORIGINS=http://localhost:5173"
set "SERVER_PORT=8081"

set "APP_JAR=%~dp0target\BuilderAppDemo-2.0.0.jar"
if not exist "%APP_JAR%" call "%~dp0mvnw.cmd" -B -DskipTests package

echo.
echo Starting backend on http://localhost:%SERVER_PORT% ...
"%JAVA_HOME%\bin\java.exe" -jar "%APP_JAR%"

endlocal
