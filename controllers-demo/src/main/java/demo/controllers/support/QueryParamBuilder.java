package demo.controllers.support;


import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.beans.BeanProperty;
import io.micronaut.core.type.Argument;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Singleton;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class QueryParamBuilder {

    public String buildUrlWithQueryParamsFromDto(String inputUrl, Object dto, DateFormat dateFormat) throws MalformedURLException {
        UriBuilder uriBuilder = prepareUriBuilderWithQuesryParamsFromDto(inputUrl, dto, dateFormat);

        URI uri = uriBuilder.build();
        URL url = uri.toURL();
        String urlAsString = url.toString();
        return urlAsString;
    }

    public UriBuilder prepareUriBuilderWithQuesryParamsFromDto(String inputUrl, Object dto, DateFormat dateFormat) {
        UriBuilder uriBuilder = UriBuilder.of(inputUrl);
        Argument toType = Argument.of(dto.getClass());
        BeanIntrospection<Object> introspection = BeanIntrospection.getIntrospection(toType.getType());
        Collection<BeanProperty<Object, Object>> beanProperties = introspection.getBeanProperties();

        beanProperties.forEach(beanProperty -> {
            Object dtoPropertyValue = beanProperty.get(dto);
            if (Date.class.equals(beanProperty.getType())) {
                Date dtoPropertyValueAsDate = (Date) dtoPropertyValue;

                String dtoPropertyValueAsDateFormatted = dateFormat.format(dtoPropertyValueAsDate);
                uriBuilder.queryParam(beanProperty.getName(), dtoPropertyValueAsDateFormatted);

            } else if (dtoPropertyValue instanceof Collection dtoPropertyValueAsCollection) {
                dtoPropertyValueAsCollection.forEach(itemInList -> {
                    uriBuilder.queryParam(beanProperty.getName(), itemInList);
                });

            } else {
                uriBuilder.queryParam(beanProperty.getName(), dtoPropertyValue);
            }
        });
        return uriBuilder;
    }

}
