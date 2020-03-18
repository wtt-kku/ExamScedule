package th.ac.kku.cis.mobileapp.examschedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var IDStudent:String = ""
    private val TAG: String = "MainActivity"
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      database = FirebaseDatabase.getInstance().getReference("Student")
        btn_check.setOnClickListener {
            IDStudent = tb_getid.text.toString()
            //Toast.makeText(this,"ID="+IDSt,Toast.LENGTH_SHORT).show()
            checkID(IDStudent)

            //val l = Intent(this,ListSubject::class.java)
            //startActivity(l)
        }

    }

    fun checkID(id:String){
        FirebaseDatabase.getInstance().reference.child("Student").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.hasChild(id)){
                    Toast.makeText(this@MainActivity,"ยินดีด้วยพบรหัส "+IDStudent +" ในระบบ",Toast.LENGTH_SHORT).show()
                    Log.d(TAG,"ไม่พบรหัส "+IDStudent +"ในระบบ")
                    val l = Intent(this@MainActivity,ListSubject::class.java)
                    l.putExtra("idStudent",IDStudent)
                    startActivity(l)

                }
                else{
                    Toast.makeText(this@MainActivity,"ไม่พบรหัส "+IDStudent +" ในระบบ \nกรุณาลองอีกครั้ง",Toast.LENGTH_LONG).show()
                    Log.d(TAG,"ไม่พบรหัส "+IDStudent +"ในระบบ")
                }

            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun goStaffLogin(view: View){
        val i = Intent(this,staff_login::class.java)
        startActivity(i)
    }

}
