package das.java.internship.springproject1.dto;

import lombok.Data;

@Data
public class BoutiqueDTO {
    private Long id;
    private String name;
    private String shortName;
    private String address;
    private Long mallId;

}
