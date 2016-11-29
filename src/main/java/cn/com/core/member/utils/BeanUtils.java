package cn.com.core.member.utils;

import cn.com.core.member.request.MemberJson;
import cn.com.core.member.entity.Member;
import cn.com.core.member.request.MemberJson;
import cn.thinking.common.util.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * BeanUtils
 *提供bean的转换方法
 * @author Le Yuan
 * @date 2016/10/22
 */
public class BeanUtils {
    /**
     * 将MemberJson对象转换成Member对象
     * @param memberJson
     * @return
     */
    public static Member  toMember(MemberJson memberJson){
        Member member=new Member();
        if (memberJson.getId()!=null&&memberJson.getId()!=""){
            member.setId(memberJson.getId());
        }
        if (memberJson.getUsername()!=null&&memberJson.getUsername()!=""){
            member.setUsername(memberJson.getUsername());
        }
        if (memberJson.getPassword()!=null&&memberJson.getPassword()!=""){
            member.setPassword(memberJson.getPassword());
        }
        if (memberJson.getStoreId()!=null&&memberJson.getStoreId()!=""){
            member.setStoreId(memberJson.getStoreId());
        }
        if (memberJson.getSex()!=null&&memberJson.getSex()!=""){
            member.setSex(memberJson.getSex());
        }
        if (memberJson.getBirthday()!=null&&memberJson.getBirthday()!=""){
            member.setBirthday(Tools.str2Date(memberJson.getBirthday(),"yyyy-MM-dd"));
        }
        if (memberJson.getTelephone()!=null&&memberJson.getTelephone()!=""){
            member.setTelephone(memberJson.getTelephone());
        }
        return  member;
    }

    /**
     * 将Member对象转换成MemberJson
     * @param member
     * @return
     */
    public static  MemberJson toMemberJson(Member member){
        MemberJson memberJson=new MemberJson();

        memberJson.setId(member.getId());
        memberJson.setUsername(member.getUsername());
        memberJson.setPassword(member.getPassword());
        memberJson.setSex(member.getSex());
        memberJson.setBirthday(Tools.date2Str(member.getBirthday(),"yyyy-MM-dd"));
        memberJson.setTelephone(member.getTelephone());

        memberJson.setStoreId(member.getStoreId());
        memberJson.setLevel(member.getLevel());
        memberJson.setPoints(member.getPoints());
        memberJson.setFlightCount(member.getFlightCount());
        memberJson.setLastVisitedTime(Tools.date2Str(member.getLastVisitedTime(),"yyyy-MM-dd HH:mm:ss"));
        memberJson.setFlightTime(member.getFlightTime());
        memberJson.setConsumptionSum(member.getConsumptionSum());
        memberJson.setBalance(member.getBalance());

        memberJson.setCreateTime(Tools.date2Str(member.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        memberJson.setUpdateTime(Tools.date2Str(member.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
        return memberJson;
    }

    /**
     * 将List<Member>转换成List<MemberJson>
     * @param memberList
     * @return
     */
    public static List<MemberJson> toMemberJsonList(List<Member> memberList){
        List<MemberJson> memberJsonList=new ArrayList<MemberJson>();
        for (Member member:memberList){
            MemberJson memberJson=toMemberJson(member);
            memberJsonList.add(memberJson);
        }
        return memberJsonList;
    }
}
