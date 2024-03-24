package DTO;

import lombok.Data;

@Data
public class PutClientDtoFineract {
    private String  firstname ;
    private String  lastname ;
    private Long legalFormId;
    private String locale;
}
