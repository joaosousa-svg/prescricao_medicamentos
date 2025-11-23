# Script para iniciar aplicação SEM problema de porta em uso
Write-Host "==================================" -ForegroundColor Cyan
Write-Host "Iniciando Sistema de Prescrições" -ForegroundColor Cyan
Write-Host "==================================" -ForegroundColor Cyan

# 1. Parar TODOS os processos Java
Write-Host "`n[1/4] Parando processos Java..." -ForegroundColor Yellow
Get-Process -Name java -ErrorAction SilentlyContinue | Stop-Process -Force -ErrorAction SilentlyContinue
Start-Sleep -Seconds 2

# 2. Liberar porta 8080 forçadamente
Write-Host "[2/4] Liberando porta 8080..." -ForegroundColor Yellow
$connections = Get-NetTCPConnection -LocalPort 8080 -ErrorAction SilentlyContinue
foreach ($conn in $connections) {
    Stop-Process -Id $conn.OwningProcess -Force -ErrorAction SilentlyContinue
}
Start-Sleep -Seconds 2

# 3. Verificar se porta está livre
Write-Host "[3/4] Verificando porta 8080..." -ForegroundColor Yellow
$portCheck = Get-NetTCPConnection -LocalPort 8080 -ErrorAction SilentlyContinue
if ($portCheck) {
    Write-Host "ERRO: Porta 8080 ainda está em uso!" -ForegroundColor Red
    Write-Host "Processos usando a porta:" -ForegroundColor Red
    netstat -ano | findstr :8080
    exit 1
} else {
    Write-Host "Porta 8080 está LIVRE!" -ForegroundColor Green
}

# 4. Iniciar aplicação
Write-Host "[4/4] Iniciando aplicação Spring Boot..." -ForegroundColor Yellow
Write-Host "`nAguarde... A aplicação estará disponível em:" -ForegroundColor Green
Write-Host "http://localhost:8080/index.html" -ForegroundColor Cyan
Write-Host "`nPressione Ctrl+C para parar a aplicação`n" -ForegroundColor Yellow

mvn spring-boot:run
