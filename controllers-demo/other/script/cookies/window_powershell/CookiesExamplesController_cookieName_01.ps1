$session = New-Object Microsoft.PowerShell.Commands.WebRequestSession
$cookie = New-Object System.Net.Cookie
$cookie.Name = "myCookie"
$cookie.Value = "valueOfCookie"
$cookie.Domain = "localhost"
$session.Cookies.Add($cookie);
Invoke-WebRequest -WebSession $session -Uri 'http://localhost:8080/cookies/cookieName'  -Method Get
