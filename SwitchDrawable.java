package com.visionet.dazhongcx.chuz.widget;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;

import com.visionet.dazhongcx.chuz.R;
import com.visionet.dazhongcx.chuz.utils.ResourceUtils;

/**
 * <font color='#9B77B2'>该类的主要用途:</font><br/><font color='#36FC2C'><b>
 * <p></p>
 * <b/></font><br/><hr/>
 * <b><font color='#05B8FD'>作者: C&C</font></b><br/><br/>
 * <b><font color='#05B8FD'>创建时间：2018/3/9</font></b><br/><br/>
 * <b><font color='#05B8FD'>联系方式：862530304@qq.com</font></b>
 */
public class SwitchDrawable extends Drawable {
    private static final int ANIMATION_TIME=100;
    private Paint mRectPaint;
    private Paint mCirclePaint;
    private RectF mRect=new RectF();
    private boolean isCheck=false;
    private float mCircleX;
    private int mRectColor;
    private int mRectEndColor;
    private int mHeight;
    public SwitchDrawable(){


        //绘制矩形
        mRectPaint=new Paint();
        mRectColor=ResourceUtils.getColor(R.color.switch_border);
        mRectEndColor=ResourceUtils.getColor(R.color.default_blue_start);
        mRectPaint.setColor(mRectColor);
        mRectPaint.setAntiAlias(true);
        //绘制圆形
        mCirclePaint=new Paint();
        mCirclePaint.setColor(Color.WHITE);
        mCirclePaint.setAntiAlias(true);
		//设置drawable的宽度是54dp
        int width= ResourceUtils.getDimenSize(R.dimen.dp_54);
        mHeight= width/2;
        setBounds(0,0,width,mHeight);
        mRect.set(0,0,width,mHeight);
        mCircleX=mHeight/2;
    }
    @Override
    public void draw(@NonNull Canvas canvas) {

        canvas.drawRoundRect(mRect,mHeight,mHeight,mRectPaint);
        canvas.drawCircle(mCircleX,mHeight/2,mHeight/2-2,mCirclePaint);
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) mRect.height();
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) mRect.width();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }
    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
    public void switchState(boolean checked){
        isCheck=checked;
        if (isCheck){
            animOn();
        }else{
            animOff();
        }
    }
    private void animOn(){
        ValueAnimator animator=ValueAnimator.ofFloat(mCircleX,mRect.width()-mRect.height()/2);
        animator.setDuration(ANIMATION_TIME);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue= (float) animation.getAnimatedValue();
                mCircleX=currentValue;
                mRectPaint.setColor(ColorUtils.blendARGB(mRectColor,mRectEndColor,animation.getAnimatedFraction()));
                invalidateSelf();
            }
        });
        animator.start();
    }
    private void animOff(){
        ValueAnimator animator=ValueAnimator.ofFloat(mCircleX,mRect.height()/2);
        animator.setDuration(ANIMATION_TIME);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue= (float) animation.getAnimatedValue();
                mCircleX=currentValue;
                mRectPaint.setColor(ColorUtils.blendARGB(mRectEndColor,mRectColor,animation.getAnimatedFraction()));
                invalidateSelf();
            }
        });
        animator.start();
    }
}
