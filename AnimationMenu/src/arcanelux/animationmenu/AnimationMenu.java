package arcanelux.animationmenu;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AnimationMenu extends FrameLayout{
	private final String TAG = "AnimationMenu";
	private Context mContext;
	private boolean D = C.DEBUG;
	private AnimationMenu mAnimationMenu = this;

	private ArrayList<AnimationMenuBtn> mAnimationMenuBtns;
	private View mainBtnView;

	private final int NONE = 0;
	private int MODE = C.LEFT_BOTTOM;
	private boolean isOpened = false;

	private boolean isSubEffect = false;
	private boolean isSubEffect2 = false;
	private boolean isSoundEffect = false;

	private int aniMain1 = android.R.anim.overshoot_interpolator;
	private int aniMain2 = android.R.anim.overshoot_interpolator;
	private int aniSubOpen = android.R.anim.overshoot_interpolator;
	private int aniSubClose = android.R.anim.anticipate_interpolator;

	/** 메뉴가 열렸을 때 이동거리 */
	private int length = 200;
	/** 클릭방지 여백 시간 **/
	private int click_delay = 300;
	/** 가장 좌측버튼 동작시간 */
	private int duration = 1000;
	/** 메인버튼 회전각도 **/
	private float rotationAngle = 360;
	/** 메인버튼 회전횟수 */
	private int rotation = 1;
	/** 하위 메뉴 동작 시간 */
	private int sub_duration = 500;
	/** 하위 메뉴 선택시 동작 시간 */
	private int sub_select_duration = 500;
	/** 하위 메뉴 선택시 동작 시간2 (사라지지않을경우) */
	private int sub_select_duration2 = 150;
	/** 하위 메뉴 동작시 각 버튼간의 시간 Gap */
	private int sub_offset = 100;
	/** 하위 메뉴 회전각도 **/
	private float sub_rotationAngle = 360;
	/** 하위 메뉴 회전횟수 **/
	private int sub_rotation = 1;

	/** 서브버튼 이동변수 **/
	private float[] endX;
	private float[] endY;
	private float endLengthX = length;
	private float endLengthY = length;
	private double endAngle = Math.PI/2;
	private int endSize = -1;

	/** Setting Function **/
	/** 이동거리, AnimationMenu 생성 후 가장 먼저 해준다 **/
	public void setLength(float value){ lengthRate = value; }
	/** 메인버튼 동작시간 **/
	public void setDuration(int value){ duration = value; }
	/** 메인버튼 회전각도(지정시 회전횟수 무시) **/
	public void setMainBtnAngle(float value){ rotationAngle = value; rotation = 1; }
	/** 메인버튼 회전횟수(지정시 회전각도 무시) **/
	public void setMainBtnRotation(int value){ rotationAngle = 360; rotation = value; }
	/** 서브버튼 회전각도(지정시 회전횟수 무시) **/
	public void setSubBtnAngle(float value){ sub_rotationAngle = value; sub_rotation = 1; }
	/** 서브버튼 회전횟수(지정시 회전각도 무시) **/
	public void setSubBtnRotation(int value){ sub_rotationAngle = 360; sub_rotation = value; }
	/** 서브버튼 동작시간 **/
	public void setSubDuration(int value){ sub_duration = value; }
	/** 서브버튼 선택시 동작시간(사라질 경우.  Effect2) **/
	public void setSubSelectDuration(int value){ sub_select_duration = value; }
	/** 서브버튼 선택시 동작시간(사라지지 않을 경우. Effect1) **/
	public void setSubSelectDuration2(int value){ sub_select_duration2 = value; }
	/** 서브버튼 동작시 각 버튼간의 시간 Gap **/
	public void setSubOffSet(int value){ sub_offset = value; }

	/** 메인버튼 애니메이션 지정(메인버튼 클릭시, 서브버튼 클릭시) **/
	public void setMainBtnAnimation(int value){ aniMain1 = value; aniMain2 = value; }
	/** 서브버튼 열림 애니메이션 지정 **/
	public void setSubBtnOpenAnimation(int value){ aniSubOpen = value; }
	/** 서브버튼 닫힘 애니메이션 지정 **/
	public void setSubBtnCloseAnimation(int value){ aniSubClose = value; }

	/** lhy Variable **/
	private int dWidth, dHeight;
	private float lengthRate;
	private AnimationMenuOnClickListener mAnimationMenuOnClickListener;
	private int mainBtnRes1, mainBtnRes2;
	private Sound mSound;
	private FrameLayout flBubbleParent;
	private BubbleMsgLayout[] bubbleMsgLayouts;
	private ArrayList<BubbleMsgLayout> mBubbleMsgLayoutList;
	private int bubbleTopSet = 0;
	private int bubbleLeftSet = 0;
	private float bubbleTextSize = 10;
	//	private AlphaAnimation bubbleAlphaOnAni;
	//	private AlphaAnimation bubbleAlphaOffAni;
	private int bubbleImageRes = R.drawable.bubble;

	/** Constructor **/
	public AnimationMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		firstInit(context);
	}
	public AnimationMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		firstInit(context);
	}
	public AnimationMenu(Context context) {
		super(context);
		firstInit(context);
	}

	/** First Initializing **/
	private void firstInit(Context context){
		mContext = context;
		mAnimationMenuBtns = new ArrayList<AnimationMenuBtn>();

		/** 테스트 기본값 **/
		setLength(0.4f);
		setDirection(MODE);

		if(isInEditMode()){
			//			setBackgroundColor(Color.WHITE);
		} else{
			Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			dWidth = display.getWidth();
			dHeight = display.getHeight();

			mSound = new Sound();	

			setBubbleLeftSet(0.06f);
			setBubbleTopSet(-0.12f);
		}

		mBubbleMsgLayoutList = new ArrayList<BubbleMsgLayout>();

		/** Sound **/
		//		mSound.setSoundFile(mContext, R.raw.main, "main");
		//		mSound.setSoundFile(mContext, R.raw.sub, "sub");
	}


	/** 
	 * 메인버튼 설정함수
	 *  AnimationMenuOnClickListener를 설정하며,
	 *  OnClickLIstener 내부에서 AnimationMenuOnClickListener를 호출
	 */
	public void setMainBtn(View v){
		mainBtnView = v;
		v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mainBtnClick();
				if(mAnimationMenuOnClickListener != null) mAnimationMenuOnClickListener.onMainBtnClick(mAnimationMenu);
			}
		});
	}

	/** 동적 서브버튼 추가 함수 **/
	public void addSubBtn(int imageRes){
		int width = FrameLayout.LayoutParams.WRAP_CONTENT;
		int height = FrameLayout.LayoutParams.WRAP_CONTENT;
		addSubBtnDefault(imageRes, width, height, null);
	}
	public void addSubBtn(int imageRes, int width, int height){
		addSubBtnDefault(imageRes, width, height, null);
	}
	public void addSubBtn(int imageRes, String msg){
		int width = FrameLayout.LayoutParams.WRAP_CONTENT;
		int height = FrameLayout.LayoutParams.WRAP_CONTENT;
		addSubBtnDefault(imageRes, width, height, msg);
	}
	public void addSubBtn(int imageRes, int width, int height, String msg){
		addSubBtnDefault(imageRes, width, height, msg);
	}

	private void addSubBtnDefault(int imageRes, int width, int height, String msg){
		/** btn LayoutParams 설정 **/
		final AnimationMenuBtn btn = new AnimationMenuBtn(mContext);
		//		btn.setImageResource(imageRes);
		btn.setBackgroundResource(imageRes);
		//		btn.setBackgroundColor(Color.TRANSPARENT);
		final FrameLayout.LayoutParams btnParams = new FrameLayout.LayoutParams(width, height);
		btnParams.gravity = Gravity.TOP;
		btn.setLayoutParams(btnParams);

		/** LayoutParams 추가설정. 뷰가 그려진 후에 작동 **/
		post(new Runnable() {
			@Override
			public void run() {
				addView(btn);
				int btnWidth = btn.getLayoutParams().width;
				int btnHeight = btn.getLayoutParams().height;

				/** WRAP_CONTENT등(-1, -2....)의 경우 직접 View의 크기 알아냄 **/
				if(btnWidth < 0){
					btn.post(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							int btnWidth = btn.getWidth();
							int btnHeight = btn.getHeight();
							if(D) Log.d(TAG, "AddSubBtn) less0, btnWidth : " + btnWidth + ", btnHeight : " + btnHeight);
							btnParams.leftMargin = mainBtnView.getLeft() + mainBtnView.getWidth()/2 - btnWidth/2;
							btnParams.topMargin = mainBtnView.getTop() + mainBtnView.getHeight()/2 - btnHeight/2;
							btn.setLayoutParams(btnParams);
						}
					});
					/** View크기 지정해준 경우 바로 적용 **/
				} else{
					btnParams.leftMargin = mainBtnView.getLeft() + mainBtnView.getWidth()/2 - btnWidth/2;
					btnParams.topMargin = mainBtnView.getTop() + mainBtnView.getHeight()/2 - btnHeight/2;
					if(D) Log.d(TAG, "AddSubBtn) btnWidth : " + btnWidth + ", btnHeight : " + btnHeight);
					btn.setLayoutParams(btnParams);
				}

				/** 각 서브버튼에 포지션값 지정(클릭시 어느 서브버튼인지 판단) **/
				btn.setPosition(mAnimationMenuBtns.size());
				btn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						int position = btn.getPosition();
						if(D) Log.d(TAG, "SubBtn" + position + " Clicked");
						subBtnClick(position);
						if(mAnimationMenuOnClickListener != null) mAnimationMenuOnClickListener.onSubBtnClick(mAnimationMenu, position);
					}
				});
				btn.setVisibility(View.INVISIBLE);
				mAnimationMenuBtns.add(btn);
				endX = new float[mAnimationMenuBtns.size()];
				endY = new float[mAnimationMenuBtns.size()];

				/** 앞에 나오는 버튼이 뒤에 나오는 버튼보다 상위에 있어 애니메이션시 겹치지 않게 만듬 **/
				for(int i=mAnimationMenuBtns.size()-1; i>=0; i--){
					mAnimationMenuBtns.get(i).bringToFront();
				}
				mainBtnView.bringToFront();
				if(D) Log.d(TAG, "mAnimationMenuBtns Size : " + mAnimationMenuBtns.size());
			}
		});

		/** BubbleMessage가 있을 경우, AnimationMenuBtn에 BubbleMsgLayout 추가, size-1을 index로 전달 **/
		if(msg!=null){
			BubbleMsgLayout curBLayout = new BubbleMsgLayout(mContext, bubbleImageRes, msg);
			btn.setBubbleMsgLayout(curBLayout);
			curBLayout.setVisibility(View.INVISIBLE);
			mBubbleMsgLayoutList.add(curBLayout);
			if(dWidth > 600){
				curBLayout.setTextSize(dWidth*bubbleTextSize/700);
			} else if(dWidth <= 600){
				curBLayout.setTextSize(dWidth*bubbleTextSize/500);
			}
			addView(curBLayout);
		} else{
			mBubbleMsgLayoutList.add(null);
		}
	}

	/** 서브버튼 이펙트1 **/
	public void subBtnEffect1Enable(boolean value){
		isSubEffect = value;
		isSubEffect2 = false;
	}
	/** 서브버튼 이펙트2 **/
	public void subBtnEffect2Enable(boolean value){
		isSubEffect = false;
		isSubEffect2 = value;
	}

	/** 메인버튼 클릭 이벤트 **/
	public void mainBtnClick(){	
		if(D) Log.d(TAG, "MainClick");
		//		mSound.playSound("main", 0.5f);
		if(isOpened) { isOpened = false; }
		else { isOpened = true; }
		startMenuAnimation(mAnimationMenuBtns, mainBtnView, isOpened, mContext);
		// 동작시간동안 재클릭 막기
		setClickDelay(mainBtnView, duration+click_delay);
	}
	/** 서브버튼 클릭 이벤트 **/
	public void subBtnClick(int index){
		//		mSound.playSound("sub");
		if(isOpened && isSubEffect2) { isOpened = false; }
		else if(!isOpened && isSubEffect2){ isOpened = true; }
		if(isSubEffect || isSubEffect2){
			startSubButtonSelectedAnimation(mAnimationMenuBtns, mainBtnView, mContext, index, isOpened);
			// 동작시간동안 재클릭 막기
			setClickDelay(mAnimationMenuBtns, sub_duration);
			setClickDelay(mainBtnView, duration > sub_duration ? duration+click_delay : sub_duration+click_delay);
		}
	}

	/** 메인버튼 클릭시 이미지를 바꿀경우 이미지Resource 이름 등록 **/
	public void setMainBtnImageRes(int res1, int res2){
		mainBtnRes1 = res1;
		mainBtnRes2 = res2;
	}

	/** 메인버튼 사이즈를 화면가로사이즈 기준 %로 맞추어줌 **/
	public void setMainBtnSize(final float ratio){
		((ImageButton) mainBtnView).setScaleType(ImageView.ScaleType.FIT_XY);
		mainBtnView.post(new Runnable() {
			@Override
			public void run() {
				int originWidth = mainBtnView.getWidth();
				int originHeight = mainBtnView.getHeight();

				float value = dWidth*ratio;
				int width = (int) value;

				float value2 = originHeight * value / originWidth;
				int height = (int) value2;

				FrameLayout.LayoutParams oriParams = (LayoutParams) mainBtnView.getLayoutParams();
				FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
				//				params.gravity = oriParams.gravity;
				mainBtnView.setLayoutParams(params);
			}
		});
	}
	/** return value **/
	public boolean isOpened(){
		return isOpened;
	}

	/**
	 * 메인 버튼을 눌렀을때 애니메이션 처리
	 * 버튼이 rotationAngle*rotation 만큼 회전
	 */
	public void startMenuAnimation(ArrayList<AnimationMenuBtn> btns, final View mainBtn, final boolean isOpen, Context mContext){
		Animation rotate;
		if(isOpen){
			rotate = new RotateAnimation(
					0, rotation*rotationAngle
					, Animation.RELATIVE_TO_SELF, 0.5f
					, Animation.RELATIVE_TO_SELF, 0.5f);
		}else{
			rotate = new RotateAnimation(
					rotation*rotationAngle, 0
					, Animation.RELATIVE_TO_SELF, 0.5f
					, Animation.RELATIVE_TO_SELF, 0.5f);
		}

		rotate.setInterpolator(AnimationUtils.loadInterpolator(mContext, aniMain1));
		rotate.setFillAfter(true);
		rotate.setDuration(duration);
		mainBtn.startAnimation(rotate);

		/** 메인버튼 이미지 변화, 미구현 **/
		rotate.setAnimationListener(new AnimationListener(){
			@Override
			public void onAnimationEnd(Animation animation) {
				if(mainBtnRes1!=NONE && mainBtnRes2!=NONE){
					//					if(isOpen)		((ImageButton) mainBtn).setImageResource(mainBtnRes1);
					//					else				((ImageButton) mainBtn).setImageResource(mainBtnRes2);
				}
			}
			@Override
			public void onAnimationRepeat(Animation animation) { }
			@Override
			public void onAnimationStart(Animation animation) {
				if(mainBtnRes1!=NONE && mainBtnRes2!=NONE){
					//					if(isOpen)		((ImageButton) mainBtn).setImageResource(mainBtnRes2);
					//					else				((ImageButton) mainBtn).setImageResource(mainBtnRes2);
				}
			}
		});

		for(int i=0; i<btns.size(); i++){
			startSubButtonAnimation(btns, mContext, i, isOpen);
		}
	}

	/**
	 * 하위메뉴가 애니메이션
	 * 회전하며 이동
	 * 효과를 위해 Interpolater사용
	 */
	public void startSubButtonAnimation(ArrayList<AnimationMenuBtn> btns, final Context mContext, final int index, final boolean open){
		final AnimationMenuBtn view = btns.get(index);

		/** 서브버튼들의 이동거리 저장 **/
		switch(MODE){
		case C.HORIZONTAL_RIGHT:
			/** 오른쪽으로 **/
			endX[index] = endLengthX * (index+1)/(btns.size());
			endY[index] = 0;
			break;
		case C.HORIZONTAL_LEFT:
			/** 왼쪽으로 **/
			endX[index] = -endLengthX * (index+1)/(btns.size());
			endY[index] = 0;
			break;
		case C.VERTICAL_TOP:
			/** 위쪽으로 **/
			endX[index] = 0;
			endY[index] = -endLengthY * (index+1)/(btns.size());
			break;
		case C.VERTICAL_BOTTOM:
			/** 아래쪽으로 **/
			endX[index] = 0;
			endY[index] = endLengthY * (index+1)/(btns.size());
			break;
			/** 그 외 모드 **/
		default:
			endX[index] = endLengthX * FloatMath.cos((float)(endAngle * (index)/(btns.size()+endSize)));
			endY[index] = endLengthY * FloatMath.sin((float)(endAngle * (index)/(btns.size()+endSize)));
		}
		if(D) Log.d(TAG, "Move" + index + ") endX : " + endX[index] + ", endY : " + endY[index]);

		AnimationSet animation = new AnimationSet(false);
		Animation translate;
		Animation rotate = new RotateAnimation(
				0, sub_rotationAngle*sub_rotation
				, Animation.RELATIVE_TO_SELF, 0.5f
				, Animation.RELATIVE_TO_SELF, 0.5f);
		rotate.setDuration(sub_duration);
		rotate.setRepeatCount(0);
		//rotate.setInterpolator(AnimationUtils.loadInterpolator(mContext, android.R.anim.accelerate_interpolator));

		if(open){
			translate = new TranslateAnimation(0, endX[index], 0, endY[index]);
			translate.setDuration(sub_duration);
			translate.setInterpolator(AnimationUtils.loadInterpolator(mContext, aniSubOpen));
			translate.setStartOffset(sub_offset*index);
			//			view.setOffset(endX, endY);
		} else{
			translate = new TranslateAnimation(0, -endX[index], 0, -endY[index]);
			//			translate = new TranslateAnimation(endX[index], 0, endY[index], 0);
			translate.setDuration(sub_duration);
			translate.setInterpolator(AnimationUtils.loadInterpolator(mContext, aniSubClose));
			translate.setStartOffset(sub_offset*(btns.size()-(index+1)));
			//			view.setOffset(-endX, -endY);
		}

		animation.setFillAfter(false);
		animation.addAnimation(rotate);
		animation.addAnimation(translate);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				if(open){ view.setVisibility(View.VISIBLE); }
				AnimationMenuBtn curBtn = mAnimationMenuBtns.get(index);
				// 닫는중일경우 애니메이션 시작시 Hide BubbleMsg
				if(curBtn.getBubbleMsgLayout()!=null && !open){
					hideBubbleMsgLayout(index);
				}
			}

			@Override
			public void onAnimationRepeat(Animation animation) { }
			@Override
			public void onAnimationEnd(Animation animation) {
				/** 깜빡이는 현상 제거위해 0.01초 딜레이 사용 **/
				Handler mHandler = new Handler();
				mHandler.postDelayed(new Runnable() {
					public void run(){
						moveAnimationButton(mAnimationMenuBtns, mContext, index, open);
						AnimationMenuBtn curBtn = mAnimationMenuBtns.get(index);
						// 여는중일경우 애니메이션 끝날 때 Show BubbleMsg
						if(curBtn.getBubbleMsgLayout()!=null && open){
							showBubbleMsgLayout(index);
						}
					}
				}, 10); //Delay time
				
				/** 닫힐경우 끝난 후 안보이게함 **/
				if(!open){
					view.setVisibility(View.INVISIBLE);
				}
			}
		});

		view.startAnimation(animation);
	}

	/** 서브버튼 클릭시 **/
	public void startSubButtonSelectedAnimation(ArrayList<AnimationMenuBtn> buttons, View mainBtn, final Context mContext, final int index, final boolean open) {
		for(int i = 0 ; i < buttons.size() ; i++) {
			if(index == i) {
				final AnimationMenuBtn view = buttons.get(i);

				AnimationSet animation = new AnimationSet(false);
				Animation scale = new ScaleAnimation(
						1.0f, 2.5f
						, 1.0f, 2.5f
						, Animation.RELATIVE_TO_SELF, 0.5f
						, Animation.RELATIVE_TO_SELF, 0.5f);
				scale.setDuration(sub_select_duration);

				if(isSubEffect){
					scale = new ScaleAnimation(
							1.0f, 1.5f
							, 1.0f, 1.5f
							, Animation.RELATIVE_TO_SELF, 0.5f
							, Animation.RELATIVE_TO_SELF, 0.5f);
					scale.setDuration(sub_select_duration2);
					scale.setRepeatMode(Animation.REVERSE);
					scale.setRepeatCount(1);
				}

				Animation alpha = new AlphaAnimation(1.0f, 0.0f);
				alpha.setDuration(sub_select_duration);

				animation.addAnimation(scale);
				if(isSubEffect2){
					animation.addAnimation(alpha);
				}
				animation.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {
						if(isSubEffect2 && view.getBubbleMsgLayout()!=null) hideBubbleMsgLayout(index);
					}
					@Override
					public void onAnimationRepeat(Animation animation) { }
					@Override
					public void onAnimationEnd(Animation animation) {
						if(isSubEffect2) moveAnimationButton(mAnimationMenuBtns, mContext, index, open);
						if(!open){
							view.setVisibility(View.INVISIBLE);
						}
					}
				});
				view.startAnimation(animation);
			} else {
				final AnimationMenuBtn view = buttons.get(i);
				final int curPos = i;
				AnimationSet animation = new AnimationSet(false);
				Animation scale = new ScaleAnimation(
						1.0f, 0.0f
						, 1.0f, 0.0f
						, Animation.RELATIVE_TO_SELF, 0.5f
						, Animation.RELATIVE_TO_SELF, 0.5f);
				scale.setDuration(sub_select_duration);

				Animation alpha = new AlphaAnimation(1.0f, 0.0f);
				alpha.setDuration(sub_select_duration);

				if(isSubEffect2){
					animation.addAnimation(scale);
					animation.addAnimation(alpha);
				}
				animation.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) { 
						if(isSubEffect2 && view.getBubbleMsgLayout()!=null) hideBubbleMsgLayout(curPos);
					}
					@Override
					public void onAnimationRepeat(Animation animation) { }
					@Override
					public void onAnimationEnd(Animation animation) {
						if(isSubEffect2) moveAnimationButton(mAnimationMenuBtns, mContext, curPos, open);
						if(!open){
							view.setVisibility(View.INVISIBLE);
						}
					}
				});
				view.startAnimation(animation);
			}
		}

		if(!open && isSubEffect2) {
			Animation rotate = new RotateAnimation(
					rotationAngle*rotation, 0
					, Animation.RELATIVE_TO_SELF, 0.5f
					, Animation.RELATIVE_TO_SELF, 0.5f);
			rotate.setInterpolator(AnimationUtils.loadInterpolator(mContext,
					aniMain2));
			rotate.setFillAfter(false);
			rotate.setDuration(duration);
			mainBtn.startAnimation(rotate);
		}
	}

	private void moveAnimationButton(ArrayList<AnimationMenuBtn> btns, Context mContext, final int index, final boolean open){
		final AnimationMenuBtn curBtn = (AnimationMenuBtn) btns.get(index);
		// 앞에서 저장한값으로 대체
		//		endX = endLengthX * FloatMath.cos((float)(endAngle * (index)/(btns.size()+endSize)));
		//		endY = endLengthY * FloatMath.sin((float)(endAngle * (index)/(btns.size()+endSize)));
		if(open){
			curBtn.offsetLeftAndRight((int)endX[index]);
			curBtn.offsetTopAndBottom((int)endY[index]);
		} else{
			curBtn.offsetLeftAndRight(-(int)endX[index]);
			curBtn.offsetTopAndBottom(-(int)endY[index]);
		}
	}

	/** AnimationMenu 작동방향 설정 **/
	public void setDirection(int value){
		MODE = value;
		length = (int)(lengthRate * dWidth);
		switch(value){
		case C.LEFT_TOP:
			endLengthX = -length;
			endLengthY = -length;
			endAngle = Math.PI/2;
			endSize = -1;
			break;
		case C.LEFT_BOTTOM:
			endLengthX = -length;
			endLengthY = length;
			endAngle = Math.PI/2;
			endSize = -1;
			break;
		case C.RIGHT_TOP:
			endLengthX = length;
			endLengthY = -length;
			endAngle = Math.PI/2;
			endSize = -1;
			break;
		case C.RIGHT_BOTTOM:
			endLengthX = length;
			endLengthY = length;
			endAngle = Math.PI/2;
			endSize = -1;
			break;
		case C.ROUND:
			endLengthX = length;
			endLengthY = -length;
			endAngle = Math.PI *2;
			endSize = 0;
			break;
		case C.TOP:
			endLengthX = -length;
			endLengthY = -length;
			endAngle = Math.PI ;
			endSize = -1;
			break;
		case C.BOTTOM:
			endLengthX = -length;
			endLengthY = length;
			endAngle = Math.PI ;
			endSize = -1;
			break;
		case C.TOP_INVERSE:
			endLengthX = length;
			endLengthY = -length;
			endAngle = Math.PI ;
			endSize = -1;
			break;
		case C.BOTTOM_INVERSE:
			endLengthX = length;
			endLengthY = length;
			endAngle = Math.PI ;
			endSize = -1;
			break;
		case C.HORIZONTAL_RIGHT:
		case C.HORIZONTAL_LEFT:
			endLengthX = lengthRate * dWidth;
			if(D) Log.d(TAG, "Horizontal Mode) endLengthX : " + endLengthX);
			endLengthY = 0;
			break;
		case C.VERTICAL_TOP:
		case C.VERTICAL_BOTTOM:
			endLengthY = lengthRate * dHeight;
			if(D) Log.d(TAG, "Vertical Mode) endLengthY : " + endLengthY);
			endLengthX = 0;
			break;
		}
	}

	/** 일정시간 버튼클릭 막아주기 **/
	private void setClickDelay(final View v, int time){
		v.setClickable(false);
		Handler mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {
			public void run(){
				v.setClickable(true);
			}
		}, time); //Delay time
	}
	/** 일정시간 버튼클릭 막기 - View ArrayList 지원 **/
	private <T> void setClickDelay(final ArrayList<T> vList, int time){
		for(T v : vList){
			final T curV = v;
			((View) curV).setClickable(false);
			Handler mHandler = new Handler();
			mHandler.postDelayed(new Runnable() {
				public void run(){
					((View) curV).setClickable(true);
				}
			}, time); //Delay time
		}
	}

	/** ANimationMenu 버튼 클릭리스너 할당 **/
	public void setAnimationMenuOnClickListener(AnimationMenuOnClickListener listener){
		mAnimationMenuOnClickListener = listener;
	}
	/** 클릭사운드 유/무 **/
	public void setClickSoundEnable(boolean value){
		isSoundEffect = value;
	}
	/** 메인버튼 클릭 사운드 설정 **/
	public void setMainSound(int res){
		mSound.setSoundFile(mContext, res, "main");
	}
	/** 서브버튼 클릭 사운드 설정 **/
	public void setSubSound(int res){
		mSound.setSoundFile(mContext, res, "sub");
	}
	/** 풍선이미지 설정 **/
	public void setBubbleImageRes(int res){
		bubbleImageRes = res;
	}

	/** BubbleMsg의 상단 시작위치 값 설정 **/
	private void setBubbleTopSet(float value){
		bubbleTopSet = (int)(dWidth * value);
	}
	
	/** BubbleMsg의 좌측 시작위치 값 설정 **/
	private void setBubbleLeftSet(float value){
		bubbleLeftSet = (int)(dWidth * value);
	}

	private void showBubbleMsgLayout(int index){
		final BubbleMsgLayout curBLayout = mBubbleMsgLayoutList.get(index);
		int oriLeft = curBLayout.getLeft();
		int oriTop = curBLayout.getTop();
		curBLayout.offsetLeftAndRight(-oriLeft);
		curBLayout.offsetTopAndBottom(-oriTop);

		int left = mainBtnView.getLeft() + mainBtnView.getWidth()/2 + (int) endX[index] + bubbleLeftSet;
		int top = mainBtnView.getTop() + mainBtnView.getHeight()/2 + (int) endY[index] + bubbleTopSet;

		curBLayout.offsetLeftAndRight(left);
		curBLayout.offsetTopAndBottom(top);
		AlphaAnimation bubbleAlphaOnAni = new AlphaAnimation(0, 1);
		bubbleAlphaOnAni.setDuration(sub_duration);

		curBLayout.startAnimation(bubbleAlphaOnAni);
		bubbleAlphaOnAni.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) { 

			}
			@Override
			public void onAnimationRepeat(Animation animation) { }
			@Override
			public void onAnimationEnd(Animation animation) {
				/** 깜빡이는 현상 제거위해 0.01초 딜레이 사용 **/
				Handler mHandler = new Handler();
				mHandler.postDelayed(new Runnable() {
					public void run(){
						curBLayout.setVisibility(View.VISIBLE);				
					}
				}, 10); //Delay time
			}
		});
	}
	private void hideBubbleMsgLayout(int index){
		final BubbleMsgLayout curBLayout = mBubbleMsgLayoutList.get(index);
		int oriLeft = curBLayout.getLeft();
		int oriTop = curBLayout.getTop();
		curBLayout.offsetLeftAndRight(-oriLeft);
		curBLayout.offsetTopAndBottom(-oriTop);

		int left = mainBtnView.getLeft() + mainBtnView.getWidth()/2 + (int) endX[index] + bubbleLeftSet;
		int top = mainBtnView.getTop() + mainBtnView.getHeight()/2 + (int) endY[index] + bubbleTopSet;

		curBLayout.offsetLeftAndRight(left);
		curBLayout.offsetTopAndBottom(top);

		AlphaAnimation bubbleAlphaOffAni = new AlphaAnimation(1, 0);
		bubbleAlphaOffAni.setDuration(sub_duration);

		curBLayout.startAnimation(bubbleAlphaOffAni);
		bubbleAlphaOffAni.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) { 

			}
			@Override
			public void onAnimationRepeat(Animation animation) { }
			@Override
			public void onAnimationEnd(Animation animation) {
				/** 깜빡이는 현상 제거위해 0.01초 딜레이 사용 **/
				Handler mHandler = new Handler();
				mHandler.postDelayed(new Runnable() {
					public void run(){
						curBLayout.setVisibility(View.INVISIBLE);				
					}
				}, 10); //Delay time
			}
		});


	}

}

