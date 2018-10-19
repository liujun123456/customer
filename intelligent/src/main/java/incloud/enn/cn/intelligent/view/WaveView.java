package incloud.enn.cn.intelligent.view;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import incloud.enn.cn.commonlib.utils.ScreenUtils;

/**
 * @author AsureLiu on 2017/12/15.
 */

public class WaveView extends View {
    private Context mContext;
    private Paint paint;
    private Paint paint2;
    private Paint paint3;
    private int mWidth;
    private int mHeight;
    private int offset;
    private int offset2;
    private int offset3;
    private ValueAnimator animator;
    private ValueAnimator animator2;
    private ValueAnimator animator3;
    public WaveView(Context context) {
        super(context,null);
        mContext=context;
        initPaint();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        mContext=context;
        initPaint();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initPaint();
    }
    private void initPaint(){
        paint= new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#f8f8f8"));
        paint.setStrokeWidth(5);

        paint2= new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.parseColor("#88819bea"));
        paint2.setStrokeWidth(5);

        paint3= new Paint();
        paint3.setAntiAlias(true);
        paint3.setStyle(Paint.Style.FILL);
        paint3.setColor(Color.parseColor("#819bea"));
        paint3.setStrokeWidth(5);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path=new Path();
        path.moveTo(-mWidth*4/3+offset-mWidth/2,mHeight/2);
        path.quadTo(-mWidth+offset-mWidth/2,0,-mWidth*2/3+offset-mWidth/2,mHeight/2);
        path.quadTo(-mWidth/3+offset-mWidth/2,mHeight,offset-mWidth/2,mHeight/2);
        path.quadTo(mWidth/3+offset-mWidth/2,0,mWidth*2/3+offset-mWidth/2,mHeight/2);
        path.quadTo(mWidth+offset-mWidth/2,mHeight,mWidth*4/3+offset-mWidth/2,mHeight/2);
        path.lineTo(mWidth, mHeight);
        path.lineTo(0, mHeight);
        path.close();
        canvas.drawPath(path,paint3);

        path.reset();
        path.moveTo(-mWidth*4/3-100+offset2,mHeight/2);
        path.quadTo(-mWidth+offset2-100,mHeight,-mWidth*2/3+offset2-100,mHeight/2);
        path.quadTo(-mWidth/3+offset2-100,0,offset2-100,mHeight/2);
        path.quadTo(mWidth/3+offset2-100,mHeight,mWidth*2/3+offset2-100,mHeight/2);
        path.quadTo(mWidth+offset2-100,0,mWidth*4/3+offset2-100,mHeight/2);
        path.lineTo(mWidth, mHeight);
        path.lineTo(0, mHeight);
        path.close();
        canvas.drawPath(path,paint2);

        path.reset();
        path.moveTo(-mWidth*4/3+offset3,mHeight/2);
        path.quadTo(-mWidth+offset3,mHeight,-mWidth*2/3+offset3,mHeight/2);
        path.quadTo(-mWidth/3+offset3,0,offset3,mHeight/2);
        path.quadTo(mWidth/3+offset3,mHeight,mWidth*2/3+offset3,mHeight/2);
        path.quadTo(mWidth+offset3,0,mWidth*4/3+offset3,mHeight/2);
        path.lineTo(mWidth, mHeight);
        path.lineTo(0, mHeight);
        path.close();
        canvas.drawPath(path,paint);
    }
    public void start(){
        firstAnimator();
        secondAnimator();
        ThirdAnimator();
    }
    private void firstAnimator(){
        animator=ValueAnimator.ofInt(0, ScreenUtils.getScreenWidth(mContext)*4/3);
        animator.setDuration(4000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset=(int)animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }
    private void secondAnimator(){
        animator2=ValueAnimator.ofInt(0,ScreenUtils.getScreenWidth(mContext)*4/3);
        animator2.setDuration(3000);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset2=(int)animation.getAnimatedValue();
                invalidate();
            }
        });
        animator2.setInterpolator(new LinearInterpolator());
        animator2.setRepeatCount(100);
        animator2.setRepeatCount(ValueAnimator.INFINITE);
        animator2.setRepeatMode(ValueAnimator.RESTART);
        animator2.start();
    }
    private void ThirdAnimator(){
        animator3=ValueAnimator.ofInt(0, ScreenUtils.getScreenWidth(mContext)*4/3);
        animator3.setDuration(2000);
        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset3=(int)animation.getAnimatedValue();
                invalidate();
            }
        });
        animator3.setInterpolator(new LinearInterpolator());
        animator3.setRepeatCount(100);
        animator3.setRepeatCount(ValueAnimator.INFINITE);
        animator3.setRepeatMode(ValueAnimator.RESTART);
        animator3.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animator3!=null){
            animator3.cancel();
        }
        if (animator2!=null){
            animator2.cancel();
        }
        if (animator!=null){
            animator.cancel();
        }
    }
}
