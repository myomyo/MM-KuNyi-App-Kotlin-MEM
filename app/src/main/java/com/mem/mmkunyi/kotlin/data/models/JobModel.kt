package com.mem.mmkunyi.kotlin.data.models

import com.mem.mmkunyi.kotlin.data.vos.JobVO
import com.mem.mmkunyi.kotlin.events.DataEvent
import com.mem.mmkunyi.kotlin.network.JobsDataAgent
import com.mem.mmkunyi.kotlin.utils.AppConstants
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class JobModel {
    companion object {
        private var INSTANCE: JobModel? = null

        fun getInstance(): JobModel {
            if (INSTANCE == null)
                INSTANCE = JobModel()

            var i = INSTANCE
            return i!!
        }
    }

    private constructor() {
        EventBus.getDefault().register(this)
    }

    private var mJobsPage: Int = 1
    private var mJobsData: HashMap<Int, JobVO> = HashMap()

    fun loadJobs() {

        JobsDataAgent.getInstance().loadJobs(AppConstants.ACCESS_TOKEN, mJobsPage)
    }

    fun forceLoadJobs() {
        mJobsPage = 1
        mJobsData = HashMap()
        JobsDataAgent.getInstance().loadJobs(AppConstants.ACCESS_TOKEN, mJobsPage)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onJobsLoadedEvent(jobsLoadedEvent: DataEvent.JobsLoadedEvent){
        for(job : JobVO in jobsLoadedEvent.loadedJobs){
            mJobsData[job.jobPostId] = job
        }
        //mJobsPage = jobsLoadedEvent.loadedPageIndex +1
    }

    fun getJobByJobPostId(jobId: Int): JobVO?{
        return mJobsData[jobId]
    }
}