package th.ac.kku.cis.mobileapp.examschedule.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

import th.ac.kku.cis.mobileapp.examschedule.Model.SubjectForStaff
import th.ac.kku.cis.mobileapp.examschedule.R


public class MyListAdapterForStaff(var mCtx: Context, var resource:Int, var items:List<SubjectForStaff>)
    : ArrayAdapter<SubjectForStaff>( mCtx , resource , items ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //super.getView(position, convertView, parent)

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        var tvSubjectId : TextView = view.findViewById(R.id.txt_subject_id)
        var tvSubjectName : TextView = view.findViewById(R.id.txt_subject_name)
        var tvSubjectRoom : TextView = view.findViewById(R.id.txt_room)
        var tvSubjectDate : TextView = view.findViewById(R.id.txt_date)
        var tvSubjectTime : TextView = view.findViewById(R.id.txt_time)
        var tvSubjectSeat : TextView = view.findViewById(R.id.txt_seat)



        var subject: SubjectForStaff = items[position]
        tvSubjectId.text = subject.sid
        tvSubjectName.text = subject.name
        tvSubjectRoom.text = subject.room
        tvSubjectDate.text = subject.date
        tvSubjectTime.text = subject.time
        tvSubjectSeat.text = subject.seatstart

        var ButtonDel : Button = view.findViewById(R.id.btn_del)

      /*  ButtonDel.setOnClickListener {

            val id = subject.sid
            val myDatabase = FirebaseDatabase.getInstance().getReference("Subject")
            myDatabase.child(id).removeValue().addOnSuccessListener {
                Toast.makeText(context,"ลบวิชา "+id+" เรียบร้อย",Toast.LENGTH_SHORT).show();
            }
        }*/
            ButtonDel.setOnClickListener{
                val id = subject.sid
                val myDatabase = FirebaseDatabase.getInstance().getReference("Subject")
                val builder = AlertDialog.Builder(context)
                builder.setTitle("ยืนยันการลบ?")
                builder.setMessage("ยืนยันยกเลิกการสอบรหัสวิชา "+id)
                builder.setPositiveButton("ยืนยัน"){dialog, which ->
                    myDatabase.child(id).removeValue().addOnSuccessListener {
                        Toast.makeText(context,"ลบวิชา "+id+" เรียบร้อยแล้ว",Toast.LENGTH_SHORT).show();
                    }

                }
                builder.setNegativeButton("กลับ"){dialog,which ->
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
        }
        return view
    }
}
