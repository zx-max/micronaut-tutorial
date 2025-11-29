package demo.controllers.cookies_and_headers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;


@Controller("/headers")
public class HeadersExamplesController {

    @Get("/header__My_header")
    public String header__My_header(@Header("My_Header") String myheader) {
        return "header__My_header: " + myheader;
    }

    @Get("/header__Content_Type")
    public String header__Content_Type(@Header("Content_Type") String contentType) {
        return "header__Content_Type: " + contentType;
    }

    @Get("/header__Content_Less_Type")
    public String header__Content_Less_Type(@Header(value = "Content-Type") String contentType) {
        return "header__Content_Less_Type: " + contentType;
    }

    @Get("/contentTypeInferred")
    public String contentTypeInferred(@Header String contentType) {
        return "contentTypeInferred: " + contentType;
    }


}
