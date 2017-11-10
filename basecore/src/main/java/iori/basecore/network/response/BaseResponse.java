package iori.basecore.network.response;

/**
 * Created by ThinkPad on 2017/11/2.
 */

public class BaseResponse<T> {

    private int status;
    private T content;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}