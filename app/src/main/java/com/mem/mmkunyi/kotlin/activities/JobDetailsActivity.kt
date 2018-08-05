package com.mem.mmkunyi.kotlin.activities

import android.os.Bundle
import android.util.Log
import com.mem.mmkunyi.kotlin.R
import com.mem.mmkunyi.kotlin.data.models.JobModel
import com.mem.mmkunyi.kotlin.data.vos.JobVO
import com.mem.mmkunyi.kotlin.utils.AppConstants
import kotlinx.android.synthetic.main.activity_job_details.*
import kotlinx.android.synthetic.main.view_item_jobs.*

class JobDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_details)

       val jobId : Int = intent.getIntExtra("jobId",-1)
        Log.d("JobDetailsActivity ","JobId : "+jobId.toString())
       bindData(JobModel.getInstance().getJobByJobPostId(jobId)!!)
    }

    private fun bindData(job : JobVO){

        tvTitle.text = job.shortDesc
        tvLocation.text = job.location
        tvPostedOn.text = job.postedDate
        tvVacancy.text = tvVacancy.context.getString(R.string.format_vacancy,job.availablePostCount)
        tvFullDesc.text = job.fullDesc

        val offerAmount = tvPayment.context.getString(R.string.format_offer_amount, job.offerAmount!!.amount, job.offerAmount.duration)
        tvPayment.text = offerAmount
        if(job.jobDuration != null){
            tvPeriod.text = tvPeriod.context.getString(R.string.format_working_day,job.jobDuration.totalWorkingDays)
        }

        if(!job.requiredSkill.isEmpty()){
            tvSkill.text = job.requiredSkill[0].skillName
        }

    }
}