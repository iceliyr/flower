package cn.edu.scnu.service;

import cn.edu.scnu.entity.MyOrder;
import cn.edu.scnu.entity.Showorder;
import cn.edu.scnu.entity.Showshoplist;
import cn.edu.scnu.mapper.OrderMapper;
import cn.edu.scnu.mapper.ShoworderMapper;
import cn.edu.scnu.mapper.ShowshoplistMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class OrderService extends ServiceImpl<OrderMapper, MyOrder> {

    private OrderMapper orderMapper;

    private ShoworderMapper showorderMapper;

    private ShowshoplistMapper showshoplistMapper;

    @Autowired
    public OrderService(OrderMapper orderMapper, ShoworderMapper showorderMapper, ShowshoplistMapper showshoplistMapper) {
        this.orderMapper = orderMapper;
        this.showorderMapper = showorderMapper;
        this.showshoplistMapper = showshoplistMapper;
    }


    public List<Showorder> showorder(String email) {
        QueryWrapper<Showorder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        queryWrapper.orderByDesc("order_id");
        return showorderMapper.selectList(queryWrapper);
    }

    public List<Showshoplist> findShoplistByOrderId(Integer orderId) {
        QueryWrapper<Showshoplist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        return showshoplistMapper.selectList(queryWrapper);
    }

    public void updateOrder(Integer orderId, String status) {
        MyOrder order = orderMapper.selectById(orderId);
        order.setStatus(status);
        orderMapper.updateById(order);
    }
}
