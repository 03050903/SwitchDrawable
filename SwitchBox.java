package com.visionet.dazhongcx.chuz.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;
import android.widget.CheckBox;

import com.chen.base.utils.DLog;

/**
 * <font color='#9B77B2'>该类的主要用途:</font><br/><font color='#36FC2C'><b>
 * <p></p>
 * <b/></font><br/><hr/>
 * <b><font color='#05B8FD'>作者: C&C</font></b><br/><br/>
 * <b><font color='#05B8FD'>创建时间：2018/3/9</font></b><br/><br/>
 * <b><font color='#05B8FD'>联系方式：862530304@qq.com</font></b>
 */
public class SwitchBox extends AppCompatCheckBox {
    private SwitchDrawable switchDrawable;

    public SwitchBox(Context context) {
        super(context);
        init();
    }

    public SwitchBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwitchBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        switchDrawable=new SwitchDrawable();
        setButtonDrawable(switchDrawable);
    }

    @Override
    public void setButtonDrawable(Drawable buttonDrawable) {
        if (buttonDrawable instanceof SwitchDrawable){
            switchDrawable= (SwitchDrawable) buttonDrawable;
        }else{
            switchDrawable=null;
        }
        super.setButtonDrawable(buttonDrawable);
    }

    @Override
    public void setChecked(boolean checked) {
        DLog.i("调用了这个方法");
        super.setChecked(checked);
        if (switchDrawable!=null){
            switchDrawable.switchState(checked);
        }

    }
}
