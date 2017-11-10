package iori.basecore.base;

import android.databinding.BaseObservable;
import android.util.Log;

import iori.basecore.listener.OnBaseNetworkListener;

/**
 * Created by yuzhe on 2017/4/8.
 */

public abstract class BaseViewModel<T> extends BaseObservable {

    protected T onNetworkListener;

    public void create()
    {

    }

    public void onDestroy()
    {

    }

    public void setOnNetworkListener(T onNetworkListener) {
        this.onNetworkListener = onNetworkListener;
    }

    protected void logv(String str)
    {
        String tag=getClass().getSimpleName();
        Log.v(tag,str);
    }

    protected void loge(String str)
    {
        String tag=getClass().getSimpleName();
        Log.e(tag,str);
    }

    protected void logi(String str)
    {
        String tag=getClass().getSimpleName();
        Log.i(tag,str);
    }

    protected void logd(String str)
    {
        String tag=getClass().getSimpleName();
        Log.d(tag,str);
    }

}
