package cn.com.core.member.controller;


import cn.com.core.member.mapper.MemberMapper;
import cn.com.core.member.request.MemberJson;
import cn.com.core.member.request.MemberPage;
import cn.com.core.member.service.impl.MemberService;
import cn.com.core.member.utils.BeanUtils;
import cn.com.core.member.entity.Member;
import cn.com.core.member.mapper.MemberMapper;
import cn.com.core.member.request.MemberJson;
import cn.com.core.member.request.MemberPage;
import cn.com.core.member.service.impl.MemberService;
import cn.com.core.member.utils.BeanUtils;
import cn.thinking.common.baseeditor.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 会员管理controller层
 * MemberInforManage
 *会员信息管理，主要实现会员的增删改查
 * 查询：
 * 增加：
 * 更改：
 * 删除：
 * @author Le Yuan
 * @date 2016/10/18
 */

@Controller
@RequestMapping("/Member")
public class MemberInforManage extends BaseController {

    @Autowired
    private MemberService memberService;


    /**
     * 根据主键id得到member
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMember/{id}")
    public MemberJson getMemberById(@PathVariable("id") String id){
         Member member=memberService.getMemberById(id);
         // return  JsonUtil.objectToJsonString(member);
        return  BeanUtils.toMemberJson(member);
    }

    /**
     * 根据主键id删除member
     * @param id
     * @return
     */
    @RequestMapping("/deleteMember/{id}")
    public void  deleteMemberById(@PathVariable("id") String id){
             int deleteCount= memberService.deleteMemberById(id);
    }

    /**
     * 添加新的会员
     */

    @ResponseBody
    @RequestMapping(value="/addNewMember",method= RequestMethod.POST)
    public Member  addNewMember(@RequestBody MemberJson memberJson) throws IOException{
          Member member=BeanUtils.toMember(memberJson);
        Member memberExits = memberService.getMemberById(member.getId());
        if(memberExits != null){
            return memberExits;
        }
        memberService.addNewMember(member);
        return null;
    }

    /**
     * 更改会员信息
     * @throws IOException
     */
     @RequestMapping(value = "/updateMember",method=RequestMethod.POST)
     public void updateOldMember(@RequestBody MemberJson memberJson) throws  IOException{
           Member member=BeanUtils.toMember(memberJson);
           int chnageCount=memberService.updateOldMember(member);
     }
   /* *//*
     * 根据组合条件查询会员信息
     *
     * 此处的ResponseBody注解将返回的bean封装为json返回给客户端
     * @return
     * @throws IOException
     *//*
      @ResponseBody
      @RequestMapping(value="/searchMember",method=RequestMethod.POST)
       public List<MemberJson> searchMembers(@RequestBody MemberJson memberJson) throws  IOException{
          //将memberJson转换成member，注意日期转换
          //调用service.searchMembers(member)
          //返回List<MemberJson>
           Member member= BeanUtils.toMember(memberJson);
           List<Member> memberList=memberService.searchMembers(member);
          return BeanUtils.toMemberJsonList(memberList);
      }*/


    /*  @RequestMapping("/addData")
      public void addData() {
          for (int i = 2; i <=100; i++) {
              memberMapper.insert(new Member(i+"", GenerateDataHelper.produceName(5),
                      GenerateDataHelper.produceNumbers(8),GenerateDataHelper.produceSex(),GenerateDataHelper.prduceDate(),
                      GenerateDataHelper.produceNumbers(11),GenerateDataHelper.produceNumbers(1),GenerateDataHelper.produceNumbers(1),
                      Integer.parseInt(GenerateDataHelper.produceNumbers(3)),Integer.parseInt(GenerateDataHelper.produceNumbers(2)),GenerateDataHelper.prduceDate(),
                      Integer.parseInt(GenerateDataHelper.produceNumbers(3)),Float.parseFloat(GenerateDataHelper.produceNumbers(3)),Float.parseFloat(GenerateDataHelper.produceNumbers(3)),
                      GenerateDataHelper.prduceDate(),GenerateDataHelper.prduceDate()));
          }
      }*/



    /**
     * 根据组合条件查询会员信息,并实现分页
     *
     * 此处的ResponseBody注解将返回的bean封装为json返回给客户端
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value="/searchMembersListPage",method=RequestMethod.POST)
    public MemberPage searchMembersListPage(@RequestBody MemberJson memberJson) throws  IOException{
        //将memberJson转换成member，注意日期转换
        //调用service.searchMembers(member)
        //返回List<MemberJson>
        Member member= BeanUtils.toMember(memberJson);
        return memberService.searchMembersListPage(member,memberJson.getBeginPageIndex(),memberJson.getPageSize());
    }



}

