@echo off
REM ==========================================================================
REM  Starts the Real Estate CRM/ERP backend API on http://localhost:8081
REM  Database / JWT / CORS / mail settings now live in the .env file next to
REM  this script (gitignored). Edit .env, not this file.
REM ==========================================================================
setlocal
cd /d "%~dp0"

set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-25.0.3.9-hotspot"
set "PATH=%JAVA_HOME%\bin;%PATH%"

REM Port 8081 because XAMPP/Apache occupies 8080 on this machine.
set "SERVER_PORT=8081"

set "APP_JAR=%~dp0target\BuilderAppDemo-2.0.0.jar"
if not exist "%APP_JAR%" call "%~dp0mvnw.cmd" -B -DskipTests package

echo.
echo Starting backend on http://localhost:%SERVER_PORT% ...
"%JAVA_HOME%\bin\java.exe" -jar "%APP_JAR%"

endlocal
