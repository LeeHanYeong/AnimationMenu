#AnimationMenu

Pop-up animation menu looks like the menu of [Path 2.0](https://path.com/) 
[Google play](https://play.google.com/store/apps/details?id=arcanelux.animationmenu.demo)


##Setup
이클립스에서 AnimationMenu 라이브러리 프로젝트를 Import합니다.

사용할 프로젝트에서 AnimationMenu 프로젝트를 라이브러리로 추가해줍니다.

##Usage
사용할 레이아웃 xml파일에 AnimationMenu를 추가합니다.

- AnimationMenu는 FrameLayout형태로 추가됩니다.

        <?xml version="1.0" encoding="utf-8"?>
        <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

             <arcanelux.animationmenu.AnimationMenu
           		android:layout_width="match_parent"
           		android:layout_height="match_parent"
           		android:id="@+id/animationMenu1">
           	</arcanelux.animationmenu.AnimationMenu>

        </FrameLayout>


추가한 AnimationMenu내에 MainButton으로 사용할 View를 추가합니다.

- 추가한 View는 xml의 미리보기에서 어떻게 나타날지 보여집니다.
- Code

        <?xml version="1.0" encoding="utf-8"?>
        <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

           	<arcanelux.animationmenu.AnimationMenu
           		android:layout_width="match_parent"
           		android:layout_height="match_parent"
           		android:id="@+id/animationMenu1">

				<ImageButton
					android:id="@+id/btnAnimationMenuMainBtn"
					android:layout_gravity="center"
					android:layout_width="wrap_content"
					android:layout_height="wrap_content"
					android:background="#00000000"
					android:src="@drawable/ic_launcher" />

           	</arcanelux.animationmenu.AnimationMenu>

        </FrameLayout>


자바파일에서 AnimationMenu를 추가합니다.

- AnimationMenu를 선언 및 초기화합니다.
- setLength. 반드시 가장 먼저 설정합니다.
- setDirection. 메뉴가 어떤 방향으로 펼쳐질지 정합니다.
- setMainBtn. MainButton으로 사용할 View의 id를 지정해줍니다.
- addSubBtn. SubButton으로 사용할 drawable의 id와 크기, BubbleMessage를 지정해줍니다.
- Code

		/** AnimationMenu **/
		mAnimationMenu = (AnimationMenu) findViewById(R.id.animationMenu1);
		mAnimationMenu.setDirection(C.LEFT_BOTTOM);
		ibAnimationMenuMainBtn = (ImageButton) findViewById(R.id.btnAnimationMenuMainBtn);
		mAnimationMenu.setMainBtn(ibAnimationMenuMainBtn);
		
		mAnimationMenu.addSubBtn(R.drawable.btn1back, 50, 50, "Button1");
		mAnimationMenu.addSubBtn(R.drawable.btn2back, 50, 50);
		mAnimationMenu.addSubBtn(R.drawable.btn3back, 50, 50, "Button2");
		mAnimationMenu.addSubBtn(R.drawable.btn4back, 50, 50);
		mAnimationMenu.addSubBtn(R.drawable.btn5back, 50, 50, "Button3");

setAnimationMenuOnClickListener로 버튼의 클릭이벤트를 설정해줍니다.

- Code

		mAnimationMenu.setAnimationMenuOnClickListener(this);

- AnimationMenu는 자체 OnClickListener인 AnimationMenuOnClickListener를 가지고 있습니다.
- Activity에서 구현시 Parameter에 this를 사용 후, Activity에 implements합니다.

		###Activity extends Activity implements AnimationMenuOnClickListener

- Listener에서 구현한 onMainBtnClick과 onSubBtnClick 함수에 Click 동작을 지정합니다.

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
