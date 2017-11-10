package iori.basecore;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by ThinkPad on 2017/11/1.
 */

public class ModelApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.init(this);
    }
}
