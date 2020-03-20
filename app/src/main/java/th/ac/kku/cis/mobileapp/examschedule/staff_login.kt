package th.ac.kku.cis.mobileapp.examschedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_staff_login.*

class staff_login : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    private val TAG: String = "Staff Login Page"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_login)

        mAuth = FirebaseAuth.getInstance()
        if(mAuth!!.currentUser != null){
            Log.d(TAG,"Continue with: "+mAuth!!.currentUser!!.email)
            startActivity(Intent(this,staff_activity::class.java))
            finish()
        }
        btn_login.setOnClickListener{
            val email = tb_getemail.text.toString().trim{it <= ' '}
            val password = tb_getpassword.text.toString().trim{it <= ' '}

            if(email.isEmpty()){
                Toast.makeText(this,"กรุณาใส่ email ",Toast.LENGTH_SHORT).show()
                //toast("กรุณาใส่ email ")
                Log.d(TAG,"Email was empty!")
                return@setOnClickListener
            }
            if(password.isEmpty()){
                Toast.makeText(this,"กรุณาใส่ email ",Toast.LENGTH_SHORT).show()
                //toast("กรุณาใส่ email ")
                Log.d(TAG,"Email was empty!")
                return@setOnClickListener
            }
            mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (!task.isSuccessful){
                    if(password.length < 6) {
                        tb_getpassword.error = "รหัสผ่านต้องมีอย่างน้อย 6 ตัว"
                        Log.d(TAG, "รหัสผ่านไม่ถึง 6 ตัว")
                    }
                    else {
                        Toast.makeText(this,"ชื่อผู้ใช้งานหรือรหัสผ่านไม่ถูกต้อง",Toast.LENGTH_SHORT).show()
                        //toast("Authentication Failed : " + task.exception)
                        Log.d(TAG, "ชื่อผู้ใช้งานหรือรหัสผ่านไม่ถูกต้อง " + task.exception)
                    }
                } else{
                    Toast.makeText(this,"เข้าสู่ระบบสำเร็จ",Toast.LENGTH_SHORT).show()
                    //toast("Sign in Successfully!")
                    Log.d(TAG, "เข้าสู่ระบบสำเร็จ !!")
                    startActivity(Intent(this,staff_activity::class.java))
                    finish()
                    }
                }
            }
    }

    fun goMain(view: View){
        val l = Intent(this,MainActivity::class.java)
        startActivity(l)
    }
}