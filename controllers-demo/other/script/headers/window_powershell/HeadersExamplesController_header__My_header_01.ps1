$My_Headers = @{
    'My_Header' = 'valore My Header'; 'Accept' = 'application/json'; 'X-My-Header' = 'Hello World';
}
Invoke-WebRequest -Uri ' http://localhost:8080/headers/header__My_header' -Headers $My_Headers -Method Get