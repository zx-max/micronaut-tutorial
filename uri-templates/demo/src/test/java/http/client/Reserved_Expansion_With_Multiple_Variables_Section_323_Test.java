package http.client;


import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriMatchInfo;
import io.micronaut.http.uri.UriTemplateMatcher;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static http.client.UriTemplateExpanderSpecController.URI_TEMPLATE_EXPANDER_SPECIFICATION;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * | +             | Reserved expansion with multiple variables    (Sec 3.2.3) |
 * |               | {+x,hello,y} 1024,Hello%20World!,768                      |
 * |               | {+path,x}/here /foo/bar,1024/here                         |
 */
@MicronautTest
public class Reserved_Expansion_With_Multiple_Variables_Section_323_Test {
    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void multiple_varaible_in_path_is_matched_from_http_annotation_on_controller() {
        String x = "x";
        String path = "path";

        String expectedValueOf_Path = "/foo/bar";
        String expectedValueOf_X = "1024";
        String expectedUrl = URI_TEMPLATE_EXPANDER_SPECIFICATION + "/section-323/foo/bar,1024/here";


        String urlTemplate = URI_TEMPLATE_EXPANDER_SPECIFICATION + "/section-323{+path,x}/here";
        UriTemplateMatcher uriTemplateMatcher = new UriTemplateMatcher(urlTemplate);
        String url = uriTemplateMatcher.expand(Map.of(path, expectedValueOf_Path, x, expectedValueOf_X));
        assertEquals(expectedUrl, url); // template is correctly expanded



        Map<String, String> response = client.toBlocking().retrieve(HttpRequest.GET(url), Map.class);
        String actualValueOf_Path = response.get(path);
        String actualValueOf_X = response.get(x);
        System.out.println(actualValueOf_X);    //  x:      foo/bar,1024
        System.out.println(actualValueOf_Path); //  path:   /



        // variables are not correctly extracted
        assertEquals(expectedValueOf_Path, actualValueOf_Path);
        assertEquals(expectedValueOf_X, actualValueOf_X);

    }
}
