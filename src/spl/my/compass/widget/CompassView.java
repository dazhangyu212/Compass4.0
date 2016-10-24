package spl.my.compass.widget;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CompassView extends View implements Runnable
{
	private Paint    _mPaint    = new Paint();
	private String   _message   = "正北 0°";
	private float    _decDegree = 0;
	private Bitmap   _compass;
	private Bitmap   _arrow;
	
	public Bitmap loadBitmap(Context context, String path){
		Bitmap bitmap = null;
		// 获取assets文件夹的管理类
		AssetManager assetManager = context.getAssets();
		try {
			InputStream in = assetManager.open(path);//产生输入流
//			Options opt = BitmapFactory.
			bitmap = BitmapFactory.decodeStream(in);//产生位图
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public CompassView(Context context)
	{
		this(context,null);
		
		
	}
	
	public CompassView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}

	public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// 载入图片
		_compass = loadBitmap(context, "bg_compasspointer.png");//bg_compasspointer
		_arrow = loadBitmap(context, "bg_compassbg.png");
		
		// 开启线程否则无法更新画面
		new Thread(this).start();
	}
	protected void onDraw(Canvas canvas)
	{
		canvas.drawColor(Color.BLACK);
		
		_mPaint.setColor(Color.WHITE);
		_mPaint.setTextSize(30);
		_mPaint.setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
		
		// 方向和角度
		canvas.drawText(_message, 75, 50, _mPaint);
		
		// 实现图像旋转
		Matrix mat = new Matrix();
		
		mat.reset();	
		mat.setTranslate(15, 100);// 位移
		//mat.preRotate(-_decDegree, 145, 145);
		mat.preRotate(-_decDegree, 225, 225);// 旋转
		
		// 绘制图像
		canvas.drawBitmap(_arrow, 15, 100, null);// 背景
		canvas.drawBitmap(_compass, mat, _mPaint);//罗盘
		
	}

	@Override
	public void run()
	{
		while(!Thread.currentThread().isInterrupted())
		{
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
			
			postInvalidate();
		}
		
	}
	
	// 更新指南针角度
	public void setDegree(float degree)
	{
		// 设置灵敏度
		if(Math.abs(_decDegree - degree) >= 2 )
		{
			_decDegree = degree;
			
			int range = 22;
			
			String degreeStr = String.valueOf(_decDegree);
			
			// 指向正北
			if(_decDegree > 360 - range && _decDegree < 360 + range)
			{
				_message = "正北 " + degreeStr + "°";
			}
			
			// 指向正东
			if(_decDegree > 90 - range && _decDegree < 90 + range)
			{
				_message = "正东 " + degreeStr + "°";
			}
			
			// 指向正南
			if(_decDegree > 180 - range && _decDegree < 180 + range)
			{
				_message = "正南 " + degreeStr + "°";
			}
			
			// 指向正西
			if(_decDegree > 270 - range && _decDegree < 270 + range)
			{
				_message = "正西 " + degreeStr + "°";
			}
			
			// 指向东北
			if(_decDegree > 45 - range && _decDegree < 45 + range)
			{
				_message = "东北 " + degreeStr + "°";
			}
			
			// 指向东南
			if(_decDegree > 135 - range && _decDegree < 135 + range)
			{
				_message = "东南 " + degreeStr + "°";
			}
			
			// 指向西南
			if(_decDegree > 225 - range && _decDegree < 225 + range)
			{
				_message = "西南 " + degreeStr + "°";
			}
			
			// 指向西北
			if(_decDegree > 315 - range && _decDegree < 315 + range)
			{
				_message = "西北 " + degreeStr + "°";
			}
		}

	}
	
	// 更新指示信息
	public void setMessage(String message)
	{
		_message = message;
//		invalidate();
	}
}
