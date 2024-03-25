package DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
@Data
public class StaffDtoFineract {
    public Long officeId;;
    public String firstname;
    public String lastname;
    public Boolean isLoanOfficer;
    public String joiningDate;
    public String locale;
    public String dateFormat;
}
