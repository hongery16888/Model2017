package iori.basecore.listener;

/**
 * Created by ThinkPad on 2017/11/2.
 */

public interface OnNormalNetworkListener<T> extends OnBaseNetworkListener {

    void success(T model);
    void error(String errorMsg);
}
