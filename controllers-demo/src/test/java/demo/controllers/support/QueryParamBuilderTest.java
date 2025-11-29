package demo.controllers.support;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.beans.BeanProperty;
import io.micronaut.http.uri.UriBuilder;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class QueryParamBuilderTest {

    String buildUrlWithQueryParams(String inputUrl, TestDto testDto) throws MalformedURLException {
        UriBuilder uriBuilder = UriBuilder.of(inputUrl);
        BeanIntrospection<TestDto> introspection = BeanIntrospection.getIntrospection(TestDto.class);
        Collection<BeanProperty<TestDto, Object>> beanProperties = introspection.getBeanProperties();

        beanProperties.forEach(beanProperty -> {
            Object dtoPropertyValue = beanProperty.get(testDto);
            if (Date.class.equals(beanProperty.getType())) {
                Date dtoPropertyValueAsDate = (Date) dtoPropertyValue;

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String dtoPropertyValueAsDateFormatted = sdf.format(dtoPropertyValueAsDate);
                uriBuilder.queryParam(beanProperty.getName(), dtoPropertyValueAsDateFormatted);

            } else if (dtoPropertyValue instanceof Collection dtoPropertyValueAsCollection) {
                dtoPropertyValueAsCollection.forEach(itemInList -> {
                    uriBuilder.queryParam(beanProperty.getName(), itemInList);
                });

            } else {
                uriBuilder.queryParam(beanProperty.getName(), dtoPropertyValue);
            }
        });

        URI uri = uriBuilder.build();
        URL url = uri.toURL();
        String urlAsString = url.toString();
        return urlAsString;
    }

    @Test
    void aa_6() throws MalformedURLException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        QueryParamBuilder queryParamBuilder = new QueryParamBuilder();
        String inputUrl = "http://localhost:8080/aaa/bb";
        TestDto testDto = new TestDto("aaa", 3L, new Date(), Arrays.asList("ccc", 123));

        String urlAsString = queryParamBuilder.buildUrlWithQueryParamsFromDto(inputUrl, testDto, sdf);

        assertEquals("http://localhost:8080/aaa/bb?aString=aaa&aLong=3&aDate=01-10-2024&aList=ccc&aList=123", urlAsString);
    }

    @Test
    void aa_5() throws MalformedURLException {
        QueryParamBuilder queryParamBuilder = new QueryParamBuilder();
        String inputUrl = "http://localhost:8080/aaa/bb";
        TestDto testDto = new TestDto("aaa", 3L, new Date(), Arrays.asList("ccc", 123));

        String urlAsString = buildUrlWithQueryParams(inputUrl, testDto);

        assertEquals("http://localhost:8080/aaa/bb?aString=aaa&aLong=3&aDate=01-10-2024&aList=ccc&aList=123", urlAsString);
    }

    @Test
    void aa_4() throws MalformedURLException {
        String inputUrl = "http://localhost:8080/aaa/bb";
        UriBuilder uriBuilder = UriBuilder.of(inputUrl);

        TestDto testDto = new TestDto("aaa", 3L, new Date(), Arrays.asList("ccc", 123));
        BeanIntrospection<TestDto> introspection = BeanIntrospection.getIntrospection(TestDto.class);
        @NonNull String[] propertyNames = introspection.getPropertyNames();
        System.out.println(Arrays.asList(propertyNames));
        Collection<BeanProperty<TestDto, Object>> beanProperties = introspection.getBeanProperties();
        String s = beanProperties.toString().replaceAll("\\},", "\\},\n");
        System.out.println(s);

        beanProperties.forEach(beanProperty -> {
            Object dtoPropertyValue = beanProperty.get(testDto);
            if (java.util.Date.class.equals(beanProperty.getType())) {
                Date dtoPropertyValueAsDate = (Date) dtoPropertyValue;

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String dtoPropertyValueAsDateFormatted = sdf.format(dtoPropertyValueAsDate);
                uriBuilder.queryParam(beanProperty.getName(), dtoPropertyValueAsDateFormatted);

            } else if (dtoPropertyValue instanceof Collection dtoPropertyValueAsCollection) {
                dtoPropertyValueAsCollection.forEach(itemInList -> {
                    uriBuilder.queryParam(beanProperty.getName(), itemInList);
                });

            } else {
                uriBuilder.queryParam(beanProperty.getName(), dtoPropertyValue);
            }
        });

        URI uri = uriBuilder.build();
        URL url = uri.toURL();
        String urlAsString = url.toString();
        assertEquals("http://localhost:8080/aaa/bb?aString=aaa&aLong=3&aDate=01-10-2024&aList=ccc&aList=123", urlAsString);
    }


}