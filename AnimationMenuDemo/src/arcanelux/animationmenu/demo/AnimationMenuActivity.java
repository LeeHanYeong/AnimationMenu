package arcanelux.animationmenu.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;
import arcanelux.animationmenu.AnimationMenu;
import arcanelux.animationmenu.AnimationMenuOnClickListener;
import arcanelux.animationmenu.C;

public class AnimationMenuActivity extends Activity implements OnClickListener, AnimationMenuOnClickListener{
	private final String TAG = "AnimationMenuExample";
	private Context mContext;

	private AnimationMenu mAnimationMenu, mAnimationMenu2;
	private ImageButton ibAnimationMenuMainBtn, ibAnimationMenuMainBtn2;

	private Button btnLEFT_TOP, btnLEFT_BOTTOM, btnRIGHT_TOP, btnRIGHT_BOTTOM, btnROUND, btnTOP, btnBOTTOM, btnTOP_INV, btnBOTTOM_INV, btnHORIZONTAL, btnVERTICAL;
	private ToggleButton tbtnEffectToggle1, tbtnEffectToggle2;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo);
		mContext = this;
		
		/** AnimationMenu **/
		mAnimationMenu = (AnimationMenu) findViewById(R.id.animationMenu1);
		mAnimationMenu.setAnimationMenuOnClickListener(this);
		mAnimationMenu.setDirection(C.LEFT_BOTTOM);
		ibAnimationMenuMainBtn = (ImageButton) findViewById(R.id.btnAnimationMenuMainBtn);
		mAnimationMenu.setMainBtn(ibAnimationMenuMainBtn);
		
		mAnimationMenu.addSubBtn(R.drawable.btn1back, 50, 50, "Button1");
		mAnimationMenu.addSubBtn(R.drawable.btn2back, 50, 50);
		mAnimationMenu.addSubBtn(R.drawable.btn3back, 50, 50, "Button2");
		mAnimationMenu.addSubBtn(R.drawable.btn4back, 50, 50);
		mAnimationMenu.addSubBtn(R.drawable.btn5back, 50, 50, "Button3");

		/** AnimationMenu2 **/
		mAnimationMenu2 = (AnimationMenu) findViewById(R.id.animationMenu2);
		mAnimationMenu2.setLength(0.8f);
		mAnimationMenu2.setAnimationMenuOnClickListener(this);
		mAnimationMenu2.setDirection(C.HORIZONTAL_RIGHT);
		ibAnimationMenuMainBtn2 = (ImageButton) findViewById(R.id.btnAnimationMainBtn2);
		mAnimationMenu2.setMainBtn(ibAnimationMenuMainBtn2);
		
		mAnimationMenu2.addSubBtn(R.drawable.btn1back, 40, 40, "Home");
		mAnimationMenu2.addSubBtn(R.drawable.btn2back, 40, 40, "MyPage");
		mAnimationMenu2.addSubBtn(R.drawable.btn3back, 40, 40, "Scrap");
		mAnimationMenu2.addSubBtn(R.drawable.btn4back, 40, 40, "Note");
		mAnimationMenu2.addSubBtn(R.drawable.btn5back, 40, 40, "Setting");

		/** Direction Test Button **/
		btnLEFT_TOP = (Button) findViewById(R.id.btnAnimationMenuLEFT_TOP);
		btnLEFT_BOTTOM = (Button) findViewById(R.id.btnAnimationMenuLEFT_BOTTOM);
		btnRIGHT_TOP = (Button) findViewById(R.id.btnAnimationMenuRIGHT_TOP);
		btnRIGHT_BOTTOM = (Button) findViewById(R.id.btnAnimationMenuRIGHT_BOTTOM);
		btnROUND = (Button) findViewById(R.id.btnAnimationMenuROUND);
		btnTOP = (Button) findViewById(R.id.btnAnimationMenuTOP);
		btnBOTTOM = (Button) findViewById(R.id.btnAnimationMenuBOTTOM);
		btnTOP_INV = (Button) findViewById(R.id.btnAnimationMenuTOP_INV);
		btnBOTTOM_INV = (Button) findViewById(R.id.btnAnimationMenuBOTTOM_INV);
		btnHORIZONTAL = (Button) findViewById(R.id.btnAnimationMenuHORIZONTAL);
		btnVERTICAL = (Button) findViewById(R.id.btnAnimationMenuVERTICAL);

		btnLEFT_TOP.setOnClickListener(this);
		btnLEFT_BOTTOM.setOnClickListener(this);
		btnRIGHT_TOP.setOnClickListener(this);
		btnRIGHT_BOTTOM.setOnClickListener(this);
		btnROUND.setOnClickListener(this);
		btnTOP.setOnClickListener(this);
		btnBOTTOM.setOnClickListener(this);
		btnTOP_INV.setOnClickListener(this);
		btnBOTTOM_INV.setOnClickListener(this);
		btnHORIZONTAL.setOnClickListener(this);
		btnVERTICAL.setOnClickListener(this);

		/** Effect Test ToggleButton **/
		tbtnEffectToggle1 = (ToggleButton) findViewById(R.id.tbtnAnimationMenu1);
		tbtnEffectToggle2 = (ToggleButton) findViewById(R.id.tbtnAnimationMenu2);
		tbtnEffectToggle1.setOnClickListener(this);
		tbtnEffectToggle2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.tbtnAnimationMenu1) {
			tbtnEffectToggle1.toggle();
			if(tbtnEffectToggle1.isChecked()){
				mAnimationMenu.subBtnEffect1Enable(false);
				tbtnEffectToggle2.setChecked(false);
				tbtnEffectToggle1.setChecked(false);
			} else{
				mAnimationMenu.subBtnEffect1Enable(true);
				tbtnEffectToggle2.setChecked(false);
				tbtnEffectToggle1.setChecked(true);
			}
		} else if (id == R.id.tbtnAnimationMenu2) {
			tbtnEffectToggle2.toggle();
			if(tbtnEffectToggle2.isChecked()){
				mAnimationMenu.subBtnEffect2Enable(false);
				tbtnEffectToggle1.setChecked(false);
				tbtnEffectToggle2.setChecked(false);
				Log.d(TAG, "Toggle1 Checked");
			} else{
				mAnimationMenu.subBtnEffect2Enable(true);
				tbtnEffectToggle1.setChecked(false);
				tbtnEffectToggle2.setChecked(true);
			}
		}

		/** Direction Test Button **/
		if(!mAnimationMenu.isOpened()){
			int id1 = v.getId();
			if (id1 == R.id.btnAnimationMenuLEFT_TOP) {
				mAnimationMenu.setDirection(C.LEFT_TOP);
				makeToast("왼쪽상단");
			} else if (id1 == R.id.btnAnimationMenuLEFT_BOTTOM) {
				mAnimationMenu.setDirection(C.LEFT_BOTTOM);
				makeToast("왼쪽하단");
			} else if (id1 == R.id.btnAnimationMenuRIGHT_TOP) {
				mAnimationMenu.setDirection(C.RIGHT_TOP);
				makeToast("오른쪽상단");
			} else if (id1 == R.id.btnAnimationMenuRIGHT_BOTTOM) {
				mAnimationMenu.setDirection(C.RIGHT_BOTTOM);
				makeToast("오른쪽하단");
			} else if (id1 == R.id.btnAnimationMenuROUND) {
				mAnimationMenu.setDirection(C.ROUND);
				makeToast("원형");
			} else if (id1 == R.id.btnAnimationMenuTOP) {
				mAnimationMenu.setDirection(C.TOP);
				makeToast("상단");
			} else if (id1 == R.id.btnAnimationMenuTOP_INV) {
				mAnimationMenu.setDirection(C.TOP_INVERSE);
				makeToast("상단 역순");
			} else if (id1 == R.id.btnAnimationMenuBOTTOM) {
				mAnimationMenu.setDirection(C.BOTTOM);
				makeToast("하단");
			} else if (id1 == R.id.btnAnimationMenuBOTTOM_INV) {
				mAnimationMenu.setDirection(C.BOTTOM_INVERSE);
				makeToast("하단 역순");
			}
		}
		if(!mAnimationMenu2.isOpened()){
			int id1 = v.getId();
			if (id1 == R.id.btnAnimationMenuHORIZONTAL) {
				mAnimationMenu2.setLength(0.8f);
				mAnimationMenu2.setDirection(C.HORIZONTAL_RIGHT);
				makeToast("가로우측, 80%");
			} else if (id1 == R.id.btnAnimationMenuVERTICAL) {
				mAnimationMenu2.setLength(0.5f);
				mAnimationMenu2.setDirection(C.VERTICAL_TOP);
				makeToast("세로상단 50%");
			}
		}
	}

	private void makeToast(String value){
		Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
	}

		@Override
		public void onMainBtnClick(AnimationMenu animationMenu) {
			if(animationMenu==mAnimationMenu){
				Toast.makeText(mContext, "Main1", Toast.LENGTH_SHORT).show();
			} else if(animationMenu==mAnimationMenu2){
				Toast.makeText(mContext, "Main2", Toast.LENGTH_SHORT).show();
			}
		}
	
		@Override
		public void onSubBtnClick(AnimationMenu animationMenu, int index) {
			Log.d(TAG, "onSubBtnClick : " + index);
			if(animationMenu==mAnimationMenu){
				switch(index){
				case 0:
					Toast.makeText(mContext, "Sub1-1", Toast.LENGTH_SHORT).show();
					break;
				case 1:
					Toast.makeText(mContext, "Sub1-2", Toast.LENGTH_SHORT).show();
					break;
				case 2:
					Toast.makeText(mContext, "Sub1-3", Toast.LENGTH_SHORT).show();
					break;
				case 3:
					Toast.makeText(mContext, "Sub1-4", Toast.LENGTH_SHORT).show();
					break;
				case 4:
					Toast.makeText(mContext, "Sub1-5", Toast.LENGTH_SHORT).show();
					break;
				}
			} else if(animationMenu==mAnimationMenu2){
				switch(index){
				case 0:
					Toast.makeText(mContext, "Sub2-1", Toast.LENGTH_SHORT).show();
					break;
				case 1:
					Toast.makeText(mContext, "Sub2-2", Toast.LENGTH_SHORT).show();
					break;
				case 2:
					Toast.makeText(mContext, "Sub2-3", Toast.LENGTH_SHORT).show();
					break;
				case 3:
					Toast.makeText(mContext, "Sub2-4", Toast.LENGTH_SHORT).show();
					break;
				case 4:
					Toast.makeText(mContext, "Sub2-5", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}
	

}
