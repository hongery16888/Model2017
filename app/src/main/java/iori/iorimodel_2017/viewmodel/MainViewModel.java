package iori.iorimodel_2017.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

import iori.basecore.base.BaseViewModel;

/**
 * Created by ThinkPad on 2017/11/1.
 */

public class MainViewModel extends BaseViewModel {

    private Context context;
    public ObservableField<String> title;

    public MainViewModel(Context context) {
        this.context = context;
        title = new ObservableField<>("默认测试！！");
    }


    public void getVersionCode(){
        title.set("我只是先测试测试！！");
    }

}
