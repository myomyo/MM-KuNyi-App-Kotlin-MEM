package com.mem.mmkunyi.kotlin.activities

import android.os.Bundle
import android.util.Log
import com.mem.mmkunyi.kotlin.R
import com.mem.mmkunyi.kotlin.data.models.JobModel
import com.mem.mmkunyi.kotlin.data.vos.JobVO
import com.mem.mmkunyi.kotlin.utils.AppConstants
import kotlinx.android.synthetic.main.activity_job_details.*

class JobDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_details)
        val jobId : Int = intent.getIntExtra(AppConstants.JOB_ID,-1)
        Log.d("JobDetail ","JobId : "+jobId.toString())
        bindData(JobModel.getInstance().getJobByJobPostId(jobId)!!)
    }

    private fun bindData(job : JobVO){

        tvJobTitle.text = job.shortDesc
        tvLocation.text = job.location
        tvPostedOn.text = job.postedDate
        tvVacancy.text = job.availablePostCount.toString()

        val offerAmount = tvPayment.context.getString(R.string.format_offer_amount, job.offerAmount!!.amount, job.offerAmount.duration)
        tvPayment.text = offerAmount
        if(job.jobDuration != null){
            tvPeriod.text = job.jobDuration.totalWorkingDays.toString()
        }

        if(!job.requiredSkill.isEmpty()){
            tvSkill.text = job.requiredSkill[0].skillName
        }

    }
}