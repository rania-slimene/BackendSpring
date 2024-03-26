package DTO;

import lombok.Data;

@Data
public class ClientDtoFineract {
    private Long officeId;
    private String  firstname ;
    private String  lastname ;
    private Long legalFormId;
    private String locale;
    private String externalId;
}
