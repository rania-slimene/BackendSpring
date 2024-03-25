package DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class CenterDtoFineract {
    public Long officeId;
    public String name;
    public Long staffId;
    public Boolean active;
    private String activationDate;
    public String dateFormat;
    public String locale;
    public List groupMembers;

}
