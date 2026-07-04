# ============================================================================
# Start the Spring Boot 3 backend (Real Estate CRM/ERP API) on http://localhost:8080
#
# EDIT the 3 values marked TODO below (at minimum DB_PASSWORD), then run:
#     .\start-backend.ps1
# ============================================================================

# --- 1. JDK 21 (Spring Boot 3.4 supports Java 17-23; you have JDK 26) -------
# TODO: set this to your installed JDK 21 path, or comment it out to use the
#       default 'java' on your PATH.
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-25.0.3.9-hotspot"
if (Test-Path $env:JAVA_HOME) {
    $env:Path = "$env:JAVA_HOME\bin;$env:Path"
    Write-Host "Using JDK at $env:JAVA_HOME" -ForegroundColor Green
} else {
    Write-Host "WARNING: JAVA_HOME '$env:JAVA_HOME' not found - using default 'java' on PATH." -ForegroundColor Yellow
}

# --- 2. Database (MySQL 8) --------------------------------------------------
$env:DB_URL      = "jdbc:mysql://localhost:3306/bcs_angular_uat?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = ""   # root has no password (verified)

# --- 3. Security / CORS ----------------------------------------------------
$env:JWT_SECRET   = "change-this-to-a-long-random-secret-at-least-32-chars"
$env:CORS_ORIGINS = "http://localhost:5173"

# 8080 is used by your XAMPP/Apache (httpd), so run the API on 8081.
$env:SERVER_PORT  = "8081"

# --- Run -------------------------------------------------------------------
Write-Host "Starting backend on http://localhost:8080 ..." -ForegroundColor Cyan
& "$PSScriptRoot\mvnw.cmd" spring-boot:run
