package com.mem.mmkunyi.kotlin.data.vos

import com.google.gson.annotations.SerializedName

class ViewerVO {

    @SerializedName("seekerId")
    val seekerId: Int = 0

    @SerializedName("seekerName")
    val seekerName: String = ""

    @SerializedName("seekerProfilePicUrl")
    val seekerProfilePicUrl: String = ""

    @SerializedName("seekerSkill")
    val seekerSkill: List<SkillVO> = ArrayList()

    @SerializedName("viewedTimeStamp")
    val viewedTimeStamp: String = ""
}