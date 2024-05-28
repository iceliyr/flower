package cn.edu.scnu.controller;

import cn.edu.scnu.entity.TbMember;
import cn.edu.scnu.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;


//    @RequestMapping("/queryMember/{ticket}")
//    @ResponseBody
//    public TbMember checkLoginUser(@PathVariable("ticket") String ticket){
//        log.info("确认登录：ticket="+ticket);
//        TbMember member=memberService.queryByTicket(ticket);
//        if(member!=null){
//            return member;
//        }else {
//            return null;
//        }
//    }
}
