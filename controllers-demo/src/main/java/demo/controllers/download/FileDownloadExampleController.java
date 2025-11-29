package demo.controllers.download;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.io.Writable;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.types.files.StreamedFile;
import io.micronaut.http.server.types.files.SystemFile;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

@Slf4j
@Controller("/download")
public class FileDownloadExampleController {

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Get("/pdf-Writable")
    HttpResponse<Writable> pdf_Writable() throws IOException {
        /*
        http://localhost:8080/download/pdf-Writable
        Invoke-WebRequest -Uri 'http://localhost:8080/download/pdf-Writable' -Method Get
         */
        Writable writable = new Writable() {
            public void writeTo(Writer out) {
            }

            public void writeTo(OutputStream outputStream, @Nullable Charset charset) throws IOException {
                File file = new File("other/files/file_pdf_01.pdf");
                byte[] fileContent = Files.readAllBytes(file.toPath());
                outputStream.write(fileContent);
            }
        };

        MutableHttpResponse<Writable> httpResponse = HttpResponse.ok(writable)
                                                                 .header(HttpHeaders.CONTENT_DISPOSITION,
                                                                         "attachment; filename=" + "pdf_Writable.pdf")
                                                                 .header(HttpHeaders.CONTENT_TYPE,
                                                                         MediaType.APPLICATION_PDF);
        return httpResponse;
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Get("/pdf-Writable-a")
    HttpResponse<Writable> pdf_Writable_a() throws IOException {
        Writable writable = new Writable() {
            public void writeTo(Writer out) {
            }

            public void writeTo(OutputStream outputStream, @Nullable Charset charset) throws IOException {
                File file = new File("other/files/file_pdf_01.pdf");
                InputStream inputStream = new FileInputStream(file);
                byte[] buf = new byte[8192];
                int length;
                while ((length = inputStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, length);
                }
            }
        };

        MutableHttpResponse<Writable> httpResponse = HttpResponse.ok(writable)
                                                                 .header(HttpHeaders.CONTENT_DISPOSITION,
                                                                         "attachment; filename=" + "pdf_Writable_a.pdf")
                                                                 .header(HttpHeaders.CONTENT_TYPE,
                                                                         MediaType.APPLICATION_PDF);
        return httpResponse;
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Get("/pdf-Writable-b")
    HttpResponse<Writable> pdf_Writable_b() throws IOException {
        Writable writable = new Writable() {
            public void writeTo(Writer out) {
            }

            public void writeTo(OutputStream outputStream, @Nullable Charset charset) throws IOException {
                File file = new File("other/files/file_pdf_01.pdf");
                try (InputStream inputStream = new FileInputStream(file)) {
                    inputStream.transferTo(outputStream);
                }
            }
        };

        MutableHttpResponse<Writable> httpResponse = HttpResponse.ok(writable)
                                                                 .header(HttpHeaders.CONTENT_DISPOSITION,
                                                                         "attachment; filename=" + "pdf_Writable_b.pdf")
                                                                 .header(HttpHeaders.CONTENT_TYPE,
                                                                         MediaType.APPLICATION_PDF);
        return httpResponse;
    }

    @Get("/excel-StreamedFile")
    public StreamedFile excel_StreamedFile() throws IOException {
        File file = new File("other/files/file_excel_01.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        StreamedFile streamedFile = new StreamedFile(inputStream, MediaType.APPLICATION_OCTET_STREAM_TYPE);
        streamedFile.attach("excel_StreamedFile.xlsx");
        return streamedFile;
    }

    @Produces(value = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    @Get("/excel-SystemFile")
    public SystemFile excel_SystemFile() {
        File file = new File("other/files/file_excel_01.xlsx");
        SystemFile systemFileXls = new SystemFile(file).attach("excel_SystemFile.xlsx");
        return systemFileXls;
    }
}
