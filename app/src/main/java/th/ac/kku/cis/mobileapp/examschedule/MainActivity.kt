package th.ac.kku.cis.mobileapp.examschedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import th.ac.kku.cis.mobileapp.examschedule.Model.RegisSub

class MainActivity : AppCompatActivity() {
    private var IDStudent:String = ""
    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_check.setOnClickListener {
            IDStudent = tb_getid.text.toString()
            //Toast.makeText(this,"ID="+IDSt,Toast.LENGTH_SHORT).show()
            if(IDStudent.trim(' ')=="") {
                Toast.makeText(this@MainActivity,"กรุณากรอกรหัสนักศึกษา",Toast.LENGTH_SHORT).show()
            }
            else if(IDStudent.length!=11){
                Toast.makeText(this@MainActivity,"รหัสนักศึกษาไม่ครบถ้วน",Toast.LENGTH_SHORT).show()
            }
            else{
                checkID(IDStudent)
            }

        }

    }

    fun checkID(id:String){
        FirebaseDatabase.getInstance().reference.child("Student").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {

                if(p0.hasChild(id)){
                    Toast.makeText(this@MainActivity,"ยินดีด้วยพบรหัส "+IDStudent +" ในระบบ",Toast.LENGTH_SHORT).show()
                    Log.d(TAG,"พบ "+IDStudent +"ในระบบ")
                    val StudentInfo = object : ValueEventListener{
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            //Log.d(TAG+"<><><><<><><><>",dataSnapshot.getValue().toString())
                            val info = dataSnapshot.getValue<RegisSub>()

                            //Log.d(TAG+"<><><><<><><><>",info.toString())
                            if(info!=null){
                                val l = Intent(this@MainActivity,ListSubject::class.java)
                                l.putExtra("idStudent",IDStudent)
                                l.putExtra("Subject",info.RegisSub)
                                l.putExtra("Seat",info.Seat)
                                startActivity(l)
                            }
                        }
                        override fun onCancelled(p0: DatabaseError) {
                        }
                    }
                    Firebase.database.reference.child("Student").child(IDStudent).addValueEventListener(StudentInfo)


/*                    val l = Intent(this@MainActivity,ListSubject::class.java)
                    l.putExtra("idStudent",IDStudent)
                    startActivity(l)*/

                }
                else{
                    Toast.makeText(this@MainActivity,"ไม่พบรหัส "+IDStudent +" ในระบบ",Toast.LENGTH_LONG).show()
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


