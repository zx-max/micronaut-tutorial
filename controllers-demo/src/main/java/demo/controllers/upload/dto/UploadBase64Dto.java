package demo.controllers.upload.dto;


import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.ReflectiveAccess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Introspected
@ReflectiveAccess
@NoArgsConstructor
@AllArgsConstructor
public class UploadBase64Dto {
    private String name;
    private String base64;
}
