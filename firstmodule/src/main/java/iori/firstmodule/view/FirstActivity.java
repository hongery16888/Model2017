package iori.firstmodule.view;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import iori.basecore.base.BaseActivity;
import iori.basecore.constant.RouteConstantPath;
import iori.basecore.listener.OnBaseNetworkListener;
import iori.firstmodule.BR;
import iori.firstmodule.R;
import iori.firstmodule.databinding.FirstActivityBinding;
import iori.firstmodule.viewmodel.FirstViewModel;

/**
 * Created by ThinkPad on 2017/11/1.
 */
@Route(path = RouteConstantPath.FristAct)
public class FirstActivity extends BaseActivity<FirstActivityBinding, FirstViewModel>{

    @Override
    protected int getLayoutId() {
        return R.layout.first_activity;
    }

    @Override
    protected FirstViewModel createViewModel() {
        return new FirstViewModel(this);
    }

    @Override
    protected int getVariableId() {
        return BR.tsm;
    }

    @Override
    protected OnBaseNetworkListener getNetworkListener() {
        return null;
    }

    @Override
    protected void initWindow() {
        super.initWindow();
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.5f).init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParas();
        setListener();
    }

    private void initParas(){
        viewModel.getTranslate();
    }

    private void setListener(){
        binding.refreshActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondActivity.launch(FirstActivity.this);
            }
        });

        binding.multiplyLayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdActivity.launch(FirstActivity.this);
            }
        });

        binding.smartLayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FourthActivity.launch(FirstActivity.this);
            }
        });
    }
}
