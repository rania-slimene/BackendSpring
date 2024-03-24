package DTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StaffDtoFineract {
    public Long officeId;
    public String firstname;
    public String lastname;
    public Boolean isLoanOfficer;
    public LocalDate joiningDate;
    public String locale;
    public String dateFormat;

}
