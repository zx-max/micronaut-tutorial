package demo.controllers.cookies_and_headers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.CookieValue;
import io.micronaut.http.annotation.Get;

@Controller("/cookies")
public class CookiesExamplesController {


    @Get("/cookieName")
    public String cookieName(@CookieValue("myCookie") String myCookie) {
        return myCookie;
    }

    @Get("/cookieInferred")
    public String cookieInferred(@CookieValue String myCookie) {
        return myCookie;
    }


}
