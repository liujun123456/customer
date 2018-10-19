package incloud.enn.cn.intelligent.config;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import incloud.enn.cn.intelligent.activities.intelligent.view.IntelligentView;

/**
 * @author AsureLiu on 2017/12/21.
 */

public class SpeechConfig {
    private SpeechRecognizer recognizer;
    private Context mContext;
    private IntelligentView chatView;

    public SpeechConfig(Context mContext, IntelligentView chatView){
        this.mContext=mContext;
        this.chatView=chatView;
    }

    public void initSpeech(){
        SpeechUtility.createUtility(mContext, SpeechConstant.APPID+"=5a37884b");
        recognizer= SpeechRecognizer.createRecognizer(mContext,initListener);
        recognizer.setParameter(SpeechConstant.DOMAIN, "iat");
        recognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        recognizer.setParameter(SpeechConstant.ACCENT, "mandarin");
        recognizer.setParameter(SpeechConstant.VAD_BOS, "60000");
        recognizer.setParameter(SpeechConstant.VAD_EOS, "60000");
        recognizer.setParameter(SpeechConstant.KEY_SPEECH_TIMEOUT, "60000");
        recognizer.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        recognizer.setParameter(SpeechConstant.ASR_AUDIO_PATH, getWavFilePath());
    }
    public void startSpeech(){
        recognizer.setParameter(SpeechConstant.ASR_AUDIO_PATH, getWavFilePath());
        recognizer.startListening(recognizerListener);
    }
    public void stopSpeech(){
        if (recognizer.isListening()){
            recognizer.stopListening();
        }
    }
    public StringBuilder mAudioWavPath ;

    public String getWavFilePath(){
        if(isSdcardExit()){
            String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            mAudioWavPath=new StringBuilder(fileBasePath+"/"+System.currentTimeMillis()+".wav");
        }
        return mAudioWavPath==null?"":mAudioWavPath.toString();
    }
    public static boolean isSdcardExit(){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }
    private InitListener initListener=new InitListener() {
        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                Toast.makeText(mContext,"初始化失败，错误码：" + code, Toast.LENGTH_SHORT).show();
            }
        }
    };
    private StringBuilder sentence;
    private Long currentTime;
    private RecognizerListener recognizerListener=new RecognizerListener() {
        @Override
        public void onVolumeChanged(int i, byte[] bytes) {

        }

        @Override
        public void onBeginOfSpeech() {
            sentence= new StringBuilder();
            currentTime=System.currentTimeMillis();

        }

        @Override
        public void onEndOfSpeech() {


        }

        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            String result = recognizerResult.getResultString();
            try {
                //处理json结果
                JSONObject jsonObject = new JSONObject(result);
                JSONArray words = jsonObject.getJSONArray("ws");
                //拼成句子
                for( int i = 0 ; i < words.length() ; i ++ ){
                    JSONObject word = words.getJSONObject(i);
                    JSONArray subArray = word.getJSONArray("cw");
                    JSONObject subWord = subArray.getJSONObject(0);
                    String character = subWord.getString("w");
                    sentence.append(character);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (b){
                chatView.mscValue(sentence.toString());
            }
        }

        @Override
        public void onError(SpeechError speechError) {
            Toast.makeText(mContext,speechError.getErrorDescription(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };
}
