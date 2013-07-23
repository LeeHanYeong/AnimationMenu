package arcanelux.animationmenu;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class AnimationMenuBtn extends ImageButton {
	private int position;
	private float x_offset = 0;
	private float y_offset = 0;
	
	private BubbleMsgLayout mBubbleMsgLayout;
	
	public AnimationMenuBtn(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public AnimationMenuBtn(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public AnimationMenuBtn(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public void getHitRect(Rect outRect) {
//		Rect curr = new Rect();
//	    super.getHitRect(curr);
//	    
//	    outRect.bottom = (int) (curr.bottom + y_offset);
//	    outRect.top = (int) (curr.top + y_offset);
//	    outRect.left = (int) (curr.left + x_offset);
//	    outRect.right = (int) (curr.right + x_offset);
//	}
	
	public void setOffset(float endX, float endY) {
//		offsetLeftAndRight((int)endX);
//		offsetTopAndBottom((int)endY);
		x_offset = endX;
		y_offset = endY;
	}
	
	public float getXOffset() {
		return x_offset;
	}
	
	public float getYOffset() {
		return y_offset;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public BubbleMsgLayout getBubbleMsgLayout() {
		return mBubbleMsgLayout;
	}

	public void setBubbleMsgLayout(BubbleMsgLayout mBubbleMsgLayout) {
		this.mBubbleMsgLayout = mBubbleMsgLayout;
	}

}
