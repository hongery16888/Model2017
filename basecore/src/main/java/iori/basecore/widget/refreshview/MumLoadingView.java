package iori.basecore.widget.refreshview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import iori.basecore.R;

/**
 * Created by user on 2017/5/24.
 */

public class MumLoadingView extends View {

    private static final String TAG = MumLoadingView.class.getSimpleName();
    private static int BLUE_COLOR_STYLE = 1;
    private static int GREY_COLOR_STYLE = 0;
    /**
     * view宽度
     */
    private int width = 80;
    /**
     * view高度
     */
    private int height = 80;
    /**
     * 菊花的矩形的宽
     */
    private int widthRect;
    /**
     * 菊花的矩形的宽
     */
    private int heigheRect;
    /**
     * 菊花绘制画笔
     */
    private Paint rectPaint;
    /**
     * 循环绘制位置
     */
    private int pos = 0;
    /**
     * 菊花矩形
     */
    private Rect rect;
    /**
     * 循环颜色
     */
    private String[] color = {"#6C7B7E", "#7B878B", "#889498", "#97A1A6", "#9FA8AC", "#ABB3B7",
            "#B6BEC0", "#C0C7C9", "#C8CED0", "#D3D6D9", "#DCDFE0", "#EBEDEE"};

    public MumLoadingView(Context context) {
        super(context);
        init();
    }

    public MumLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MumLoadingView);
        width = (int)a.getDimension(R.styleable.MumLoadingView_progressWidth, 80);
        height = (int)a.getDimension(R.styleable.MumLoadingView_progressHeight, 80);
        if (a.getInt(R.styleable.MumLoadingView_colorStyle, 0) == BLUE_COLOR_STYLE){
            color = new String[] {"#1DA3DB", "#23A8DD", "#2BAFE1", "#35B5E5", "#3CB9E7", "#43BDEA",
                    "#4DC3EE", "#56C7EF", "#5FCBF3", "#67D0F5", "#71D5F8", "#7BDAFB"};
        }
        init();
    }


    private void init() {
        rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthRect = width / 12;   //菊花矩形的宽
        heigheRect = 30 * widthRect / 10;  //菊花矩形的高
        rectPaint.setStrokeWidth(widthRect);
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制部分是关键了，菊花花瓣矩形有12个，我们不可能去一个一个的算出所有的矩形坐标，我们可以考虑
        //旋转下面的画布canvas来实现绘制，每次旋转30度
        //首先定义一个矩形
        if (rect == null) {
            rect = new Rect((width - widthRect) / 2, 0, (width + widthRect) / 2, heigheRect);
        }

        for (int i = 0; i < 12; i++) {
            rectPaint.setColor(Color.parseColor(color[(color.length - i + pos)%color.length]));
            canvas.drawLine(width/2, 0, width/2, heigheRect, rectPaint);
            canvas.rotate(30, width / 2, width / 2);    //旋转
        }

        pos++;
        if (pos > 11) {
            pos = 0;
        }

        postInvalidateDelayed(100);  //一个周期用时1200

    }

//    public void setStartAnimal() {
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 12);
//        valueAnimator.setDuration(1500);
//        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                pos = (int) animation.getAnimatedValue();
//                Log.d(TAG, "pos:" + pos);
//                invalidate();
//            }
//        });
//        valueAnimator.start();
//    }

}