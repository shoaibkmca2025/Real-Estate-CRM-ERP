# ============================================================================
# Tests the database connection using the values in .env (same file the
# backend reads). Run from anywhere:
#     powershell -ExecutionPolicy Bypass -File .\test-db-connection.ps1
# ============================================================================
$envFile = Join-Path $PSScriptRoot ".env"
if (-not (Test-Path $envFile)) { Write-Host "ERROR: .env not found next to this script." -ForegroundColor Red; exit 1 }

# --- parse KEY=VALUE lines (ignores comments/blanks) ------------------------
$cfg = @{}
foreach ($line in Get-Content $envFile) {
    $t = $line.Trim()
    if ($t -eq "" -or $t.StartsWith("#")) { continue }
    $i = $t.IndexOf("=")
    if ($i -gt 0) { $cfg[$t.Substring(0, $i).Trim()] = $t.Substring($i + 1).Trim() }
}

$dbHost = $cfg["DB_HOST"]; $port = $cfg["DB_PORT"]; $user = $cfg["DB_USER"]
$pass = $cfg["DB_PASSWORD"]; $db  = $cfg["DB_NAME"]
$ssl  = if ($cfg["DB_SSL"] -eq "true") { "REQUIRED" } else { "PREFERRED" }

Write-Host "Testing $user@${dbHost}:$port / $db (ssl-mode=$ssl) ..." -ForegroundColor Cyan
if ($pass -like "PUT_YOUR_*") { Write-Host "ERROR: set DB_PASSWORD in .env first." -ForegroundColor Red; exit 1 }

$mysql = "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe"
if (-not (Test-Path $mysql)) { $mysql = "mysql" }   # fall back to PATH

$env:MYSQL_PWD = $pass
try {
    $r = & $mysql --host=$dbHost --port=$port --user=$user --ssl-mode=$ssl --connect-timeout=15 -N `
         -e "SELECT CONCAT('server=', VERSION()); SELECT CONCAT('tables=', COUNT(*)) FROM information_schema.tables WHERE table_schema='$db'; SELECT CONCAT('procedures=', COUNT(*)) FROM information_schema.routines WHERE routine_schema='$db'; SELECT CONCAT('users=', COUNT(*)) FROM ``$db``.user;" 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Host "CONNECTION OK" -ForegroundColor Green
        $r | ForEach-Object { Write-Host "  $_" }
    } else {
        Write-Host "CONNECTION FAILED:" -ForegroundColor Red
        $r | ForEach-Object { Write-Host "  $_" }
        exit 1
    }
} finally {
    Remove-Item Env:\MYSQL_PWD -ErrorAction SilentlyContinue
}
