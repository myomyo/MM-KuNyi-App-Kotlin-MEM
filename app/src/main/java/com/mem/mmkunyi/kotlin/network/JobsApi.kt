package com.mem.mmkunyi.kotlin.network

import com.mem.mmkunyi.kotlin.network.responses.GetJobsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface JobsApi {

    @FormUrlEncoded
    @POST("getJobs.php")
    fun loadJobs(
            @Field("page") pageIndex: Int,
            @Field("access_token") accessToken: String) : Call<GetJobsResponse>
}