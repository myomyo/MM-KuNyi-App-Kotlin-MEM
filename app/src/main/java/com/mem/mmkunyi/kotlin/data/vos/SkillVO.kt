package com.mem.mmkunyi.kotlin.data.vos

import com.google.gson.annotations.SerializedName

class SkillVO(@SerializedName("skillId") var skillId : Int = 0,
              @SerializedName("skillName") var skillName : String = "")