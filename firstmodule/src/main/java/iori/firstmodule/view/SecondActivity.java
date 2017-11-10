package iori.firstmodule.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import iori.basecore.base.BaseActivity;
import iori.basecore.listener.OnBaseNetworkListener;
import iori.basecore.listener.OnNormalNetworkListener;
import iori.basecore.model.translate.TranslateModel;
import iori.basecore.widget.refreshview.RecycleViewDivider;
import iori.basecore.widget.refreshview.ZRecyclerView;
import iori.firstmodule.R;
import iori.firstmodule.adapter.SecondAdapter;
import iori.firstmodule.databinding.SecondActivityBinding;
import iori.firstmodule.viewmodel.SecondViewModel;

/**
 * Created by ThinkPad on 2017/11/3.
 */

public class SecondActivity extends BaseActivity<SecondActivityBinding, SecondViewModel> implements OnNormalNetworkListener<TranslateModel>, ZRecyclerView.LoadingListener{

    private SecondAdapter adapter;
    private boolean isRefresh = false;

    public static void launch(Context context){
        context.startActivity(new Intent(context, SecondActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.second_activity;
    }

    @Override
    protected SecondViewModel createViewModel() {
        return new SecondViewModel(this);
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    protected OnBaseNetworkListener getNetworkListener() {
        return this;
    }

    @Override
    protected void initWindow() {
        super.initWindow();
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.white).statusBarDarkFont(true, 0.5f).init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParas();
        setListener();
    }

    private void initParas() {
        adapter = new SecondAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 1, Color.BLACK));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLoadingMoreEnabled(true);

        viewModel.getTranslate();
    }

    private void setListener(){
        binding.recyclerView.setLoadingListener(this);
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        viewModel.getTranslate();
    }

    @Override
    public void onLoadMore() {
        isRefresh = false;
        viewModel.getTranslate();
    }

    @Override
    public void success(TranslateModel model) {
        if (isRefresh)  adapter.getItems().clear();
        adapter.getItems().add(model);
        binding.recyclerView.refreshComplete();
        binding.recyclerView.loadMoreComplete();
    }

    @Override
    public void error(String errorMsg) {
        showToast(errorMsg);
        binding.recyclerView.refreshComplete();
        binding.recyclerView.loadMoreComplete();
    }
}
