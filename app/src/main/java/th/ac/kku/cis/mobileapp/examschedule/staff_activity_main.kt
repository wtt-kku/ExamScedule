package th.ac.kku.cis.mobileapp.examschedule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import th.ac.kku.cis.mobileapp.examschedule.R
import kotlinx.android.synthetic.main.activity_staff_main.*
import kotlinx.android.synthetic.main.content_staff_activity_main.*
import kotlinx.android.synthetic.main.subject_list2.*
import th.ac.kku.cis.mobileapp.examschedule.Adapter.MyListAdapterForStaff
import th.ac.kku.cis.mobileapp.examschedule.Model.Subject
import th.ac.kku.cis.mobileapp.examschedule.Model.SubjectForStaff

class staff_activity_main : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private val TAG: String = "Staff ACtivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_main)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
            val i = Intent(this,AddSubject::class.java)
            startActivity(i)
        }



        FirebaseDatabase.getInstance().reference.child("Subject").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {

                var i = intent

                mAuth = FirebaseAuth.getInstance()
                val user = mAuth!!.currentUser
                txt_show_email_staff.text = user!!.email

                val listView2: ListView = findViewById(R.id.listView2)
                val subject_list = mutableListOf<SubjectForStaff>()
                for(p0 in p0.children){
                    subject_list.add(SubjectForStaff(p0.key.toString(),p0.child("name").getValue().toString(),p0.child("room").getValue().toString(),p0.child("date").getValue().toString(),p0.child("time").getValue().toString(),p0.child("seatstart").getValue().toString()))
                }

                listView2.adapter = MyListAdapterForStaff(
                    this@staff_activity_main,
                    R.layout.subject_list2,
                    subject_list
                )


                listView2.setOnItemClickListener { parent, view, position, id ->
                   val selectedItem = parent.getItemAtPosition(position) as SubjectForStaff
                    Toast.makeText(this@staff_activity_main, selectedItem.name, Toast.LENGTH_SHORT).show()
                }

            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.

        menuInflater.inflate(R.menu.staff_menu, menu)

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_logout_staff -> {
                mAuth!!.signOut()
                Toast.makeText(this,"ออกจากระบบสำเร็จ", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "ออกจากระบบสำเร็จ !!")
                startActivity(Intent(this,staff_login::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
