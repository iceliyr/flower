package cn.edu.scnu.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class MyFlower {
    private String fname;
    private String myclass;
    private String fclass;
    private String fclass1;
    private String cailiao;
    private String baozhuang;
    private String huayu;
    private String shuoming;
    private Integer price;
    private Integer yourprice;
    private MultipartFile pictures;
    private MultipartFile picturem;
    private MultipartFile pictureb;
    private MultipartFile pictured;
    private MultipartFile cailiaopicture;
    private MultipartFile bzpicture;
    private String tejia;
    //自动生成getter和setter
}
