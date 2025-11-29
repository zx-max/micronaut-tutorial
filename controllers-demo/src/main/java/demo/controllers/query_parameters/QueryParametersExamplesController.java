package demo.controllers.query_parameters;

import demo.controllers.request.dto.Query003Dto;
import demo.controllers.request.dto.Query005AaDto;
import demo.controllers.request.dto.Query005BbDto;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

import java.util.List;
import java.util.Map;

@Controller("/query-parameters")
public class QueryParametersExamplesController {

//    le parentesi diventano map ?
//TODO
//    http://localhost:8000/lastnames/location/city/215722?filter=beginswith:p&paging=(offset:2,limit:2)

    @Get("/case-01")
    String case_01(@Nullable @QueryValue("name") String name) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/case-01?name=/foo/bar' -Method Get
        /case-01, name: /foo/bar
         */
        String logMsg = "/case-01, name: %s".formatted(name);
        System.out.println(logMsg);
        return logMsg;
    }

    @Get("/case-02")
    String case_02(@Nullable String name) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/case-02?name=/foo/bar' -Method Get
        /case-02, name: /foo/bar
         */
        String logMsg = "/case-02, name: %s".formatted(name);
        System.out.println(logMsg);
        return logMsg;
    }

    @Get("/case-03{?x}")
    String case_03(@Nullable String x) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/case-03?x=/foo/bar' -Method Get
         */
        String logMsg = "/case-03{?x}, x: %s".formatted(x);
        System.out.println(logMsg);
        return logMsg;
    }

    @Get("/case-04{?x,y}")
    String case_04(@Nullable String x, @Nullable String y) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/case-04?x=/foo/bar;y=yyy' -Method Get
        /case-04{?x,y}, x: /foo/bar, y: yyy
         */
        String logMsg = "/case-04{?x,y}, x: %s, y: %s".formatted(x, y);
        System.out.println(logMsg);
        return logMsg;
    }

    @Get("/case-05{?x,y,z}")
    String case_05(@Nullable String x, @Nullable String y, @Nullable String z) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/case-05?x=/foo/bar;y=yyy;z=zzz' -Method Get
        /case-05{?x,y,z}, x: /foo/bar, y: yyy, z: zzz
         */
        String logMsg = "/case-05{?x,y,z}, x: %s, y: %s, z: %s".formatted(x, y, z);
        System.out.println(logMsg);
        return logMsg;
    }

    @Get("/case-06{?x}{;y,z}")
    String case_06(@Nullable String x, @Nullable String y, @Nullable String z) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/case-06?x=/foo/bar;y=yyy;z=zzz' -Method Get
        /case-06{?x,y,z}, x: /foo/bar, y: yyy, z: zzz
         */
        String logMsg = "/case-06{?x,y,z}, x: %s, y: %s, z: %s".formatted(x, y, z);
        System.out.println(logMsg);
        return logMsg;
    }

    @Get("/case-07{?x}{&y,z}")
    String case_07(@Nullable String x, @Nullable String y, @Nullable String z) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/case-06?x=/foo/bar&y=yyy&z=zzz' -Method Get
        /case-06{?x,y,z}, x: /foo/bar, y: yyy, z: zzz
         */
        String logMsg = "/case-07{?x}{&y,z}, x: %s, y: %s, z: %s".formatted(x, y, z);
        System.out.println(logMsg);
        return logMsg;
    }


    @Get("/query-010{?name*}{;count}")
    List<String> query_010(@Nullable List<String> name, @Nullable String count) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-010?name=1965&name=2000&name=2012;count=one;count=two;count=three' -Method Get
        name: [1965, 2000, 2012], count: one,two,three
        */
        System.out.printf("name: %s, count: %s%n", name, count);
        return name;
    }

    @Get("/query-009{?name*}{;count*}")
    List<String> query_009(@Nullable List<String> name, @Nullable List<String> count) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-009?name=1965&name=2000&name=2012;count=one;count=two;count=three' -Method Get
        name: [1965, 2000, 2012], count: [one, two, three]
        */
        System.out.printf("name: %s, count: %s%n", name, count);
        return count;
    }

    @Get("/query-008{?name*}{&count*}")
    List<String> query_008(@Nullable List<String> name, @Nullable List<String> count) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-008?name=1965&name=2000&name=2012&count=one&count=two&count=three' -Method Get
        name: [1965, 2000, 2012], count: [one, two, three]
        */
        System.out.printf("name: %s, count: %s%n", name, count);
        return count;
    }

    @Get("/query-007{?name*}")
    List<String> query_007(List<String> name) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-007?name=1965&name=2000&name=2012' -Method Get
        name: [1965, 2000, 2012]
        */
        System.out.printf("name: %s%n", name);
        return name;
    }


    @Get("/query-006{?name,empty}")
    String query_006(@Nullable String name, @Nullable String empty) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-006?name=1024&empty=' -Method Get
        name: 1024, empty:
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-006?name=1024&empty' -Method Get
        name: 1024, empty:
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-006?name=1024&' -Method Get
        name: 1024, empty: null
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-006?name=1024' -Method Get
        name: 1024, empty: null
        */
        System.out.printf("name: %s, empty: %s%n", name, empty);
        return name;
    }


    @Get("/query-005{?name*}{&baz*}")
    Query005AaDto query_005(@Nullable Query005AaDto name, @Nullable Query005BbDto baz) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-005?aa=nnn&bb=bbb' -Method Get
        name: Query006AaDto(aa=nnn), baz: Query006BbDto(bb=bbb)
        */
        System.out.printf("name: %s, baz: %s%n", name, baz);
        return name;
    }

    @Get("/query-004{?name*}{&baz*}")
    Map query_004(Map name, Map baz) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-004?var=foo' -Method Get
        name: {var=[foo]}
        baz: {var=[foo]}
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-004?var=foo&aa=3,bb=hello world' -Method Get
        name: {var=[foo], aa=[3,bb=hello world]}
        baz: {var=[foo], aa=[3,bb=hello world]}
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-004?var=foo&aa=3&bb=hello world' -Method Get
        name: {var=[foo], aa=[3], bb=[hello world]}
        baz: {var=[foo], aa=[3], bb=[hello world]}
        */
        System.out.printf("name: %s%n", name);
        System.out.printf("baz: %s%n", baz);
        return name;
    }

    @Get("/query-003{?req*}")
    Query003Dto query_003(Query003Dto req) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-003?var=foo' -Method Get
        req: Query003Dto(var=foo, aa=0, bb=null)
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-003?var=foo&aa=3&bb=hello world' -Method Get
        {"var":"foo","aa":3,"bb":"hello world"}
        */
        System.out.printf("req: %s%n", req);
        return req;
    }

    @Get("/query-002{?req*}")
    Map query_002(Map req) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-002?var=foo' -Method Get
        req: {var=[foo]}
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-002?var=foo&aa=3,bb=hello world' -Method Get
        req: {var=[foo], aa=[3,bb=hello world]}
        Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-002?var=foo&aa=3&bb=hello world' -Method Get
         {"var":["foo"],"aa":["3"],"bb":["hello world"]}
        */
        System.out.printf("req: %s%n", req);
        return req;
    }


    //    ----------------------------------------------------------------------------
    //    Binding from Multiple Query values
    //    ----------------------------------------------------------------------------


    @Get("/multiple-query-values-2/list{?colors*}")
    public void multiple_query_values(@QueryValue Map<String, Object> colors) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/annotations/multiple-query-values-2/list?aa=aaaa&bb=true' -Method Get
        {aa=[aaaa], bb=[true]}
         */
        System.out.println(colors);
    }


    /**
     * HttpGetSpec
     *
     * @Get("/multipleExplodedQueryParams{?bar*,tag}") String multipleExplodedQueryParams(@QueryValue("bar")
     * List<String> bar, @Nullable @QueryValue("tag") String label, HttpRequest<?> request) {
     */
    @Get("/multiple-query-values-3/list{?bar*,tag}")
    public void multiple_query_values_3(@QueryValue("bar") List<String> foo, @Nullable @QueryValue("tag") String tag,
                                        HttpRequest<?> request) {
        /*
        Invoke-WebRequest -Uri 'http://localhost:8080/annotations/multiple-query-values-3/list?tag=v_tag&bar=v_bar,sdfa' -Method Get
         */
        System.out.println("foo: " + foo);
        System.out.println("tag: " + tag);
        System.out.println("request: " + request.toString());
    }

}
