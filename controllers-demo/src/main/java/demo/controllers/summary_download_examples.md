# Requests from command line

- https://learn.microsoft.com/en-us/powershell/module/microsoft.powershell.utility/invoke-webrequest?view=powershell-7.4#example-8-download-multiple-files-at-the-same-time

## FileDownloadExampleController, FileDownloadExampleController2

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/download/pdf-Writable-a' -Method Get -OutFile C:\tmp\esempi_dowload\pdf-Writable-a.pdf
Invoke-WebRequest -Uri 'http://localhost:8080/download/pdf-Writable-b' -Method Get -OutFile C:\tmp\esempi_dowload\pdf-Writable-b.pdf
Invoke-WebRequest -Uri 'http://localhost:8080/download/excel-StreamedFile' -Method Get -OutFile C:\tmp\esempi_dowload\excel-StreamedFile.xls
Invoke-WebRequest -Uri 'http://localhost:8080/download/excel-SystemFile' -Method Get -OutFile C:\tmp\esempi_dowload\excel-SystemFile.xls
Invoke-WebRequest -Uri 'http://localhost:8080/download-2/with-reactor-Streaming-Http-Client' -Method Get -OutFile C:\tmp\esempi_dowload\logo_micronaut.png
Invoke-WebRequest -Uri 'http://localhost:8080/download-2/with-reactor-Streaming-Http-Client?q=https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png' -Method Get -OutFile C:\tmp\esempi_dowload\logo_google.png
```

```http-in-browser
http://localhost:8080/download/pdf-Writable-a
http://localhost:8080/download/pdf-Writable-b
http://localhost:8080/download/excel-StreamedFile
http://localhost:8080/download/excel-SystemFile
http://localhost:8080/download-2/with-reactor-Streaming-Http-Client
http://localhost:8080/download-2/with-reactor-Streaming-Http-Client?q=https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png
```
