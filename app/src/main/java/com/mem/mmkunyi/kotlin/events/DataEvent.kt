package com.mem.mmkunyi.kotlin.events

import com.mem.mmkunyi.kotlin.data.vos.JobVO

class DataEvent {

    class JobsLoadedEvent(val loadedJobs: List<JobVO>)

    class EmptyDataLoadedEvent(val errorMsg: String? = "Empty Body Response")
}