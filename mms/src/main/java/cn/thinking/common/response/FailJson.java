package cn.thinking.common.response;

/**
 * FailJson
 *
 * @author Le Yuan
 * @date 2016/11/26
 */
public class FailJson implements ResponseInfor {
    private  String failJsonInfor;

    public FailJson() {
    }

    public FailJson(String failJsonInfor) {
        this.failJsonInfor = failJsonInfor;
    }

    public String getFailJsonInfor() {
        return failJsonInfor;
    }

    public void setFailJsonInfor(String failJsonInfor) {
        this.failJsonInfor = failJsonInfor;
    }
}
