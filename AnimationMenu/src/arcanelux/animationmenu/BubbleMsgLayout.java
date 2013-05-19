/**
 * Author : Lee HanYeong
 * File Name : BubbleMessage.java
 * Created Date : 2012. 11. 5.
 * Description
 */
package arcanelux.animationmenu;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class BubbleMsgLayout extends FrameLayout{
	private final String TAG = "lhy_PathMenu_BubbleMsgLayout";
	private Context mContext;
	
	private int index;
	private int imageRes;
	private String msg;
	private TextView tvMsg;
	private FrameLayout.LayoutParams paramsWrapContent = new FrameLayout.LayoutParams(
			FrameLayout.LayoutParams.WRAP_CONTENT, 
			FrameLayout.LayoutParams.WRAP_CONTENT);
	private FrameLayout.LayoutParams paramsWrapContent2 = new FrameLayout.LayoutParams(
			FrameLayout.LayoutParams.WRAP_CONTENT, 
			FrameLayout.LayoutParams.WRAP_CONTENT);

	public BubbleMsgLayout(Context context, int imageRes, String msg){
		super(context);
		this.mContext = context;
		this.imageRes = imageRes;
		this.msg = msg;
		
		firstInit(context);
	}

	private void firstInit(Context context){
		paramsWrapContent2.gravity = Gravity.TOP;
		
//		paramsWrapContent2.setMargins(-5, -5, -5, -5);
		setLayoutParams(paramsWrapContent2);
		setBackgroundResource(imageRes);
		
		tvMsg = new TextView(mContext);
		tvMsg.setText(msg);
		tvMsg.setLayoutParams(paramsWrapContent);
		tvMsg.setTextColor(Color.BLACK);
		addView(tvMsg);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void setTextSize(float value){
		tvMsg.setTextSize(value);
	}

}
