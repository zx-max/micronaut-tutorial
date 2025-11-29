package demo.controllers.support;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Introspected
public class TestDto {
    private String aString;
    private Long aLong;
    private Date aDate;
    private List aList;
}
