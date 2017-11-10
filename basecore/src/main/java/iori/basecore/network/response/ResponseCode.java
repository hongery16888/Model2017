package iori.basecore.network.response;

/**
 * Created by shendawei on 17/2/8.
 */

public class ResponseCode {
    //200 临时成功code
    public static final int SUCCESS = 1;
    public static final int NON_LOGIN = 401;
    //503 暂停服务中
    public static final int NON_SERVICE = 503;
    //505 需要强制升级客户端
    public static final int MANDATORY_UPDATE = 505;

}
