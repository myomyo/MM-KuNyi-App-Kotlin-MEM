package com.mem.mmkunyi.kotlin.views.holders


import android.view.View
import com.mem.mmkunyi.kotlin.R
import com.mem.mmkunyi.kotlin.R.id.tvPostedTime
import com.mem.mmkunyi.kotlin.data.vos.JobVO
import com.mem.mmkunyi.kotlin.delegates.JobItemDelegate
import kotlinx.android.synthetic.main.view_item_jobs.view.*

class JobsViewHolder(itemView: View, private val mDelegate: JobItemDelegate) : BaseViewHolder<JobVO>(itemView) {
    override fun setData(data: JobVO) {

        mData = data

        itemView.tvJobTitle.text = data.shortDesc
        itemView.tvJobDesc.text = data.fullDesc
        itemView.tvPlace.text = data.location

        val offerAmount = itemView.context.getString(R.string.format_offer_amount, data.offerAmount!!.amount, data.offerAmount.duration)
        itemView.tvOfferAmount.text = offerAmount

        itemView.tvPostedTime.text = itemView.context.getString(R.string.format_working_day,data.jobDuration!!.totalWorkingDays)


    }

    override fun onClick(v: View?) {
        mDelegate.onTabJobItem(mData)
    }

}