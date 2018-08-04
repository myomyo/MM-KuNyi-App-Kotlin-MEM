package com.mem.mmkunyi.kotlin.delegates

import com.mem.mmkunyi.kotlin.data.vos.JobVO

interface JobItemDelegate {

    fun onTabJobItem(job: JobVO?)
}