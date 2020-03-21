package th.ac.kku.cis.mobileapp.examschedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import th.ac.kku.cis.mobileapp.examschedule.Adapter.MyListAdapter
import th.ac.kku.cis.mobileapp.examschedule.Model.RegisSub
import th.ac.kku.cis.mobileapp.examschedule.Model.Subject

class ListSubject : AppCompatActivity() {

    private val TAG: String = "ListSubject"
    lateinit var idStudent_fromMain:String
    lateinit var Subject_fromMain:String
    var numRegisSub: Int? = null
    var numSeat: Int? = null
    lateinit var Seat_fromMain:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_subject)


        Firebase.database.reference.child("Subject").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var l = intent
                idStudent_fromMain = intent.getStringExtra("idStudent")
                Subject_fromMain = intent.getStringExtra("Subject")
                Seat_fromMain = intent.getStringExtra("Seat")
                //Toast.makeText(this@ListSubject,Seat_fromMain,Toast.LENGTH_LONG).show()
                val eachsubject= Subject_fromMain.split(',')
                numRegisSub = eachsubject.size

                val eachSeat= Seat_fromMain.split(',')
                numSeat = eachSeat.size

                val txtShowID = findViewById<TextView>(R.id.txt_show_id_std)
                txtShowID.text = idStudent_fromMain

                val listView: ListView = findViewById(R.id.listView)
                val student_list = mutableListOf<Subject>()
                for (postSnapshot in dataSnapshot.children) {

                    for (i in 0..(numRegisSub!!-1)){
                        if(postSnapshot.key.equals(eachsubject[i])){
                            var Seat : Int? =  (eachSeat[i].toInt()  + postSnapshot.child("seatstart").value.toString().toInt())-1
                            student_list.add(Subject(postSnapshot.key.toString(), postSnapshot.child("name").value.toString(),postSnapshot.child("room").value.toString(),postSnapshot.child("date").value.toString(),postSnapshot.child("time").value.toString(),Seat.toString()))
                            break
                        }
                        else{

                        }
                    }

                    listView.adapter = MyListAdapter(
                        this@ListSubject,
                        R.layout.subject_list,
                        student_list
                    )
                    listView.setOnItemClickListener { parent, view, position, id ->
                        val selectedItem = parent.getItemAtPosition(position) as Subject
                        Toast.makeText(this@ListSubject, selectedItem.name, Toast.LENGTH_SHORT).show()
                    }
                    //student_list.add(Subject(postSnapshot.key.toString(), postSnapshot.child("name").value.toString(),"1","2202","30 มี.ค. 2563","13:00-16:00","1"))
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })

    }

}
