package demo.controllers.request.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Data
@Serdeable
public class Query003Dto {
    private String var;
    private int aa;
    private String bb;
}
