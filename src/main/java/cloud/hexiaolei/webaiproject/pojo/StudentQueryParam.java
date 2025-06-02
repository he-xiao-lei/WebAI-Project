package cloud.hexiaolei.webaiproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentQueryParam {
    private Integer page = 1;
    private Integer pageSize = 5;
    private Integer id;
    private Integer degree;
    private String name;
    private Integer clazzId;

}
