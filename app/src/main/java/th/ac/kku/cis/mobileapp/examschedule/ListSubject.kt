package th.ac.kku.cis.mobileapp.examschedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import th.ac.kku.cis.mobileapp.examschedule.Adapter.MyListAdapter
import th.ac.kku.cis.mobileapp.examschedule.Model.Subject

class ListSubject : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_subject)
        var l = intent
        val idStudent_fromMain = intent.getStringExtra("idStudent")

        val txtShowID = findViewById<TextView>(R.id.txt_show_id_stu)
        txtShowID.text = idStudent_fromMain

        val listView: ListView = findViewById(R.id.listView)
        val student_list = mutableListOf<Subject>()
        student_list.add(Subject("935306", "INTELLIGENT SYSTEM","1","2201","23 มี.ค. 2563","13:00-16:00","1"))
        student_list.add(Subject("935307", "COMPUTER GRAPHICS AND VISUAL COMPUTING","1","2417","1 เม.ย 2563","13:00-16:00","1"))
        student_list.add(Subject("935308", "INFORMATION STORAGE AND RETRIEVAL","1","2202","30 มี.ค. 2563","13:00-16:00","1"))




        listView.adapter = MyListAdapter(
            this,
            R.layout.subject_list,
            student_list
        )

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as Subject
            Toast.makeText(this, selectedItem.name, Toast.LENGTH_SHORT).show()
        }
    }
}
