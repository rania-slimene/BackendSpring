package DTO;

import lombok.Data;

@Data
public class SavingsaccountsDtoFineract {
    public Integer productId;
    public Integer clientId;
    public String dateFormat;
    public String locale;
    public String externalId;
    public String  fieldOfficerId;
    public String submittedOnDate;
    public Double nominalAnnualInterestRate;
    public Integer interestCompoundingPeriodType;
    public Integer interestPostingPeriodType;
    public Integer interestCalculationType;
    public Integer interestCalculationDaysInYearType;

}
