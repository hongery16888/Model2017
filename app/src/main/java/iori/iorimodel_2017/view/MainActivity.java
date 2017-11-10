package iori.iorimodel_2017.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

import iori.basecore.base.BaseActivity;
import iori.basecore.constant.RouteConstantPath;
import iori.basecore.listener.OnBaseNetworkListener;
import iori.iorimodel_2017.BR;
import iori.iorimodel_2017.R;
import iori.iorimodel_2017.databinding.ActivityMainBinding;
import iori.iorimodel_2017.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    public static void launch(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel createViewModel() {
        return new MainViewModel(this);
    }

    @Override
    protected int getVariableId() {
        return BR.mvm;
    }

    @Override
    protected OnBaseNetworkListener getNetworkListener() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParas();
        setListener();
    }

    private void initParas(){
        viewModel.getVersionCode();
    }

    private void setListener(){
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouteConstantPath.FristAct).navigation(MainActivity.this);
            }
        });
    }
}
