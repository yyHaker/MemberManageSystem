package cn.com.core.member.service.impl;

import cn.com.core.member.entity.Member;
import cn.com.core.member.entity.MemberExample;
import cn.com.core.member.mapper.MemberMapper;
import cn.com.core.member.request.MemberPage;
import cn.com.core.member.utils.BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * MemberService
 *会员管理服务层
 * @author Le Yuan
 * @date 2016/10/21
 */
@Component
public class MemberService {
    @Resource  private MemberMapper memberMapper;

    /**
     * 根据主键id得到member对象
     * @param id
     * @return
     */
    public Member getMemberById(String id){
        return memberMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键id删除member
     * @param id
     * @return
     */
    public int deleteMemberById(String id){
         return memberMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据组合条件查询会员
     * @param member
     * @return
     */
    public MemberPage searchMembersListPage(Member member, int beginPageIndex, int pageSize){
        MemberExample memberExample=new MemberExample();
        MemberExample.Criteria criteria=memberExample.createCriteria();

         if (member.getId()!=null){
             criteria.andIdEqualTo(member.getId());
         }
         if (member.getUsername()!=null){
             criteria.andUsernameLike("%"+member.getUsername()+"%");
         }
         if (member.getSex()!=null){
             criteria.andSexEqualTo(member.getSex());
         }
         if (member.getBirthday()!=null){
             criteria.andBirthdayEqualTo(member.getBirthday());
         }
         if (member.getTelephone()!=null){
             criteria.andTelephoneLike(member.getTelephone()+"%");
         }
         if (member.getStoreId()!=null){
             criteria.andStoreIdEqualTo(member.getStoreId());
         }
         if (member.getLevel()!=null){
             criteria.andLevelEqualTo(member.getLevel());
         }
         if (member.getPoints()!=null){
             criteria.andPointsGreaterThan(member.getPoints());
         }
         if (member.getFlightCount()!=null){
             criteria.andFlightCountGreaterThan(member.getFlightCount());
         }
         if (member.getLastVisitedTime()!=null){
             criteria.andLastVisitedTimeBetween(member.getLastVisitedTime(),new Date());
         }
         if (member.getFlightTime()!=null){
             criteria.andFlightTimeGreaterThan(member.getFlightTime());
         }
         if (member.getConsumptionSum()!=null){
             criteria.andConsumptionSumGreaterThan(member.getConsumptionSum());
         }
         if (member.getBalance()!=null){
             criteria.andBalanceGreaterThan(member.getBalance());
         }
         //System.out.println(criteria.getAllCriteria());
        memberExample.setDistinct(true);

        PageHelper.startPage(beginPageIndex,pageSize);
        List<Member> memberList=memberMapper.selectByExample(memberExample);

        //得到pageInfor对象，包含所有分页信息
          PageInfo<Member> memberPageInfo=new PageInfo<Member>(memberList);


        return  new MemberPage(BeanUtils.toMemberJsonList(memberList),memberPageInfo);
    }

    /**
     * 添加和会员
     * @param member
     * @return
     */
    public int  addNewMember(Member member){
         int changeCount=0;
         if (member!=null){
             member.setCreateTime(new Date());
             member.setUpdateTime(new Date());
             member.setStoreId((int)(Math.random()*9)+"");
             member.setBalance(Float.parseFloat(0+""));
             member.setConsumptionSum(Float.parseFloat(0+""));
             member.setFlightTime(0);
             member.setLastVisitedTime(new Date());
             member.setFlightCount(0);
             member.setLevel(1+"");
             member.setPoints(0);
             memberMapper.insert(member);
         }
         return changeCount;
    }

    /**
     * 根据条件修改Member
     * @param member
     * @return
     */
    public int  updateOldMember(Member member){
          int  changeCount=0;
        if (member.getId()!=null){
           // Member oldMember=memberMapper.selectByPrimaryKey(member.getId());
            MemberExample memberExample=new MemberExample();
            MemberExample.Criteria criteria=memberExample.createCriteria();
             criteria.andIdEqualTo(member.getId());
             member.setUpdateTime(new Date());
             changeCount=memberMapper.updateByExampleSelective(member,memberExample);
           }
        return changeCount;
    }


}
