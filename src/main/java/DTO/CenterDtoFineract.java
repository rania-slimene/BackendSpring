package DTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;
@Data
public class CenterDtoFineract {
    public Long officeId;
    public String name;
    public Boolean active;
    private String activationDate;
    public String dateFormat;
    public String locale;
    public List groupMembers;
}