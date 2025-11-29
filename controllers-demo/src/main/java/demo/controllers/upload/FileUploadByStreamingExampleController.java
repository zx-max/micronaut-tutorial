package demo.controllers.upload;


import io.micronaut.core.io.IOUtils;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.StreamingFileUpload;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
@Controller("/upload/streaming")
public class FileUploadByStreamingExampleController {
    public static final String REDIRECT_PATH = "/create";


    /**
     * <pre>
     * KO
     * Invoke-WebRequest -Uri 'http://localhost:8080/upload/streaming/StreamingFileUpload_1' -ContentType multipart/form-data -InFile C:\tmp\test.txt -Method Post
     * OK
     * curl --form "file=@C:/tmp/test.txt" http://localhost:8080/upload/streaming/StreamingFileUpload_1
     * </pre>
     *
     * @param file
     *
     * @return
     *
     * @throws IOException
     */
    @Consumes(MediaType.MULTIPART_FORM_DATA) // <1>
    @Post("/StreamingFileUpload_1") // <2>
    public String upload(StreamingFileUpload file) throws IOException {
// https://github.com/micronaut-guides/micronaut-file-upload/blob/master/complete/src/main/java/example/micronaut/StreamingFileUploadController.java

        File tempFile = File.createTempFile("other/files/test_download/temp/",
                                            "StreamingFileUpload_1_" + file.getFilename());
        file.transferTo(tempFile);

        String logMsg = "Temp File created: " + tempFile.getName() + ", " + tempFile.getAbsolutePath();
        if (tempFile.exists()) {
            log.info(logMsg);
        }
        return logMsg; // <3>
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
