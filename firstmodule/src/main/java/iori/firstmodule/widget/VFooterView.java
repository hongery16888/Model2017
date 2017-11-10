package iori.firstmodule.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.IBottomView;

import iori.firstmodule.R;

/**
 * Created by ThinkPad on 2017/11/9.
 */

public class VFooterView extends LinearLayout implements IBottomView {

    private TextView textView;

    public VFooterView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.loading_header_view, this);

        textView = (TextView) findViewById(R.id.text);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingUp(float fraction, float maxBottomHeight, float bottomHeight) {
        textView.setText("----上拉加载----");
        textView.setScaleX(Math.abs(fraction));
        textView.setScaleY(Math.abs(fraction));
    }

    @Override
    public void onPullReleasing(float fraction, float maxBottomHeight, float bottomHeight) {
        textView.setScaleX(fraction);
        textView.setScaleY(fraction);
    }

    @Override
    public void startAnim(float maxBottomHeight, float bottomHeight) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void reset() {

    }
}
