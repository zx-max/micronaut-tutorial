package http.client;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

@Controller
public class Section323Controller {

    /* works the same if you remove the arguments' annotations */
    @Get("1)/{+path},{x}/here")
    public String getExpansionWithMultipleVars(@PathVariable("path") String path, @PathVariable("x") String x) {
        return "1) path=" + path + " AND x=" + x ;
    }

    @Get("2)/{+path,x}/here")
    public String getExpansionWithOneVar(@PathVariable("path") String path, String x) {
        return "2) path=" + path + " AND x=" + x ;
    }

    @Get("3)/{+path,x}/here")
    public String getExpansionWithNoVar(String path, String x) {
        return "3) path=" + path + " AND x=" + x ;
    }
}