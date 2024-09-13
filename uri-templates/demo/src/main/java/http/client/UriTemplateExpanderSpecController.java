package http.client;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

@Controller(UriTemplateExpanderSpecController.URI_TEMPLATE_EXPANDER_SPECIFICATION)
public class UriTemplateExpanderSpecController {

    public static final String URI_TEMPLATE_EXPANDER_SPECIFICATION = "/uri-template-expander-specification";

    /**
     * http://localhost:8080{+path,x}/here
     * [path: "/foo/bar", x: 1024]
     * http://localhost:8080/foo/bar,1024/here
     */
    @Get("/section-323{+path,x}/here")
    Map<String, String> reserved_expansion_with_multiple_variables(@Nullable String path, @Nullable String x, HttpRequest<?> httpRequest) throws MalformedURLException, URISyntaxException {
        /*
        url in uri template test:
        http://localhost:8080/uri-template-expander-specification/section-323{+path,x}/here
        http://localhost:8080/uri-template-expander-specification/section-323/foo/bar,1024/here

        # powershell:
            Invoke-WebRequest -Uri 'http://localhost:8080/uri-template-expander-specification/section-323/foo/bar,1024/here' -Method Get
        # curl:
            curl http://localhost:8080/uri-template-expander-specification/section-323/foo/bar,1024/here
        # received values :
            path: /,
            x: foo/bar,1024,
         */

        Map<String, String> inputData = Map.of("path", path, "x", x);
        String inputDataAsString = "path: %s, \nx: %s, \nhttpRequest: %s".formatted(path, x, httpRequest);
        System.out.println(inputDataAsString);

        return inputData;
    }
}
