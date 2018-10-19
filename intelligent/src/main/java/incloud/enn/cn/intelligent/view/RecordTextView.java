package incloud.enn.cn.intelligent.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

import incloud.enn.cn.intelligent.R;

/**
 * @author AsureLiu on 2017/12/19.
 */

public class RecordTextView extends AppCompatTextView {
    public RecordTextView(Context context) {
        super(context);
        initView();
    }

    public RecordTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RecordTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setGravity(Gravity.CENTER);
        setBackgroundResource(R.drawable.hollow_text_bg);
        setText(R.string.press_voice);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (listener != null) {
                    listener.begin();
                    setBackgroundResource(R.drawable.hollow_text_press_bg);
                    setText(R.string.start_voice);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (listener != null) {
                    listener.end();
                    setBackgroundResource(R.drawable.hollow_text_bg);
                    setText(R.string.press_voice);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if (listener != null) {
                    listener.end();
                    setBackgroundResource(R.drawable.hollow_text_bg);
                    setText(R.string.press_voice);
                }
                break;
        }
        return true;
    }

    public interface RecordListener {
        void begin();

        void end();
    }

    public RecordListener listener;

    public void setRecordListener(RecordListener listener) {
        this.listener = listener;
    }
}
