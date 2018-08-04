package com.mem.mmkunyi.kotlin.network.responses

import com.google.gson.annotations.SerializedName
import com.mem.mmkunyi.kotlin.data.vos.JobVO

class GetJobsResponse {

    @SerializedName("code")
    private val code: Int = 0

    @SerializedName("message")
    private val message: String? = null

    @SerializedName("apiVersion")
    private val apiVersion: String? = null

    @SerializedName("jobs")
    private var jobsList : List<JobVO>? = null

    fun getJobsList(): List<JobVO>{
        if(jobsList == null){
                jobsList = ArrayList<JobVO>()
        }
        val jobsListVal = jobsList
        return jobsListVal!!
    }

    fun getCode(): Int {
        return code
    }

    fun getMessage(): String? {
        return message
    }

    fun getApiVersion():String?{
        return apiVersion
    }
}