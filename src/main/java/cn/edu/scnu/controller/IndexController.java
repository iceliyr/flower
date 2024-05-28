package cn.edu.scnu.controller;

import cn.edu.scnu.common.MD5Util;
import cn.edu.scnu.entity.Flower;
import cn.edu.scnu.entity.TbMember;
import cn.edu.scnu.service.FlowerService;
import cn.edu.scnu.service.MemberService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class IndexController{
    @Autowired
    private FlowerService flowerService;

    @Autowired
    private MemberService memberService;



    @RequestMapping({"/index","","/"})
    public String index(@RequestParam(name="pageNo",defaultValue = "1")Integer pageNo,
                        @RequestParam(name="pageSize",defaultValue = "4")Integer pageSize, //每页展示8条数据
                        String fname,
                        String fclass,
                        Integer minprice,
                        Integer maxprice,
                        Model model){
        if( minprice==null){
            minprice=0;
        }
        if( maxprice==null || maxprice<minprice  ){
            maxprice=Integer.MAX_VALUE;
        }
        Map<String,Object> map=flowerService.queryPage(fname,fclass,minprice,maxprice,pageNo,pageSize);
        Integer count=(Integer) map.get("count");
        List<Flower> flowerlist=(List<Flower>)map.get("recourds");
        int pageCount = (count%pageSize==0)?(count/pageSize):(count/pageSize + 1);
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("fname",fname);
        model.addAttribute("fclass",fclass);
        model.addAttribute("minprice",minprice);
        model.addAttribute("maxprice",maxprice);

        model.addAttribute("flowerlist",flowerlist);
        model.addAttribute("fclasses",flowerService.findfclass());
        return "index";
    }


    @RequestMapping("/index/flowerdetail")
    public String flowerdetail(String flowerid, Model model) {
        model.addAttribute("flower", flowerService.findFlowerById(flowerid));
        return "flowerdetail";
    }

    @RequestMapping("/index/toLogin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/index/doLogin")
    @ResponseBody
    public String doLogin(String email, String password, HttpSession session) {
        TbMember member = memberService.login(email, password);
        member.setPassword(""); //去敏
        if (member != null) {
            session.setAttribute("memberLogin", member);
            return "success";
        } else {
            return "登录失败!";
        }
    }

    @RequestMapping("/index/toRegister")
    public String register(){
        return "register";
    }

    @RequestMapping("/index/checkemail")
    @ResponseBody
    public String checkEmail(@RequestParam("email") String email) {
        // 调用 userService 中的方法检查邮箱是否已经存在
        TbMember emailExists = memberService.findMemberById(email);
        if (emailExists!=null) {
            return "该邮箱已被注册";
        } else {
            return "邮箱可用";
        }
    }

    @RequestMapping("/index/doRegister")
    @ResponseBody
    public String doRegister(@RequestParam("email") String email, @RequestParam("password") String password) {
        // 调用 userService 中的方法进行用户注册
        TbMember tbMember=new TbMember();
        tbMember.setEmail(email);
        tbMember.setPassword(MD5Util.md5(password));
        log.info("准备注册："+tbMember.getEmail()+", "+tbMember.getPassword());
        if(memberService.findMemberById(tbMember.getEmail())==null&&tbMember.getPassword()!=null){
            memberService.add(tbMember);
            log.info("已添加用户：" + tbMember.getEmail() + ", " + tbMember.getPassword());
            return "success";
        }else {
            return "注册失败";
        }

    }

    @RequestMapping("/index/logOut")
    public String logOut( HttpSession session){
        session.removeAttribute("memberLogin");
        return "redirect:/index";
    }



}
