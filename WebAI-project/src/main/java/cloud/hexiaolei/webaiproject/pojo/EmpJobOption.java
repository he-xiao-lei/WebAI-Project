package cloud.hexiaolei.webaiproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpJobOption {
    private List jobList;
    private List dataList;
}
