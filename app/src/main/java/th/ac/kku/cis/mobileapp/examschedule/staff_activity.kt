package th.ac.kku.cis.mobileapp.examschedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_staff_activity.*

class staff_activity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private val TAG: String = "Staff Page"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_activity)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser

        txtshowemail.text = user!!.email
        txtshowuid.text = user!!.uid

        btn_logout.setOnClickListener{
            mAuth!!.signOut()
            Toast.makeText(this,"ออกจากระบบสำเร็จ", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "ออกจากระบบสำเร็จ !!")
            startActivity(Intent(this,staff_login::class.java))
            finish()
        }
    }
}
