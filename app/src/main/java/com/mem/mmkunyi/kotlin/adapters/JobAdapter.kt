package com.mem.mmkunyi.kotlin.adapters

import android.content.Context
import android.view.ViewGroup
import com.mem.mmkunyi.kotlin.R
import com.mem.mmkunyi.kotlin.data.vos.JobVO
import com.mem.mmkunyi.kotlin.delegates.JobItemDelegate
import com.mem.mmkunyi.kotlin.views.holders.BaseViewHolder
import com.mem.mmkunyi.kotlin.views.holders.JobsViewHolder

class JobAdapter(context: Context, private val mJobItemDelegate: JobItemDelegate) : BaseRecyclerAdapter<JobsViewHolder, JobVO>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<JobVO> {
        val jobItemView = mLayoutInflator.inflate(R.layout.view_item_jobs, parent, false)
        return JobsViewHolder(jobItemView, mJobItemDelegate)
    }


}