package DTO;

import lombok.Data;

@Data
public class DepositMoneyDtoFineract {
    public String  transactionDate;
    public String  transactionAmount;
    public Integer paymentTypeId;
    public String  locale;
    public String  dateFormat;
}
