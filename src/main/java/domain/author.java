package domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class author {
    private Integer id;
    private  String password;
    private String name;
    private String introduction;
    private Date date_of_birth;
}
