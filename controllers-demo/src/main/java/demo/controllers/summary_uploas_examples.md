# Firme possibili

```Java

@Consumes(MediaType.MULTIPART_FORM_DATA) // <1>
@Post("/upload") // <2>
public HttpResponse upload(StreamingFileUpload file) {
// https://github.com/micronaut-guides/micronaut-file-upload/blob/master/complete/src/main/java/example/micronaut/StreamingFileUploadController.java
}
```

https://www.google.com/search?q=invoke-webrequest+send+form+data
https://stackoverflow.com/questions/36268925/powershell-invoke-restmethod-multipart-form-data

```powershell
$Uri = 'http://localhost:8080/upload/streaming/StreamingFileUpload_1';
$Form = @{
    file = Get-Item -Path 'C:\tmp\test.txt'
}
Invoke-RestMethod -Uri $Uri -Method Post -Form $Form



$FilePath = 'C:\tmp\test.txt';
$URL = 'http://localhost:8080/upload/streaming/StreamingFileUpload_1';
$fileBytes = [System.IO.File]::ReadAllBytes($FilePath);
$fileEnc = [System.Text.Encoding]::GetEncoding('UTF-8').GetString($fileBytes);
$boundary = [System.Guid]::NewGuid().ToString();
$LF = "`r`n";
$bodyLines = (
"--$boundary",
"Content-Disposition: form-data; name=`"file`"; filename=`"temp.txt`"",
"Content-Type: application/octet-stream$LF",
$fileEnc,
"--$boundary--$LF"
) -join $LF
Invoke-RestMethod -Uri $URL -Method Post -ContentType "multipart/form-data; boundary=`"$boundary`"" -Body $bodyLines
```

```shell
curl --form "file=@C:/tmp/test.txt" http://localhost:8080/upload/streaming/StreamingFileUpload_1
curl -F "file=@C:/tmp/test.txt" http://localhost:8080/upload/streaming/StreamingFileUpload_1
```

```java

@Post(value = "/outputStream", consumes = MULTIPART_FORM_DATA, produces = TEXT_PLAIN) // <1>
@SingleResult
public Mono<HttpResponse<String>> uploadOutputStream(StreamingFileUpload file) { // <2>
// https://github.com/micronaut-projects/micronaut-core/blob/v4.6.0/test-suite/src/test/java/io/micronaut/docs/server/upload/UploadController.java
}

@Post(value = "/", consumes = MULTIPART_FORM_DATA, produces = TEXT_PLAIN) // <1>
@SingleResult
public Publisher<HttpResponse<String>> upload(StreamingFileUpload file) { // <2>
// https://github.com/micronaut-projects/micronaut-core/blob/v4.6.0/test-suite/src/test/java/io/micronaut/docs/server/upload/UploadController.java
}


@Consumes(MediaType.MULTIPART_FORM_DATA) // <5>
@Post("/upload") // <6>
public HttpResponse upload(CompletedFileUpload file) { // <7>
// https://github.com/micronaut-guides/micronaut-file-upload/blob/master/complete/src/main/java/example/micronaut/HomeController.java
}

@Post(value = "/completed", consumes = MULTIPART_FORM_DATA, produces = TEXT_PLAIN) // <1>
public HttpResponse<String> uploadCompleted(CompletedFileUpload file) { // <2>
// https://github.com/micronaut-projects/micronaut-core/blob/v4.6.0/test-suite/src/test/java/io/micronaut/docs/server/upload/CompletedUploadController.java
}

@Consumes(MediaType.MULTIPART_FORM_DATA) // <5>
@Post("/CompletedFileUpload") // <6>
public HttpResponse upload(CompletedFileUpload file) throws IOException {
}


@Post(value = "/whole-body", consumes = MULTIPART_FORM_DATA, produces = TEXT_PLAIN) // <1>
@SingleResult
public Publisher<String> uploadBytes(@Body MultipartBody body) { // <2>
// https://github.com/micronaut-projects/micronaut-core/blob/v4.6.0/test-suite/src/test/java/io/micronaut/docs/server/upload/WholeBodyUploadController.java
}

@Post(value = "/bytes", consumes = MULTIPART_FORM_DATA, produces = TEXT_PLAIN) // <1>
public HttpResponse<String> uploadBytes(byte[] file, String fileName) {
// https://github.com/micronaut-projects/micronaut-core/blob/v4.6.0/test-suite/src/test/java/io/micronaut/docs/server/upload/BytesUploadController.java
}

@Post(value = "/base-64", processes = MediaType.APPLICATION_JSON)
public void upload_base_64(@Body UploadBase64Dto uploadBase64Dto) throws IOException {
}

@Post(value = "/inputStream", processes = MediaType.TEXT_PLAIN)
@ExecuteOn(TaskExecutors.IO)
String read(@Body InputStream inputStream) throws IOException {
}
```

# Requests from command line

- https://learn.microsoft.com/en-us/powershell/module/microsoft.powershell.utility/invoke-webrequest?view=powershell-7.4#example-5-submit-a-multipart-form-data-file
- https://gist.github.com/alexlehm/bae63d78bd59c46f4170d27766d2db85
- https://medium.com/nerd-for-tech/micronaut-3-ways-to-upload-files-via-http-ddfa6118ab99

## FileUploadExampleController

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/upload/base-64'  -ContentType "text/plain" -InFile C:\tmp\test.txt -Method Post
Invoke-WebRequest -Uri 'http://localhost:8080/upload/inputStream'  -ContentType "text/plain" -InFile C:\tmp\test.txt -Method Post
curl --form "file=@C:/tmp/test.txt" http://localhost:8080/upload/CompletedFileUpload


NO
Invoke-WebRequest -Uri 'http://localhost:8080/upload/CompletedFileUpload'  -ContentType "multipart/form-data" -InFile C:\tmp\test.txt -Method Post

Prova

curl -F "file=@YOUR_ZIP_FILE.zip" localhost:8080

template
Invoke-RestMethod -Uri $uri -Method Post -InFile $uploadPath -UseDefaultCredentials
```

```powershell
$FilePath = 'c:\document.txt'
$FieldName = 'document'
$ContentType = 'text/plain'

$FileStream = [System.IO.FileStream]::new($filePath, [System.IO.FileMode]::Open)
$FileHeader = [System.Net.Http.Headers.ContentDispositionHeaderValue]::new('form-data')
$FileHeader.Name = $FieldName
$FileHeader.FileName = Split-Path -leaf $FilePath
$FileContent = [System.Net.Http.StreamContent]::new($FileStream)
$FileContent.Headers.ContentDisposition = $FileHeader
$FileContent.Headers.ContentType = [System.Net.Http.Headers.MediaTypeHeaderValue]::Parse($ContentType)

$MultipartContent = [System.Net.Http.MultipartFormDataContent]::new()
$MultipartContent.Add($FileContent)

$Response = Invoke-WebRequest -Body $MultipartContent -Method 'POST' -Uri 'https://api.contoso.com/upload'
```

```powershell
$Uri = 'https://api.contoso.com/v2/profile'
$Form = @{
    firstName = 'John'
    lastName = 'Doe'
    email = 'john.doe@contoso.com'
    avatar = Get-Item -Path 'c:\Pictures\jdoe.png'
    birthday = '1980-10-15'
    hobbies = 'Hiking', 'Fishing', 'Jogging'
}
$Result = Invoke-WebRequest -Uri $Uri -Method Post -Form $Form
```
