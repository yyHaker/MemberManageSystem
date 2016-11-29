package cn.thinking.common.response;

/**
 * SuccessJson
 *
 * @author Le Yuan
 * @date 2016/11/26
 */
public class SuccessJson  implements  ResponseInfor{
    private String successInfor;

    public SuccessJson() {
    }

    public SuccessJson(String successInfor) {
        this.successInfor = successInfor;
    }

    public String getSuccessInfor() {
        return successInfor;
    }

    public void setSuccessInfor(String successInfor) {
        this.successInfor = successInfor;
    }
}
