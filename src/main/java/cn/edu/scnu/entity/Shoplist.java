package cn.edu.scnu.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Timestamp;
@Data
@TableName("shoplist")
public class Shoplist {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private Integer orderId;
    private String flowerid;
    private Integer num;
    private String email;
    private String pjcontent;
    private Integer pjstar;
    private Timestamp pjtime;
    private String pjip;
    private String pjreplay;
}