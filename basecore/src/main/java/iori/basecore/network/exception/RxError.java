package iori.basecore.network.exception;

/**
 * Created by yuzhe on 2017/5/16.
 */

public class RxError {
    private int errorCode;
    private String mes;
    private Object data;
    public RxError(int errorCode, String mes)
    {
        this.errorCode=errorCode;
        this.mes=mes;
    }
    public RxError(int errorCode, String mes, Object data)
    {
        this.errorCode=errorCode;
        this.mes=mes;
        this.data=data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() throws Throwable {
        if(data==null)
            throw new DataNullException("data is null");
        else{
            return data;
        }
    }
}
