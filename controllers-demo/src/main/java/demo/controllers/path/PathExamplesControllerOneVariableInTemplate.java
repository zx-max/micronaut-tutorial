package demo.controllers.path;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.convert.format.Format;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller("/one-variable-in-template/path-examples")
public class PathExamplesControllerOneVariableInTemplate {

    @Get("/regex/{name:^blue|orange$}")
    String path_007(@Nullable String name) {
        String inputFormatted = "/regex/{name:^blue|orange$} name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/dateTime/{myDate}")
    String formatDateTime(@Format("yyyy-MM-dd") LocalDate myDate) {
        String dateReFormatted = myDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        String inputFormatted = "/dateTime/{myDate}  [\"yyyy-MM-dd\"] myDate: %s, dateReFormatted: %s".formatted(
            myDate.toString(), dateReFormatted);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/dateTime-2/{myDate}")
    String formatDateTime_2(@Format("MMMM dd yyyy 'at' HH:mm:ss a") LocalDate myDate) {
        String dateReFormatted = myDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        String inputFormatted = "/dateTime-2/{myDate}  [\"MMMM dd yyyy 'at' HH:mm:ss a\"] myDate: %s, dateReFormatted: %s".formatted(
            myDate.toString(), dateReFormatted);
        System.out.println(inputFormatted);
        return inputFormatted;
    }


    @Get("/case-1/{name}")
    String case_1(String name) {
        String inputFormatted = "/case-1/{name}, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-1a/{aaa}")
    String case_1a(@PathVariable("aaa") String name) {
        String inputFormatted = "/case-1a/{aaa}, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-1b/{name}{#hash}")
    String case_1b(String name, @Nullable String hash) {
        String inputFormatted = "case-1b/{aaa}{#hash}, name: %s, hash: %s".formatted(name, hash);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-2/{+name}")
    String case_2(String name) {
        String inputFormatted = "/case-2/{+name}, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }


    @Get("/case-2a/{+name}/here")
    String case_2a(@PathVariable("name") String name) {
        String inputFormatted = "/case-2a/{+name}/here, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }


    @Get("/case-3/{/name}")
    String case_3(@Nullable String name) {
        String inputFormatted = "/case-3/{/name}, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }


    @Get("/case-4{name}")
    String case_(String name) {
        String inputFormatted = "/case-4{name}, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-5{+name}")
    String case_5(String name) {
        String inputFormatted = "/case-5{+name}, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-6{/name}")
    String case_6(@Nullable String name) {
        String inputFormatted = "/case-6{/name}, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-6a{/name}{/x}{/y}")
    String case_6a(@Nullable String name, Optional<String> x, Optional<String> y) {
        String inputFormatted = "case-6a{/name}{/x}{/y}, name: %s, x:%s, y:%s".formatted(name, x, y);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-6d{/name}/here")
    String case_6d(@Nullable String name) {
        String inputFormatted = "/case-6d{/name}/here, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-6e{/name*}/here")
    String case_6e(@Nullable String name) {
        String inputFormatted = "/case-6e{/name*}/here, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-6f{+name*}/here")
    String case_6f(@Nullable String name) {
        String inputFormatted = "/case-6f{+name*}/here, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-7/{name*}")
    String case_7(String name) {
        String inputFormatted = "/case-7/{name*}, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-8/{+name*}")
    String case_8(String name) {
        String inputFormatted = "/case-8/{+name*}, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-9/{/name*}")
    String case_9(String name) {
        String inputFormatted = "/case-9/{/name*}, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }


    @Get("/case-10{name*}")
    String case_10(String name) {
        String inputFormatted = "/case-10{name*}, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }


    @Get("/case-11{+name*}")
    String case_11(String name) {
        String inputFormatted = "/case-11{+name*}, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-12{/name*}")
    String case_12(String name) {
        String inputFormatted = "/case-12{/name*}, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }


    @Get("/case-13/xxx{.name}")
    String case_13(@Nullable String name) {
        String inputFormatted = "/case-13/xxx{.name}, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }

    @Get("/case-13a/xxx{.name}/here")
    String case_13a(@Nullable String name) {
        String inputFormatted = "/case-13a/xxx{.name}/here, name: %s".formatted(name);
        System.out.println(inputFormatted);
        return inputFormatted;
    }
}
