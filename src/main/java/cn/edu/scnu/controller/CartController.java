package cn.edu.scnu.controller;

import cn.edu.scnu.entity.Cart;
import cn.edu.scnu.entity.TbMember;
import cn.edu.scnu.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/cart/addCart")
    public String addCart(String flowerid, HttpSession session){
        TbMember member=(TbMember)session.getAttribute("memberLogin");
        Cart cart = new Cart();
        cart.setFlowerid(flowerid);
        cart.setEmail(member.getEmail());
        cart.setNum(1);
        cartService.addCart(cart);
        return "redirect:/cart/showCart";
    }

    @RequestMapping("/cart/showCart")
    public String showCart(Model model,HttpSession session){
        TbMember member=(TbMember)session.getAttribute("memberLogin");
        List<Cart> carts=cartService.showCart(member.getEmail());
        model.addAttribute("carts",carts);
        return "showcart";
    }

    @RequestMapping("/cart/updateCart")
    public String updateCart(Integer cartId, Integer num) {
        cartService.updateCart(cartId, num);
        return "redirect:/cart/showCart";
    }

    @RequestMapping("/cart/deleteCart")
    public String updateCart(Integer cartId){
        cartService.removeById(cartId);
        return "redirect: /cart/showCart";
    }
}