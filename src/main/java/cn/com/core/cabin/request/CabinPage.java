package cn.com.core.cabin.request;

import cn.com.core.cabin.entity.Cabin;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * CabinPage
 *
 * @author Le Yuan
 * @date 2016/11/6
 */
public class CabinPage {
    List<CabinJson> cabinJsonList;
    PageInfo<Cabin> cabinPageInfo;

    public CabinPage() {
    }

    public CabinPage(List<CabinJson> cabinJsonList, PageInfo<Cabin> cabinPageInfo) {
        this.cabinJsonList = cabinJsonList;
        this.cabinPageInfo = cabinPageInfo;
    }

    public List<CabinJson> getCabinJsonList() {
        return cabinJsonList;
    }

    public void setCabinJsonList(List<CabinJson> cabinJsonList) {
        this.cabinJsonList = cabinJsonList;
    }

    public PageInfo<Cabin> getCabinPageInfo() {
        return cabinPageInfo;
    }

    public void setCabinPageInfo(PageInfo<Cabin> cabinPageInfo) {
        this.cabinPageInfo = cabinPageInfo;
    }
}
