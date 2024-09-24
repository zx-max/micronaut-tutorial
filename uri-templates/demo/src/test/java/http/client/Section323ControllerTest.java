package http.client;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class Section323ControllerTest {
    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testTwoPathVars() {

        MutableHttpRequest<String> request = HttpRequest.GET("1)/foo/bar,1024/here");
        HttpResponse<Object> httpResponse = client.toBlocking().exchange(request);
        assertEquals(HttpStatus.OK, httpResponse.getStatus());
        assertEquals("1) path=foo/bar AND x=1024", httpResponse.getBody(Argument.STRING).get());
    }

    @Test
    void testOnePathVar() {
        MutableHttpRequest<String> request = HttpRequest.GET("2)/foo/bar,1024/here");
        HttpResponse<Object> httpResponse = client.toBlocking().exchange(request);
        assertEquals(HttpStatus.OK, httpResponse.getStatus());
        /* fails; the result is "2) path=f AND x=oo/bar,1024" */
        assertEquals("2) path=foo/bar AND x=1024", httpResponse.getBody(Argument.STRING).get());
    }

    @Test
    void testNoPathVar() {
        MutableHttpRequest<String> request = HttpRequest.GET("3)/foo/bar,1024/here");
        HttpResponse<Object> httpResponse = client.toBlocking().exchange(request);
        assertEquals(HttpStatus.OK, httpResponse.getStatus());
        /* fails; the result is 3) path=f AND x=oo/bar,1024" */
        assertEquals("3) path=foo/bar AND x=1024", httpResponse.getBody(Argument.STRING).get());
    }
}