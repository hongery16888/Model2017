package iori.firstmodule.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.LinkedList;
import java.util.List;

import iori.basecore.base.BaseActivity;
import iori.basecore.listener.OnBaseNetworkListener;
import iori.basecore.listener.OnMultiplyNetworkListener;
import iori.basecore.listener.OnNormalNetworkListener;
import iori.basecore.model.translate.TranslateModel;
import iori.basecore.widget.refreshview.RecycleViewDivider;
import iori.basecore.widget.refreshview.ZRecyclerView;
import iori.firstmodule.R;
import iori.firstmodule.adapter.VBTopAdapter;
import iori.firstmodule.adapter.VBottomAdapter;
import iori.firstmodule.adapter.VMidAdapter;
import iori.firstmodule.adapter.VTopAdapter;
import iori.firstmodule.databinding.ThirdActivityBinding;
import iori.firstmodule.viewmodel.ThirdViewModel;
import iori.firstmodule.widget.VFooterView;
import iori.firstmodule.widget.VHeardView;

/**
 * Created by ThinkPad on 2017/11/3.
 */

public class ThirdActivity extends BaseActivity<ThirdActivityBinding, ThirdViewModel>
        implements OnMultiplyNetworkListener<TranslateModel, TranslateModel, List<TranslateModel>>, ZRecyclerView.LoadingListener {

    private VTopAdapter topAdapter;
    private VMidAdapter midAdapter;
    private VBottomAdapter bottomAdapter;

    public static void launch(Context context){
        context.startActivity(new Intent(context, ThirdActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.third_activity;
    }

    @Override
    protected ThirdViewModel createViewModel() {
        return new ThirdViewModel(this);
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
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(delegateAdapter);
        binding.recyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 2, Color.BLACK));
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        topAdapter = new VTopAdapter(this);
        midAdapter = new VMidAdapter(this, null);
        bottomAdapter = new VBottomAdapter(this);
        adapters.add(midAdapter);
        adapters.add(topAdapter);
        adapters.add(bottomAdapter);
        delegateAdapter.setAdapters(adapters);

        viewModel.getTranslateForTop();
        viewModel.getTranslateForMid();
        viewModel.getTranslateForBottom();

        binding.swipRefresh.setHeaderView(new VHeardView(this));
        binding.swipRefresh.setBottomView(new VFooterView(this));
    }

    private void setListener(){
//        binding.recyclerView.setLoadingListener(this);

        binding.swipRefresh.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
               viewModel.getTranslateForTop();
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                viewModel.getTranslateForBottom();
            }
        });
    }

    @Override
    public void onRefresh() {
//        binding.recyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
//        binding.recyclerView.loadMoreComplete();
    }

    @Override
    public void successForTop(TranslateModel model) {
        topAdapter.getItems().add(model);
        binding.swipRefresh.finishRefreshing();
    }

    @Override
    public void successForMid(TranslateModel model) {
        midAdapter.refreshData(model);
    }

    @Override
    public void successForBottom(List<TranslateModel> model) {
        bottomAdapter.getItems().addAll(model);
        binding.swipRefresh.finishLoadmore();
    }

    @Override
    public void error(String errorMsg) {

    }
}
