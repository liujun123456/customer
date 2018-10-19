package incloud.enn.cn.intelligent.activities.intelligent

import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import android.widget.RelativeLayout
import incloud.enn.cn.commonlib.BaseActivity
import incloud.enn.cn.commonlib.utils.LogUtil
import incloud.enn.cn.commonlib.utils.ScreenUtils
import incloud.enn.cn.commonlib.view.GridSpacingItemDecoration
import incloud.enn.cn.intelligent.R
import incloud.enn.cn.intelligent.activities.intelligent.adapter.ChatAdapter
import incloud.enn.cn.intelligent.activities.intelligent.beans.*
import incloud.enn.cn.intelligent.activities.intelligent.view.IntelligentView
import incloud.enn.cn.intelligent.activities.intelligent.presenter.IntelligentPresenter
import incloud.enn.cn.intelligent.config.SpeechConfig
import incloud.enn.cn.intelligent.config.TtsHelper
import incloud.enn.cn.intelligent.view.RecordTextView
import kotlinx.android.synthetic.main.activity_intelligent_layout.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.toast

/**
 * @author  AsureLiu on 2018/9/7.
 */
class IntelligentActivity:BaseActivity(), RecordTextView.RecordListener, IntelligentView {

    override fun getToolBarResID(): Int =R.layout.title_intelligent_layout

    override fun setTitleColor(): Int=R.color.all_transparent

    override fun getMainContentResId(): Int = R.layout.activity_intelligent_layout

    override fun isKeepFullScreen(): Boolean =false

    lateinit var speech:SpeechConfig
    private var currentStyle = 2 //1文字  2//语音
    private val minHeight = 50
    private var oldBottom = 0
    private var newBottom = 0
    private val rect = Rect()
    private var msgList:MutableList<BaseChatBean> = arrayListOf()
    private var mAdapter:ChatAdapter?=null
    private var mPresenter:IntelligentPresenter?=null
    private var currentAnswer = AnswerModel()
    private var userID: Int = 0

    override fun afterView() {
        LogUtil.DEBUG=true
        root_content.setBackgroundColor(ContextCompat.getColor(mContext,R.color.intelligent_bg))
        userID = intent.getIntExtra("userID", 186)
        initSpeech()
        initListener()
        initRecyclerView()
        initAdapterAndPresenter()
    }

    private fun initListener(){
        record_voice_text.setRecordListener(this)
        change_input.setOnClickListener { changeInput() }
        content_layout.viewTreeObserver.addOnGlobalLayoutListener {
            oldBottom = rect.bottom
            content_layout.getWindowVisibleDisplayFrame(rect)
            newBottom = rect.bottom
            if (oldBottom - newBottom > minHeight) {
                val params = layout_bottom.layoutParams as RelativeLayout.LayoutParams
                params.setMargins(0, 0, 0, oldBottom - newBottom)
                layout_bottom.layoutParams = params

                val params2 = bottom_layout.layoutParams as RelativeLayout.LayoutParams
                params2.setMargins(0, 0, 0, oldBottom - newBottom)
                bottom_layout.layoutParams = params2


                chat_list.scrollToPosition(msgList.size - 1)
            } else if (newBottom - oldBottom > minHeight) {
                val params = layout_bottom.layoutParams as RelativeLayout.LayoutParams
                params.setMargins(0, 0, 0, 0)
                layout_bottom.layoutParams = params

                val params2 = bottom_layout.layoutParams as RelativeLayout.LayoutParams
                params2.setMargins(0, 0, 0, 0)
                bottom_layout.layoutParams = params2
            }
        }
        send_action.setOnClickListener {
            if (TextUtils.isEmpty(input_value.text.toString())){
                toast("请输入你想说的内容!")
                return@setOnClickListener

            }
            buildMineMsg(input_value.text.toString())
            input_value.setText("")
        }
    }
    private fun initRecyclerView(){
        var mLayoutManager=LinearLayoutManager(mContext)
        chat_list.layoutManager = mLayoutManager
        // 设置Item增加、移除默认动画
        chat_list.itemAnimator = DefaultItemAnimator()
        chat_list.setHasFixedSize(true)
        chat_list.addItemDecoration(GridSpacingItemDecoration(1,ScreenUtils.dip2px(mContext,10f),false))

    }
    private fun initAdapterAndPresenter(){
        mPresenter=IntelligentPresenter(mContext,this)
        mAdapter=ChatAdapter(mContext,msgList)
        chat_list.adapter=mAdapter
        mPresenter?.doLabel(userID)
    }

    private fun initSpeech(){
        speech= SpeechConfig(mContext,this)
        speech.initSpeech()
        TtsHelper.getInstance(mContext).init()
    }
    private fun changeInput(){
        if (currentStyle==2){
            currentStyle=1
            change_input.imageResource=R.mipmap.icon_vo_s
            input_value.visibility=View.VISIBLE
            record_voice_text.visibility=View.GONE
        }else{
            currentStyle=2
            change_input.imageResource=R.mipmap.icon_kb_s
            input_value.visibility=View.GONE
            record_voice_text.visibility=View.VISIBLE
        }
    }
    private fun buildMineMsg(msg:String){
        var model=AnswerModel()
        model.itemType=ChatItemType.MINE_MSG_TYPE
        model.msgType=ChatMsgType.MSG_LOADING
        model.answer=msg
        msgList.add(model)
        mAdapter?.notifyItemInserted(msgList.size-1)
        chat_list.scrollToPosition(msgList.size - 1)
        mPresenter?.doChat(currentAnswer.entityId,currentAnswer.factorId,currentAnswer.intentId,currentAnswer.intentType,msg,userID,msgList.size-1)
        test(msg)
    }

    private fun test(msg:String){
        var model=AnswerModel()
        when(msg){
            "1"->{
                model.itemType=ChatItemType.MINE_STATE_TYPE
            }
            "2"->{
                model.itemType=ChatItemType.ABNORMAL_TYPE
            }
            "3"->{
                model.itemType=ChatItemType.REPORT_TYPE
            }
            "4"->{
                model.itemType=ChatItemType.EVALUATE_TYPE
            }
            "5"->{
                model.itemType= ChatItemType.END_TYPE
            }
        }
        model.answer=msg
        msgList.add(model)
        mAdapter?.notifyItemInserted(msgList.size-1)
        chat_list.scrollToPosition(msgList.size - 1)

    }
    private fun buildRobotMsg(msg:String){
        var model=AnswerModel()
        model.itemType=ChatItemType.ROBOT_MSG_TYPE
        if (TextUtils.isEmpty(msg)){
            model.answer="本宝宝不知道你在说什么!"
        }else{
            model.answer=msg
        }
        msgList.add(model)
        mAdapter?.notifyItemInserted(msgList.size-1)
        chat_list.scrollToPosition(msgList.size - 1)
    }

    override fun begin() {
        speech.startSpeech()
    }

    override fun end() {
        speech.stopSpeech()
    }
    override fun mscValue(value: String) {
        if (!TextUtils.isEmpty(value)){
            buildMineMsg(value)
        }
    }
    override fun onChat(isSuccess: Boolean,data: List<AnswerModel>,index:Int) {
        if (isSuccess&&data.size>0){
            msgList[index].msgType=ChatMsgType.MSG_SUCCESS
            mAdapter?.notifyItemChanged(index)
            currentAnswer=data[data.size-1]
            data.forEach { model->
                buildRobotMsg(model.answer)
            }
        }else{
            msgList[index].msgType=ChatMsgType.MSG_FAIL
            mAdapter?.notifyItemChanged(index)
        }
    }

    override fun onLabel(data: LabelModel) {
        if (data.labelList.isNotEmpty()){
            data.itemType=ChatItemType.MENU_TYPE
            msgList.add(data)
            mAdapter?.notifyItemInserted(msgList.size-1)
            chat_list.scrollToPosition(msgList.size - 1)
        }
    }

}