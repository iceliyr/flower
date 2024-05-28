package cn.edu.scnu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.sql.Timestamp;
@Data
public class Showorder{
    @TableId
    private Integer orderId;
    private String email;
    private Timestamp inputtime;
    private String sname;
    private String address;
    private Integer shifu;
    private String peisongday;
    private String status;
}
