package iori.basecore.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import iori.basecore.listener.OnBaseNetworkListener;

/**
 * Created by yuzhe on 2017/4/7.
 */

public abstract class BaseActivity<B extends ViewDataBinding, VM extends BaseViewModel> extends RxAppCompatActivity {

    protected ImmersionBar mImmersionBar;
    protected B binding;
    protected VM viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, getLayoutId());
        initWindow();
        loadParas();
        viewModel = viewModel == null ? createViewModel() : viewModel;
        if(getVariableId()!=0)
        {
            binding.setVariable(getVariableId(),viewModel);
            binding.executePendingBindings();
        }
        if(viewModel!=null) {
            viewModel.create();
            if (getNetworkListener() != null){
                viewModel.setOnNetworkListener(getNetworkListener());
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(viewModel!=null) {
            viewModel.onDestroy();
        }
        if(mImmersionBar!=null)
        {
            mImmersionBar.destroy();
        }

    }

    protected abstract int getLayoutId();

    protected abstract VM createViewModel();

    protected abstract int getVariableId();

    protected abstract OnBaseNetworkListener getNetworkListener();

    protected void loadParas()
    {

    }

    protected void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    protected void initWindow()
    {
        mImmersionBar=ImmersionBar.with(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
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
