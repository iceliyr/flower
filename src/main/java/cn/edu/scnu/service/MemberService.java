package cn.edu.scnu.service;

import cn.edu.scnu.common.MD5Util;
import cn.edu.scnu.entity.TbMember;
import cn.edu.scnu.mapper.MemberMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Slf4j
@Service
public class MemberService extends ServiceImpl<MemberMapper, TbMember> {
   @Autowired
   private MemberMapper memberMapper;
   @Autowired
   private RedisTemplate redisTemplate;

   public void add(TbMember tbMember) {
      memberMapper.insert(tbMember);
   }
   public TbMember login(String email,String password) {
      // 进入数据库查询
      TbMember member=memberMapper.selectById(email);
      if(member.getPassword().equals(MD5Util.md5(password))) {
         return member;
      }else{
         return null;
      }
   }
//   public TbMember queryByTicket(String ticket){
//      TbMember member=(TbMember)redisTemplate.opsForValue().get(ticket);
//      Long expireTime=redisTemplate.getExpire(ticket);
//      if(expireTime<1000*60*10l)
//         redisTemplate.opsForValue().set(ticket,member,expireTime+5*60*1000,TimeUnit.MILLISECONDS);
//      return member;
//   }
//   public void logOut(String ticket){
//      redisTemplate.delete(ticket);
//   }


   public TbMember findMemberById(String email) {
      return memberMapper.selectById(email);
   }

}
