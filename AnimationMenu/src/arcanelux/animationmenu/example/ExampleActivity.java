package arcanelux.animationmenu.example;
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
import arcanelux.animationmenu.C;
import arcanelux.animationmenu.AnimationMenu;
import arcanelux.animationmenu.AnimationMenuOnClickListener;
import arcanelux.animationmenu.R;

public class ExampleActivity extends Activity implements OnClickListener, AnimationMenuOnClickListener{
	private final String TAG = "lhy_PathMenuActivity";
	private Context mContext;

	private AnimationMenu mPathMenu, mPathMenu2;
	private ImageButton ibPathMenuMainBtn, ibPathMenuMainBtn2;

	private Button btnLEFT_TOP, btnLEFT_BOTTOM, btnRIGHT_TOP, btnRIGHT_BOTTOM, btnROUND, btnTOP, btnBOTTOM, btnTOP_INV, btnBOTTOM_INV, btnHORIZONTAL, btnVERTICAL;
	private ToggleButton tbtnEffectToggle1, tbtnEffectToggle2;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example);
		mContext = this;

		/** PathMenu **/
//		mPathMenu = new PathMenu(mContext);
//		ibPathMenuMainBtn = (ImageButton) findViewById(R.id.btnPathMainBtn);
//		mPathMenu.setMainBtn(ibPathMenuMainBtn);
//		mPathMenu.setDirection(C.LEFT_BOTTOM);
//		mPathMenu.setMainBtnRotation(1);
//		mPathMenu.setSubBtnRotation(1);
//		mPathMenu.setPathMenuOnClickListener(this);
////		mPathMenu.setMainBtnImageRes(R.drawable.simyeong2, R.drawable.simyeong3);
//		mPathMenu.setClickSoundEnable(true);
//		mPathMenu.setMainBtnSize(0.15f);
//		//		mPathMenu.setMainBtnAngle(90);
//		//		mPathMenu.setSubBtnAngle(90);
//
//		mPathMenu.addSubBtn((PathMenuBtn) findViewById(R.id.btnPathSub1));
//		mPathMenu.addSubBtn((PathMenuBtn) findViewById(R.id.btnPathSub2));
//		mPathMenu.addSubBtn((PathMenuBtn) findViewById(R.id.btnPathSub3));
//		mPathMenu.addSubBtn((PathMenuBtn) findViewById(R.id.btnPathSub4));
//		mPathMenu.addSubBtn((PathMenuBtn) findViewById(R.id.btnPathSub5));
//
//		/** PathMenu2 **/
//		mPathMenu2 = new PathMenu(mContext);
//		mPathMenu2.setLength(0.5f);
//		ibPathMenuMainBtn2 = (ImageButton) findViewById(R.id.btnPathMainBtn2);
//		mPathMenu2.setMainBtn(ibPathMenuMainBtn2);
//		//		mPathMenu2.setDirection(C.HORIZONTAL_RIGHT);
//		mPathMenu2.setDirection(C.VERTICAL_TOP);
//		mPathMenu2.setPathMenuOnClickListener(this);
////		mPathMenu2.setMainBtnRotation(1);
////		mPathMenu2.setDuration(1000);
////		mPathMenu2.setSubBtnRotation(2);
////		mPathMenu2.setSubDuration(1500);
//
//		mPathMenu2.addSubBtn((PathMenuBtn) findViewById(R.id.btnPathSub2_1));
//		mPathMenu2.addSubBtn((PathMenuBtn) findViewById(R.id.btnPathSub2_2));
//		mPathMenu2.addSubBtn((PathMenuBtn) findViewById(R.id.btnPathSub2_3));
//		mPathMenu2.addSubBtn((PathMenuBtn) findViewById(R.id.btnPathSub2_4));
//		mPathMenu2.addSubBtn((PathMenuBtn) findViewById(R.id.btnPathSub2_5));
		
		/** New PathMenu **/
		mPathMenu = (AnimationMenu) findViewById(R.id.lhy_PathMenu1);
		mPathMenu.setAnimationMenuOnClickListener(this);
		mPathMenu.setDirection(C.LEFT_BOTTOM);
		ibPathMenuMainBtn = (ImageButton) findViewById(R.id.btnPathMainBtn);
		mPathMenu.setMainBtn(ibPathMenuMainBtn);
		
		mPathMenu.addSubBtn(R.drawable.btn1back, 50, 50, "우히히");
		mPathMenu.addSubBtn(R.drawable.btn2back, 50, 50);
		mPathMenu.addSubBtn(R.drawable.btn3back, 50, 50, "우희희희");
		mPathMenu.addSubBtn(R.drawable.btn4back, 50, 50);
		mPathMenu.addSubBtn(R.drawable.btn5back, 50, 50, "우우우우우");
//		mPathMenu.addSubBtn(R.drawable.arrow);
//		mPathMenu.addSubBtn(R.drawable.arrow);
//		mPathMenu.addSubBtn(R.drawable.arrow);
//		mPathMenu.addSubBtn(R.drawable.arrow);
//		mPathMenu.addSubBtn(R.drawable.simyeong);
//		mPathMenu.addSubBtn(R.drawable.simyeong2);
//		mPathMenu.addSubBtn(R.drawable.simyeong3);
//		mPathMenu.addSubBtn(R.drawable.simyeong);
		
		mPathMenu2 = (AnimationMenu) findViewById(R.id.lhy_PathMenu2);
		mPathMenu2.setLength(0.8f);
		mPathMenu2.setAnimationMenuOnClickListener(this);
		mPathMenu2.setDirection(C.HORIZONTAL_RIGHT);
		ibPathMenuMainBtn2 = (ImageButton) findViewById(R.id.btnPathMainBtn2);
		mPathMenu2.setMainBtn(ibPathMenuMainBtn2);
		
		mPathMenu2.addSubBtn(R.drawable.btn1back, 40, 40, "Home");
		mPathMenu2.addSubBtn(R.drawable.btn2back, 40, 40, "MyPage");
		mPathMenu2.addSubBtn(R.drawable.btn3back, 40, 40, "Scrap");
		mPathMenu2.addSubBtn(R.drawable.btn4back, 40, 40, "Note");
		mPathMenu2.addSubBtn(R.drawable.btn5back, 40, 40, "Setting");

		/** Direction Test Button **/
		btnLEFT_TOP = (Button) findViewById(R.id.btnPathMenuLEFT_TOP);
		btnLEFT_BOTTOM = (Button) findViewById(R.id.btnPathMenuLEFT_BOTTOM);
		btnRIGHT_TOP = (Button) findViewById(R.id.btnPathMenuRIGHT_TOP);
		btnRIGHT_BOTTOM = (Button) findViewById(R.id.btnPathMenuRIGHT_BOTTOM);
		btnROUND = (Button) findViewById(R.id.btnPathMenuROUND);
		btnTOP = (Button) findViewById(R.id.btnPathMenuTOP);
		btnBOTTOM = (Button) findViewById(R.id.btnPathMenuBOTTOM);
		btnTOP_INV = (Button) findViewById(R.id.btnPathMenuTOP_INV);
		btnBOTTOM_INV = (Button) findViewById(R.id.btnPathMenuBOTTOM_INV);
		btnHORIZONTAL = (Button) findViewById(R.id.btnPathMenuHORIZONTAL);
		btnVERTICAL = (Button) findViewById(R.id.btnPathMenuVERTICAL);

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
		tbtnEffectToggle1 = (ToggleButton) findViewById(R.id.tbtnPathMenu1);
		tbtnEffectToggle2 = (ToggleButton) findViewById(R.id.tbtnPathMenu2);
		tbtnEffectToggle1.setOnClickListener(this);
		tbtnEffectToggle2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.tbtnPathMenu1) {
			tbtnEffectToggle1.toggle();
			if(tbtnEffectToggle1.isChecked()){
				mPathMenu.subBtnEffect1Enable(false);
				tbtnEffectToggle2.setChecked(false);
				tbtnEffectToggle1.setChecked(false);
			} else{
				mPathMenu.subBtnEffect1Enable(true);
				tbtnEffectToggle2.setChecked(false);
				tbtnEffectToggle1.setChecked(true);
			}
		} else if (id == R.id.tbtnPathMenu2) {
			tbtnEffectToggle2.toggle();
			if(tbtnEffectToggle2.isChecked()){
				mPathMenu.subBtnEffect2Enable(false);
				tbtnEffectToggle1.setChecked(false);
				tbtnEffectToggle2.setChecked(false);
				Log.d(TAG, "Toggle1 Checked");
			} else{
				mPathMenu.subBtnEffect2Enable(true);
				tbtnEffectToggle1.setChecked(false);
				tbtnEffectToggle2.setChecked(true);
			}
		}

		/** Direction Test Button **/
		if(!mPathMenu.isOpened()){
			int id1 = v.getId();
			if (id1 == R.id.btnPathMenuLEFT_TOP) {
				mPathMenu.setDirection(C.LEFT_TOP);
				makeToast("왼쪽상단");
			} else if (id1 == R.id.btnPathMenuLEFT_BOTTOM) {
				mPathMenu.setDirection(C.LEFT_BOTTOM);
				makeToast("왼쪽하단");
			} else if (id1 == R.id.btnPathMenuRIGHT_TOP) {
				mPathMenu.setDirection(C.RIGHT_TOP);
				makeToast("오른쪽상단");
			} else if (id1 == R.id.btnPathMenuRIGHT_BOTTOM) {
				mPathMenu.setDirection(C.RIGHT_BOTTOM);
				makeToast("오른쪽하단");
			} else if (id1 == R.id.btnPathMenuROUND) {
				mPathMenu.setDirection(C.ROUND);
				makeToast("원형");
			} else if (id1 == R.id.btnPathMenuTOP) {
				mPathMenu.setDirection(C.TOP);
				makeToast("상단");
			} else if (id1 == R.id.btnPathMenuTOP_INV) {
				mPathMenu.setDirection(C.TOP_INVERSE);
				makeToast("상단 역순");
			} else if (id1 == R.id.btnPathMenuBOTTOM) {
				mPathMenu.setDirection(C.BOTTOM);
				makeToast("하단");
			} else if (id1 == R.id.btnPathMenuBOTTOM_INV) {
				mPathMenu.setDirection(C.BOTTOM_INVERSE);
				makeToast("하단 역순");
			}
		}
		if(!mPathMenu2.isOpened()){
			int id1 = v.getId();
			if (id1 == R.id.btnPathMenuHORIZONTAL) {
				mPathMenu2.setLength(0.8f);
				mPathMenu2.setDirection(C.HORIZONTAL_RIGHT);
				makeToast("가로우측, 80%");
			} else if (id1 == R.id.btnPathMenuVERTICAL) {
				mPathMenu2.setLength(0.5f);
				mPathMenu2.setDirection(C.VERTICAL_TOP);
				makeToast("세로상단 50%");
			}
		}
	}

	private void makeToast(String value){
		Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onMainBtnClick(AnimationMenu pathMenu) {
		if(pathMenu==mPathMenu){
//			Toast.makeText(mContext, "PathMenu1 Main", Toast.LENGTH_SHORT).show();
		} else if(pathMenu==mPathMenu2){
//			Toast.makeText(mContext, "PathMenu2 Main", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onSubBtnClick(AnimationMenu pathMenu, int index) {
		Log.d(TAG, "onSubBtnClick : " + index);
		if(pathMenu==mPathMenu){
			switch(index){
			case 0:
				Toast.makeText(mContext, "PathMenu1 Sub1", Toast.LENGTH_SHORT).show();
				break;
			}
		} else if(pathMenu==mPathMenu2){
			switch(index){
			case 0:
				Toast.makeText(mContext, "PathMenu2 Sub1", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	}
	

}
