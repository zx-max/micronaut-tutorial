$My_Headers = @{
    'Content_Type' = 'valore My Header'; 'My_Header' = 'application/json'; 'X-My-Header' = 'Hello World';
}
Invoke-WebRequest -Uri ' http://localhost:8080/headers/header__Content_Type' -Headers $My_Headers -Method Get