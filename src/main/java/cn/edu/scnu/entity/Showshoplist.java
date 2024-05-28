package cn.edu.scnu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
@Data
public class Showshoplist {
    @TableId
    private Integer orderId;
    private int id;
    private int num;
    private String email;
    private String flowerid;
    private String fname;
    private String pictures;
    private int price;
    private int yourprice;
}
