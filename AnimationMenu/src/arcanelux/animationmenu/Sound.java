package arcanelux.animationmenu;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Sound {
	/** 사운드풀 관련 변수 **/
	static SoundPool soundPool; 
	static HashMap<String, Integer> soundList;
	
	public Sound(){
		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		soundList = new HashMap<String, Integer>();
	}
	
	public Sound(int stream){
		soundPool = new SoundPool(stream, AudioManager.STREAM_MUSIC, 0);
		soundList = new HashMap<String, Integer>();
	}

	public void setSoundFile(Context context, int res, String key){
		soundList.put(key, soundPool.load(context, res, 0));
	}
	
	public void playSound(String key){
		soundPool.play(soundList.get(key), 1, 1, 0, 0, 1);
	}
	public void playSound(String key, float volume){
		soundPool.play(soundList.get(key), volume, volume, 0, 0, 1);
	}
//
//	/** 사운드풀 할당 **/
//	soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
//	sound[0] = soundPool.load(this, R.raw.ddukbaegi, 1);
//	sound[1] = soundPool.load(this, R.raw.iwant, 1);
}