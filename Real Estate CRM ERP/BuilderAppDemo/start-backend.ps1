# ============================================================================
# Starts the Real Estate CRM/ERP backend API on http://localhost:8081
# Database / JWT / CORS / mail settings now live in the .env file next to
# this script (gitignored). Edit .env, not this file.
# ============================================================================
Set-Location $PSScriptRoot

$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-25.0.3.9-hotspot"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

# Port 8081 because XAMPP/Apache occupies 8080 on this machine.
$env:SERVER_PORT = "8081"

$jar = Join-Path $PSScriptRoot "target\BuilderAppDemo-2.0.0.jar"
if (-not (Test-Path $jar)) { & "$PSScriptRoot\mvnw.cmd" -B -DskipTests package }

Write-Host "Starting backend on http://localhost:$($env:SERVER_PORT) ..." -ForegroundColor Cyan
& "$env:JAVA_HOME\bin\java.exe" -jar $jar
