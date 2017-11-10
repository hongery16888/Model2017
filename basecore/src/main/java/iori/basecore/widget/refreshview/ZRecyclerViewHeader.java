package iori.basecore.widget.refreshview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import iori.basecore.R;

/**
 * Created by user on 2017/6/7.
 */

public class ZRecyclerViewHeader extends LinearLayout {
    private LinearLayout mContainer;//布局指向
    public final static int STATE_NORMAL = 0;// 初始状态
    public final static int STATE_RELEASE_TO_REFRESH = 1;   // 释放刷新
    public final static int STATE_REFRESHING = 2;   // 正在刷新

    private int mState = STATE_NORMAL;  // 当前状态（临时保存用）

    public int mMeasuredHeight;//布局的原始高度，用于做状态改变的标志

    // 均匀旋转动画
    public ZRecyclerViewHeader(Context context) {
        super(context);
        initView(context);
    }

    public ZRecyclerViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ZRecyclerViewHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }
    //初始化
    private void initView(Context context) {
        mContainer= (LinearLayout) LayoutInflater.from(context).inflate(R.layout.recycler_loading_header_view,null);
        LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,0,0,0);
        this.setLayoutParams(lp);
        this.setPadding(0, 0, 0, 0);
        addView(mContainer, new LayoutParams(LayoutParams.MATCH_PARENT, 0));
        setGravity(Gravity.CENTER);

        measure(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        mMeasuredHeight=getMeasuredHeight();
    }
    //设置状态
    public void setState(int state){
        if (state==mState){
            return;
        }
        //判断动画的添加
        switch (state){
            case STATE_NORMAL:
                setVisibility(GONE);
                break;
            case STATE_RELEASE_TO_REFRESH:
                setVisibility(VISIBLE);
                break;
            case STATE_REFRESHING:
                setVisibility(VISIBLE);
                break;
            default:
                break;
        }
        mState=state;
    }
    //返回当前状态
    public int getState(){
        return mState;
    }
    //完成刷新
    public void refreshComplate(){
        //注释的是时间文本  有需要可以去掉  也是在布局里改可见属性
//        mHeaderTimeView.setText(friendlyTime(new Date()));
        smoothScrollTo(0);
        setState(STATE_NORMAL);
    }

    /**
     * 刷新头滑动改变
     */
    public void onMove(float dalta){
        if (getVisiableHeight()>0||dalta>0){
            setVisiableHeight((int)dalta+getVisiableHeight());
            if (mState<=STATE_RELEASE_TO_REFRESH){// 未处于刷新状态，更新箭头
                if (getVisiableHeight()>mMeasuredHeight){
                    setState(STATE_RELEASE_TO_REFRESH);
                }else {
                    setState(STATE_NORMAL);
                }
            }
        }
    }

    /**
     *释放刷新头的时候，是否满足刷新的要求
     */
    public boolean releaseAction() {
        boolean isOnRefresh = false;
        int height=getVisiableHeight();
        if (height==0){
            isOnRefresh=false;
        }
        //刷新时改变状态
        if (getVisiableHeight()>mMeasuredHeight&&mState<STATE_REFRESHING){
            setState(STATE_REFRESHING);
            isOnRefresh=true;
        }
        //刷新时回滚到原始高度
        int destHeight=0;
        if (mState==STATE_REFRESHING){
            destHeight=mMeasuredHeight;
        }
        smoothScrollTo(destHeight);
        return  isOnRefresh;
    }

    //回滚到顶部
    private void smoothScrollTo(int destHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(getVisiableHeight(), destHeight);
        animator.setDuration(300).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                setVisiableHeight((Integer) animation.getAnimatedValue());
            }
        });
        animator.start();
    }
    //设置刷新头可见的高度
    public void setVisiableHeight(int height) {
        if (height < 0)
            height = 0;
        LayoutParams lp = (LayoutParams) mContainer
                .getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }
    //获得刷新头可见的高度
    public int getVisiableHeight() {
        int height = 0;
        LayoutParams lp = (LayoutParams) mContainer
                .getLayoutParams();
        height = lp.height;
        return height;
    }
}