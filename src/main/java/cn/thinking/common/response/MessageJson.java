package cn.thinking.common.response;

/**
 * MessageJson
 *服务器返回客户端的json数据信息
 * @author Le Yuan
 * @date 2016/11/27
 */
public class MessageJson {
    private String msg;

    public MessageJson() {
    }

    public MessageJson(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
