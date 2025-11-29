package demo.controllers.request.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Data
@Serdeable
public class Query005BbDto {
    private String bb;
}
