$My_Headers = @{
    'contentType' = 'contentType camel case'; 'Content-Type' = 'contentType con -'; 'X-My-Header' = 'Hello World';
}
Invoke-WebRequest -Uri 'http://localhost:8080/headers/contentTypeInferred' -Headers $My_Headers -Method Get