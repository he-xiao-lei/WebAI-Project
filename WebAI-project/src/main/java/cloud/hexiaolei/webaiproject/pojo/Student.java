package cloud.hexiaolei.webaiproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    /**
     * 主键 id
     */
    private Integer id;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 学生学号
     */
    private String no;
    /**
     *
     性别(1: 男 , 2: 女)
     */
    private Integer gender;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 学历(1:初中,2:高中,3:大专,4:本科,5:硕士,6:博士)
     */
    private Integer degree;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 是否为在校学生,1:是,0:否
     */
    private Integer isCollege;
    /**
     * 家庭地址
     */
    private String address;
    /**
     * 毕业时间
     */
    private LocalDate graduationDate;
    /**
     * 违纪次数
     */
    private Integer violationCount;
    /**
     * 违纪扣分
     */
    private Integer violationScore;
    /**
     * 班级id
     */
    private Integer clazzId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 班级名称
     */
    private String clazzName;
}
