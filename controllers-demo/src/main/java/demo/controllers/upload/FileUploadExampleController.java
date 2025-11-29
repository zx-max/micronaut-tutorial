package demo.controllers.upload;


import demo.controllers.upload.dto.UploadBase64Dto;
import io.micronaut.core.io.IOUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Base64;

/**
 * https://owasp.org/www-community/attacks/Path_Traversal
 * https://www.google.com/search?q=owasp+file+upload
 * https://www.google.com/search?q=owasp+file+upload+cheat+sheet
 * <p>
 * https://cheatsheetseries.owasp.org/cheatsheets/File_Upload_Cheat_Sheet.html
 * https://owasp.org/www-project-web-security-testing-guide/v42/4-Web_Application_Security_Testing/10-Business_Logic_Testing/09-Test_Upload_of_Malicious_Files
 */
@Slf4j
@Controller("/upload")
public class FileUploadExampleController {
    public static final String REDIRECT_PATH = "/";

    @Consumes(MediaType.MULTIPART_FORM_DATA) // <5>
    @Post("/CompletedFileUpload") // <6>
    public HttpResponse upload(CompletedFileUpload file) throws IOException { // <7>
        /*
        http://localhost:8080/upload/CompletedFileUpload
         */
        String filename = file.getFilename();
        log.info("file: %s".formatted(filename));
        if ((filename == null || filename.equals(""))) {
            return HttpResponse.seeOther(URI.create(REDIRECT_PATH));
        }
        byte[] bytes = file.getBytes();
        File outputFile = new File("other/files/test_download/temp/" + filename);
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(bytes);
        }

        if (outputFile.exists()) {
            // the file is created
            // as the function returned true
            System.out.println("Temp File created: " + outputFile.getName() + ", " + outputFile.getAbsolutePath());
        }
        return HttpResponse.seeOther(URI.create(REDIRECT_PATH)); // <8>
    }

    @Post(value = "/base-64",
          processes = MediaType.APPLICATION_JSON)
    public void upload_base_64(@Body UploadBase64Dto uploadBase64Dto) throws IOException {
        /*
        http://localhost:8080/upload/base-64
         */
        byte[] content = Base64.getDecoder().decode(uploadBase64Dto.getBase64());
        File tempFile = File.createTempFile("other/files/test_download/temp/", uploadBase64Dto.getName());
        FileOutputStream fos = new FileOutputStream(tempFile);
        fos.write(content);
        fos.flush();
        fos.close();

        if (tempFile.exists()) {
            // the file is created
            // as the function returned true
            System.out.println("Temp File created: " + tempFile.getName() + ", " + tempFile.getAbsolutePath());
        }
    }


    @Post(value = "/inputStream",
          processes = MediaType.TEXT_PLAIN)
    @ExecuteOn(TaskExecutors.IO)
    String read(@Body InputStream inputStream) throws IOException {
        String s = IOUtils.readText(new BufferedReader(new InputStreamReader(inputStream)));
        System.out.println(s);
        return s;
    }

}
