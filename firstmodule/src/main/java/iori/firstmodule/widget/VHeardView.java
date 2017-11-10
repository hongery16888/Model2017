package iori.firstmodule.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;

import iori.firstmodule.R;

/**
 * Created by ThinkPad on 2017/11/9.
 */

public class VHeardView extends LinearLayout implements IHeaderView {

    private TextView textView;

    public VHeardView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.loading_header_view, this);

        textView = (TextView) findViewById(R.id.text);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        textView.setScaleX(fraction);
        textView.setScaleY(fraction);
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        textView.setScaleX(fraction);
        textView.setScaleY(fraction);
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {

    }

    @Override
    public void onFinish(OnAnimEndListener animEndListener) {
        animEndListener.onAnimEnd();
    }

    @Override
    public void reset() {

    }
}
