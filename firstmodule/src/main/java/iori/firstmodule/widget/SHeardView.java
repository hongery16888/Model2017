package iori.firstmodule.widget;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

import iori.firstmodule.R;

/**
 * Created by ThinkPad on 2017/11/9.
 */

public class SHeardView extends LinearLayout implements RefreshHeader {

    private TextView textView;

    public SHeardView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.loading_header_view, this);

        textView = (TextView) findViewById(R.id.text);
    }

    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {
        textView.setScaleX(percent);
        textView.setScaleY(percent);
    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {
        textView.setScaleX(percent);
        textView.setScaleY(percent);
    }

    @Override
    public void onRefreshReleased(RefreshLayout layout, int headerHeight, int extendHeight) {

    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(@ColorInt int... colors) {

    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {

    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        return 0;
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                textView.setText("下拉开始刷新");
                break;
            case Refreshing:
                textView.setText("正在刷新");
                break;
            case ReleaseToRefresh:
                textView.setText("释放立即刷新");
                break;
        }
    }
}
