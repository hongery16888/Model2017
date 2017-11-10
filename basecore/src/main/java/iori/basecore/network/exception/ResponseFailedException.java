package iori.basecore.network.exception;

/**
 * Created by ThinkPad on 2017/11/2.
 */

public class ResponseFailedException extends Throwable {

    private RxError rxError;
    public ResponseFailedException(){}
    public ResponseFailedException(int errorCode,String mes)
    {
        rxError=new RxError(errorCode,mes);
    }
    public ResponseFailedException(int errorCode,String mes,Object data)
    {
        rxError=new RxError(errorCode,mes,data);
    }
    public RxError getRxError() {
        return this.rxError;
    }
}
