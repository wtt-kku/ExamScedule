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

        /*var l = intent
        idStudent_fromMain = intent.getStringExtra("idStudent")
        Subject_fromMain = intent.getStringExtra("Subject")
        Seat_fromMain = intent.getStringExtra("Seat")
        val eachsubject= Subject_fromMain.split(',')
        numRegisSub = eachsubject.size*/

        //Toast.makeText(this,numRegisSub.toString(),Toast.LENGTH_SHORT).show()
        /*val StudentInfo = object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //Log.d(TAG+"<><><><<><><><>",dataSnapshot.getValue().toString())
               val info = dataSnapshot.getValue<RegisSub>()

                //Log.d(TAG+"<><><><<><><><>",info.toString())
                if(info!=null){
                    Toast.makeText(this@ListSubject, info?.Seat.toString() ,Toast.LENGTH_LONG).show()
                    //U.Seat =  info.Seat
               }
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        }
        Firebase.database.reference.child("Student").child(idStudent_fromMain).addValueEventListener(StudentInfo)*/


        //Toast.makeText(this@ListSubject, SeatInfo ,Toast.LENGTH_LONG).show()


       /* val txtShowID = findViewById<TextView>(R.id.txt_show_id_stu)
        txtShowID.text = idStudent_fromMain

        val listView: ListView = findViewById(R.id.listView)*/
        //val student_list = mutableListOf<Subject>()


//        student_list.add(Subject("935306", "INTELLIGENT SYSTEM","1","2201","23 มี.ค. 2563","13:00-16:00","1"))
//        student_list.add(Subject("935307", "COMPUTER GRAPHICS AND VISUAL COMPUTING","1","2417","1 เม.ย 2563","13:00-16:00","1"))
//        student_list.add(Subject("935308", "INFORMATION STORAGE AND RETRIEVAL","1","2202","30 มี.ค. 2563","13:00-16:00","1"))

        /*postSnapshot.key.toString()) {
            student_list.add(Subject("935308"+i.toShort(), "INFORMATION STORAGE AND RETRIEVAL","1","2202","30 มี.ค. 2563","13:00-16:00","1"))
        }*/

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

                val txtShowID = findViewById<TextView>(R.id.txt_show_id_stu)
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




        /*listView.adapter = MyListAdapter(
            this,
            R.layout.subject_list,
            student_list
        )*/


        /*listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as Subject
            Toast.makeText(this, selectedItem.name, Toast.LENGTH_SHORT).show()
        }*/

    }

}
