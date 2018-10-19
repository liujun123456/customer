package incloud.enn.cn.intelligent.activities.intelligent.model

import incloud.enn.cn.commonlib.retrofit.bean.EnnResponseData
import incloud.enn.cn.commonlib.retrofit.bean.ReturnInterface
import incloud.enn.cn.commonlib.retrofit.commen.Impl.RetrofitClientImpl
import incloud.enn.cn.commonlib.retrofit.commen.RetrofitClient
import incloud.enn.cn.intelligent.request.AnswerReq
import incloud.enn.cn.intelligent.request.ChatRequest
import incloud.enn.cn.intelligent.request.LabelReq
import incloud.enn.cn.intelligent.response.AnswerResponse
import incloud.enn.cn.intelligent.response.LabelResponse

/**
 * @author  AsureLiu on 2018/9/10.
 */
class IntelligentModelImpl(var listener:IntelligentListener):IntelligentModel {

    val baseUrl="http://lk-intelligent-question-qa.cloud.enndata.cn/"
    lateinit var client: RetrofitClient
    override fun getChat(req:AnswerReq,index:Int) {
        client=RetrofitClientImpl(baseUrl)
        client.setInterface(object :ReturnInterface{
            override fun onSuccess(p0: EnnResponseData) {
                if (p0.success){
                    val response = p0 as AnswerResponse
                    listener.onChat(true,response.data,index)
                }else{
                    listener.onChat(false, arrayListOf(),index)
                }

            }

            override fun onFailure(p0: Throwable?) {
                listener.onChat(false, null,index)
            }

            override fun onComplete() {

            }

            override fun onProgress(p0: Int) {

            }

        }).Build()
        var  request=client.getCallClass(ChatRequest::class.java)
        client.request(request.getAnswer(req))
    }

    override fun getLabel(req: LabelReq) {
        client=RetrofitClientImpl(baseUrl)
        client.setInterface(object :ReturnInterface{
            override fun onSuccess(p0: EnnResponseData) {
                if (p0.success){
                    val response = p0 as LabelResponse
                    listener.onLabel(true,response.data)
                }else{
                    listener.onLabel(false, null)
                }
            }

            override fun onFailure(p0: Throwable?) {
                listener.onLabel(false, null)
            }

            override fun onComplete() {

            }

            override fun onProgress(p0: Int) {

            }

        }).Build()
        var  request=client.getCallClass(ChatRequest::class.java)
        client.request(request.getLabel(req))
    }

}