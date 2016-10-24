package spl.my.compass.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import spl.my.compass.R;

/**
 * 类名:CustomTitleView
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年4月12日
 */
public class CustomTitleView extends View {
	/** 
     * 文本 
     */  
    private String mTitleText;  
    /** 
     * 文本的颜色 
     */  
    private int mTitleTextColor;  
    /** 
     * 文本的大小 
     */  
    private int mTitleTextSize;  
  
    /** 
     * 绘制时控制文本绘制的范围 
     */  
    private Rect mBound;  
    private Paint mPaint;  
  
    public CustomTitleView(Context context, AttributeSet attrs)  
    {  
        this(context, attrs, 0);  
    }  
  
    public CustomTitleView(Context context)  
    {  
        this(context, null);  
    }  
  
    /** 
     * 获得我自定义的样式属性 
     *  
     * @param context 
     * @param attrs 
     * @param defStyle 
     */  
    public CustomTitleView(Context context, AttributeSet attrs, int defStyle)  
    {  
        super(context, attrs, defStyle);  
        /** 
         * 获得我们所定义的自定义样式属性 
         */  
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyle, 0);  
        int n = a.getIndexCount();  
        for (int i = 0; i < n; i++)  
        {  
            int attr = a.getIndex(i);  
            switch (attr)  
            {  
            case R.styleable.CustomTitleView_titleText:  
                mTitleText = a.getString(attr);  
                break;  
            case R.styleable.CustomTitleView_titleTextColor:  
                // 默认颜色设置为黑色  
                mTitleTextColor = a.getColor(attr, Color.BLACK);  
                break;  
            case R.styleable.CustomTitleView_titleTextSize:  
                // 默认设置为16sp，TypeValue也可以把sp转化为px  
                mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(  
                        TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));  
                break;  
  
            }  
  
        }  
        a.recycle();  
  
        /** 
         * 获得绘制文本的宽和高 
         */  
        mPaint = new Paint();  
        mPaint.setTextSize(mTitleTextSize);  
        // mPaint.setColor(mTitleTextColor);  
        mBound = new Rect();  
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);  
    }
}
