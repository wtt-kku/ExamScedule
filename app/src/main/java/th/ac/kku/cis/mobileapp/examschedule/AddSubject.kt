package th.ac.kku.cis.mobileapp.examschedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_add_subject.*
import th.ac.kku.cis.mobileapp.examschedule.Model.Subject
import th.ac.kku.cis.mobileapp.examschedule.Model.SubjectAddFirebase

class AddSubject : AppCompatActivity() {


    var subId :String? =""
    var subName :String? =""
    var selectRoom :String? = ""
    var selectDay :String? =""
    var selectMonth :String? =""
    var selectYear :String? =""
    var selectTime :String? =""
    var subSeatstart :String? =""
    private val TAG: String = "AddSub"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_subject)
        val room = arrayOf("เลือกห้อง","2202","2203","2214","2215")
        val day = arrayOf("วัน","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31")
        val month = arrayOf("เดือน","ม.ค","ก.พ","มี.ค","เม.ย","พ.ค","มิ.ย","ก.ค","ส.ค","ก.ย","ต.ค","พ.ย","ธ.ค")
        val year = arrayOf("ปี","2563","2564","2565","2566")
        val time = arrayOf("เวลาในการสอบ","08.30-11.30น.","13.00-16.00น.")

        val roomAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,room)
        select_room.adapter = roomAdapter

        val dayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,day)
        select_day.adapter = dayAdapter

        val monthAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,month)
        select_month.adapter = monthAdapter

        val yearAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,year)
        select_year.adapter = yearAdapter

        val timeAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,time)
        select_time.adapter = timeAdapter

        img_info_seatstart.setOnClickListener{view ->
            Snackbar.make(view, "เช่น กำหนดหมายเลขที่นั่งเริ่มต้น = 5   นักศึกษาลำดับที่ 1 จะได้ที่นั่งหมายเลข 5 และคนที่ 2 จะได้ที่นั่งหมายเลข 6", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        select_room.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var room = room[position]
                if(room=="เลือกห้อง"){
                    selectRoom=""
                }else{
                    selectRoom=room
                }
                Log.d(TAG,"Room Select = "+selectRoom.toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectRoom=""

            }
            }

        select_day.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var day = day[position]
                if(day=="วัน"){
                    selectDay=""
                }else{
                    selectDay=day
                }
                Log.d(TAG,"Day Select = "+selectDay.toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectDay=""

            }
        }

        select_month.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var month = month[position]
                if(month=="เดือน"){
                    selectMonth=""
                }else{
                    selectMonth=month
                }
                Log.d(TAG,"Month Select = "+selectMonth.toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectMonth=""

            }
        }

        select_year.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var year = year[position]
                if(year=="ปี"){
                    selectYear=""
                }else{
                    selectYear=year
                }
                Log.d(TAG,"Year Select = "+selectYear.toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectYear=""

            }
        }

        select_time.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var time = time[position]
                if(time=="เวลาในการสอบ"){
                    selectTime=""
                }else{
                    selectTime=time
                }
                Log.d(TAG,"Year Time = "+selectTime.toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectTime=""

            }
        }


            btn_add.setOnClickListener {
                subId =txt_add_subid.text.toString()
                subName = txt_add_name.text.toString()
                subSeatstart = txt_add_seatstart.text.toString()
            /*    Log.d(TAG,"subId = "+ subId.toString()+"\n"+
                        "subName = "+ subName.toString()+"\n"+
                        "selectRoom = "+ selectRoom.toString()+"\n"+
                        "selectDay = "+ selectDay.toString()+"\n"+
                        "selectMonth = "+ selectMonth.toString()+"\n"+
                        "selectYear = "+ selectYear.toString()+"\n"+
                        "selectTime = "+ selectTime.toString()+"\n"+
                        "subSeatstart = "+ subSeatstart.toString()+"\n");*/
                if(subId==""||subName==""||selectRoom==""||selectDay==""||selectMonth==""||selectYear==""||selectTime==""||subSeatstart==""){
                    Toast.makeText(this,"ข้อมูลไม่ครบถ้วน",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"ข้อมูลครบถ้วน",Toast.LENGTH_SHORT).show()
                    AddtoFirebase(subId!!,subName!!,selectRoom!!,selectDay!!,selectMonth!!,selectYear!!,selectTime!!,subSeatstart!!)
                }
            }
    }

    private fun AddtoFirebase(subId:String,subName:String,selectRoom:String,selectDay:String,selectMonth:String,selectYear:String,selectTime:String,subSeatstart:String){

        val sid = subId
        val name= subName
        val room= selectRoom
        val date= selectDay+" "+selectMonth+" "+selectYear
        val time= selectTime
        val seatstart= subSeatstart

        val myDatabase = FirebaseDatabase.getInstance().getReference("Subject")
        val id = sid
        val subject = SubjectAddFirebase(name,room,date,time,seatstart)
        myDatabase.child(id).setValue(subject).addOnCompleteListener{
                Toast.makeText(this,"เพิ่มสำเร็จ",Toast.LENGTH_SHORT).show()
            var i = Intent(this,staff_activity_main::class.java)
            startActivity(i)
        }


    }


}
