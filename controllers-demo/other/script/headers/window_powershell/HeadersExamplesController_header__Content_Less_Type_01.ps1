# power shell remove header Content-Type in Headers_Content_Less_Type
$Headers_Content_Less_Type = @{
    'Content-Type' = 'application/json'; 'My_Header' = 'My_Header'; 'X-My-Header' = 'Hello World';
}
Invoke-WebRequest -Uri ' http://localhost:8080/headers/header__Content_Less_Type' -Headers $Headers_Content_Less_Type -Method Get -ContentType 'application/json'