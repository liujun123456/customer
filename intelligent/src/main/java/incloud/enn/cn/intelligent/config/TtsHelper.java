package incloud.enn.cn.intelligent.config;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

/**
 * @author AsureLiu on 2017/12/25.
 */

public class TtsHelper {
    public static TtsHelper instance;
    private SpeechSynthesizer mTts;
    private static Context mContext;
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    private final static String catherine="xiaoyan";
    private final static String henry="xiaoyu";
    private final static String vimary="vixy";


    public static TtsHelper getInstance(Context context){
        mContext=context;
        if (instance==null){
            instance=create();
        }
        return instance;
    }
    private static synchronized TtsHelper create(){
        return new TtsHelper();
    }
    public void init(){
        mTts= SpeechSynthesizer.createSynthesizer(mContext,mTtsInitListener);
    }
    public void speaking(String value){
        mTts.startSpeaking(value,mTtsListener);

    }
    private InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                Log.e("tts---->","初始化失败,错误码："+code);
            } else {
                // 初始化成功，之后可以调用startSpeaking方法
                // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                // 正确的做法是将onCreate中的startSpeaking调用移至这里
                setParam();
            }
        }
    };
    private SynthesizerListener mTtsListener = new SynthesizerListener(){

        @Override
        public void onSpeakBegin() {

        }

        @Override
        public void onBufferProgress(int i, int i1, int i2, String s) {

        }

        @Override
        public void onSpeakPaused() {

        }

        @Override
        public void onSpeakResumed() {

        }

        @Override
        public void onSpeakProgress(int i, int i1, int i2) {

        }

        @Override
        public void onCompleted(SpeechError speechError) {

        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };
    private void setParam(){
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        // 根据合成引擎设置相应参数
        if(mEngineType.equals(SpeechConstant.TYPE_CLOUD)) {
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
            // 设置在线合成发音人
            mTts.setParameter(SpeechConstant.VOICE_NAME, catherine);
            //设置合成语速
            mTts.setParameter(SpeechConstant.SPEED,"50");
            //设置合成音调
            mTts.setParameter(SpeechConstant.PITCH,"50");
            //设置合成音量
            mTts.setParameter(SpeechConstant.VOLUME, "80");
        }else {
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
            // 设置本地合成发音人 voicer为空，默认通过语记界面指定发音人。
            mTts.setParameter(SpeechConstant.VOICE_NAME, "");
            /**
             * TODO 本地合成不设置语速、音调、音量，默认使用语记设置
             * 开发者如需自定义参数，请参考在线合成参数设置
             */
        }
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
    }
}
