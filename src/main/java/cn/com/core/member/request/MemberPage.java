package cn.com.core.member.request;

import cn.com.core.member.entity.Member;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * MemberPage
 *
 * @author Le Yuan
 * @date 2016/10/24
 */

public class MemberPage {
    private List<MemberJson> memberJsonList;
    private PageInfo<Member> pageInfo;

    public MemberPage() {
    }

    public MemberPage(List<MemberJson> memberJsonList, PageInfo<Member> pageInfo) {
        this.memberJsonList = memberJsonList;
        this.pageInfo = pageInfo;
    }

    public List<MemberJson> getMemberJsonList() {
        return memberJsonList;
    }

    public void setMemberJsonList(List<MemberJson> memberJsonList) {
        this.memberJsonList = memberJsonList;
    }

    public PageInfo<Member> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<Member> pageInfo) {
        this.pageInfo = pageInfo;
    }
}

