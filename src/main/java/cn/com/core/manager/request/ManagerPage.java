package cn.com.core.manager.request;

import cn.com.core.manager.entity.Manager;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * ManagerPage
 *
 * @author Le Yuan
 * @date 2016/12/2
 */
public class ManagerPage {
    private List<ManagerJson> managerJsonList;
    private PageInfo<Manager> managerPageInfo;

    public ManagerPage() {
    }

    public ManagerPage(List<ManagerJson> managerJsonList, PageInfo<Manager> managerPageInfo) {
        this.managerJsonList = managerJsonList;
        this.managerPageInfo = managerPageInfo;
    }

    public List<ManagerJson> getManagerJsonList() {
        return managerJsonList;
    }

    public void setManagerJsonList(List<ManagerJson> managerJsonList) {
        this.managerJsonList = managerJsonList;
    }

    public PageInfo<Manager> getManagerPageInfo() {
        return managerPageInfo;
    }

    public void setManagerPageInfo(PageInfo<Manager> managerPageInfo) {
        this.managerPageInfo = managerPageInfo;
    }
}
