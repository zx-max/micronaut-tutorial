package demo.controllers.path;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.convert.format.Format;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller("/two-variable-in-template/path-examples")
public class PathExamplesControllerTwoVariablesInTemplate {

    @Get("/case-1/{name,x}")
    String case_1(String name, String x) {
        String inputFormatted = "/case-1/{name,x}, name: %s, x: %s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-1a/{aaa,x}")
    String case_1a(@PathVariable("aaa") String name, String x) {
        String inputFormatted = "/case-1a/{aaa,x}, name: %s, x: %s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-1b/{name,x,y}{#hash}")
    String case_1b(String name, @Nullable String hash, String x, String y) {
        String inputFormatted = "case-1b/{aaa}{#hash}, name: %s, hash: %s, x: %s, y:%s".formatted(name, hash, x, y);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-1c/{name,x}")
    String case_1c(String name, String x) {
        String inputFormatted = "case-1c/{aaa}{#hash}, name: %s, x: %s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-2/{+name,x}")
    String case_2(String name, String x) {
        String inputFormatted = "case-2/{+name,x}, name: %s, x: %s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }


    @Get("/case-2a/{+name,x}/here")
    String case_2a(@PathVariable("name") String name, String x) {
        String inputFormatted = "/case-2a/{+name,x}/here, name: %s, x: %s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }


    @Get("/case-3/{/name,x}")
    String case_3(@Nullable String name, @Nullable String x) {
        String inputFormatted = "/case-3/{/name,x}, name: %s, x: %s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }


    @Get("/case-4{name,x}")
    String case_(String name, String x) {
        String logMsg = "/case-4{name,x}, name: %s, x: %s".formatted(name, x);
        System.out.println(logMsg);
        return logMsg;
    }

    @Get("/case-5{+name,x}")
    String case_5(String name, String x) {
        String inputFormatted = "/case-5{+name,x}, name: %s, x: %s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-6{/name,x}")
    String case_6(@Nullable String name, @Nullable String x) {
        String inputFormatted = "/case-6{/name,x}, name: %s, x: %s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-6a{/name,x}{/a}{/b}")
    String case_6a(@Nullable String name, Optional<String> a, Optional<String> b, @Nullable String x) {
        String inputFormatted = "case-6a{/name,x}{/a}{/b}, name: %s, x: %s, a:%s, b:%s".formatted(name, x, a, b);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-6b{/name,x}")
    String case_6b(Optional<String> name, Optional<String> x) {
        String inputFormatted = "/case-6b{/name,x}, name: %s, x:%s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-6c{/name,x}")
    String case_6c(@Nullable String name, @Nullable String x) {
        String inputFormatted = "/case-6c{/name,x}, name: %s, x:%s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-6d{/name,x}/here")
    String case_6d(@Nullable String name, @Nullable String x) {
        String inputFormatted = "/case-6d{/name,x}, name: %s, x:%s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-6e{/name,x,y}/here")
    String case_6e(@Nullable String name, @Nullable String x, @Nullable String y) {
        String inputFormatted = "/case-6e{/name,x,y}/here, name: %s, x:%s, y:%s".formatted(name, x, y);
        System.out.println(inputFormatted);
        return inputFormatted;
    }


    @Get("/case-7/{name,x*}")
    String case_7(String name, String x) {
        String inputFormatted = "/case-7/{name,x*}, name: %s, x:%s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-8/{+name,x*}")
    String case_8(String name, String x) {
        String inputFormatted = "/case-8/{+name,x*}, name: %s, x:%s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-9/{/name,x*}")
    String case_9(@Nullable String name, String x) {
        String inputFormatted = "/case-9/{/name,x*}, name: %s, x:%s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }


    @Get("/case-10{name,x*}")
    String case_10(String name, String x) {
        String inputFormatted = "/case-10{name,x*}, name: %s, x:%s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }


    @Get("/case-11{+name,x*}")
    String case_11(String name, String x) {
        String inputFormatted = "/case-11{+name,x*}, name: %s, x:%s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-12{/name,x*}")
    String case_12(@Nullable String name, String x) {
        String inputFormatted = "/case-12{/name,x*}, name: %s, x:%s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }


    @Get("/case-13/xxx{.name,x}")
    String case_13(@Nullable String name, @Nullable String x) {
        String inputFormatted = "/case-13/xxx{.name,x}, name: %s, x: %s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-13a/xxx{.name,x}/here")
    String case_13a(@Nullable String name, @Nullable String x) {
        String inputFormatted = "/case-13a/xxx{.name,x}/here, name: %s, x: %s".formatted(name, x);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

}
