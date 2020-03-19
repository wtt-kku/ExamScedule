package th.ac.kku.cis.mobileapp.examschedule.Model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class RegisSub(
    var RegisSub: String? = "",
    var Seat:String? = ""

)