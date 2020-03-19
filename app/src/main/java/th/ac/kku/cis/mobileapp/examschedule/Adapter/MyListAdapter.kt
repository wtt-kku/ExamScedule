package th.ac.kku.cis.mobileapp.examschedule.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import th.ac.kku.cis.mobileapp.examschedule.Model.Subject
import th.ac.kku.cis.mobileapp.examschedule.R


public class MyListAdapter(var mCtx: Context, var resource:Int, var items:List<Subject>)
    : ArrayAdapter<Subject>( mCtx , resource , items ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //super.getView(position, convertView, parent)

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        val TvIcon: ImageView = view.findViewById(R.id.icon)
        var tvSubjectId : TextView = view.findViewById(R.id.txt_subject_id)
        var tvSubjectName : TextView = view.findViewById(R.id.txt_subject_name)
        var tvSubjectRoom : TextView = view.findViewById(R.id.txt_room)
        var tvSubjectDate : TextView = view.findViewById(R.id.txt_date)
        var tvSubjectTime : TextView = view.findViewById(R.id.txt_time)
        var tvSubjectSeat : TextView = view.findViewById(R.id.txt_seat)


        var subject: Subject = items[position]
        TvIcon.setImageResource(R.drawable.examicon)
        tvSubjectId.text = subject.sid
        tvSubjectName.text = subject.name
        tvSubjectRoom.text = subject.room
        tvSubjectDate.text = subject.date
        tvSubjectTime.text = subject.time
        tvSubjectSeat.text = subject.seatstart

        return view
    }
}
