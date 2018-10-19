package incloud.enn.cn.intelligent.request

import incloud.enn.cn.intelligent.response.AnswerResponse
import incloud.enn.cn.intelligent.response.LabelResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author  AsureLiu on 2018/9/10.
 */
interface ChatRequest{
    @POST("intelligent/getItemList")
    fun getLabel(@Body req:LabelReq):Call<LabelResponse>

    @POST("intelligent/getAnswer")
    fun getAnswer(@Body req:AnswerReq):Call<AnswerResponse>
}
