package cn.edu.scnu.mapper;

import cn.edu.scnu.entity.MyOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<MyOrder> {
}
