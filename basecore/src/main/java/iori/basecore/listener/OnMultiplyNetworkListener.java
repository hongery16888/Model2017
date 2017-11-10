package iori.basecore.listener;

/**
 * Created by ThinkPad on 2017/11/3.
 */

public interface OnMultiplyNetworkListener<T, V, B> extends OnBaseNetworkListener {

    void successForTop(T model);
    void successForMid(V model);
    void successForBottom(B model);
    void error(String errorMsg);

}
