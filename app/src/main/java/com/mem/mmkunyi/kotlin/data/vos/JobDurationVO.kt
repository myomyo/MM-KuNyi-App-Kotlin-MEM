package com.mem.mmkunyi.kotlin.data.vos

import com.google.gson.annotations.SerializedName

class JobDurationVO {

    @SerializedName("jobEndDate")
    val jobEndDate: String = ""

    @SerializedName("jobStartDate")
    val jobStartDate: String = ""

    @SerializedName("totalWorkingDays")
    val totalWorkingDays: Int = 0

    @SerializedName("workingDaysPerWeek")
    val workingDaysPerWeek: Int = 0

    @SerializedName("workingHoursPerDay")
    val workingHoursPerDay: Double = 0.0

}