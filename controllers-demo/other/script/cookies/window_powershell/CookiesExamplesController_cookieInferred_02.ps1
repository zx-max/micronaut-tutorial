$session = [Microsoft.PowerShell.Commands.WebRequestSession]::new()
$cookie = [System.Net.Cookie]::new('myCookie', 'value')
$session.Cookies.Add('http://localhost:8080', $cookie)
Invoke-RestMethod -Uri 'http://localhost:8080/cookies/cookieInferred' -Body $request_body -WebSession $session -Method Get