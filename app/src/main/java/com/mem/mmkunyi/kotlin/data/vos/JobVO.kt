package com.mem.mmkunyi.kotlin.data.vos

import com.google.gson.annotations.SerializedName

class JobVO {

    @SerializedName("applicant")
    val applicant: List<ApplicantVO> = ArrayList()

    @SerializedName("availablePostCount")
    val availablePostCount: Int = 0

    @SerializedName("email")
    val email: String = ""

    @SerializedName("fullDesc")
    val fullDesc: String = ""

    @SerializedName("genderForJob")
    val genderForJob: Int = 0

    @SerializedName("images")
    val images: List<String> = ArrayList()

    @SerializedName("importantNotes")
    val importantNotes: List<String> = ArrayList()

    @SerializedName("interested")
    val interested: List<InterestedVO> = ArrayList()

    @SerializedName("isActive")
    val isActive: Boolean = false

    @SerializedName("jobDuration")
    val jobDuration: JobDurationVO? = null

    @SerializedName("jobPostId")
    val jobPostId: Int = 0

    @SerializedName("jobTags")
    val jobTags: List<JobTagVO> = ArrayList()

    @SerializedName("location")
    val location: String = ""

    @SerializedName("makeMoneyRating")
    val makeMoneyRating: Int = 0

    @SerializedName("offerAmount")
    val offerAmount: OfferAmountVO? = null

    @SerializedName("phoneNo")
    val phoneNo: String = ""

    @SerializedName("postClosedDate")
    val postClosedDate: String = ""

    @SerializedName("postedDate")
    val postedDate: String = ""

    @SerializedName("relevant")
    val relevant: List<RelevantVO> = ArrayList()

    @SerializedName("requiredSkill")
    val requiredSkill: List<SkillVO> = ArrayList()

    @SerializedName("shortDesc")
    val shortDesc: String = ""

    @SerializedName("viewed")
    val viewed: List<ViewerVO> = ArrayList()


}