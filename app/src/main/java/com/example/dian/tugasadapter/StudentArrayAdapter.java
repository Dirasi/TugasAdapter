package com.example.dian.tugasadapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dian.tugasadapter.R;
import com.example.dian.tugasadapter.com.example.dian.user.Student;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StudentArrayAdapter extends ArrayAdapter<Student> {

    public StudentArrayAdapter(Context context, ArrayList<Student> studentArrayList) {
        super(context, 0, studentArrayList);
    }

    public View getView(int position, View view, ViewGroup parent){
        Student student = getItem(position);
        if(view==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.student_instance,parent,false);
        }

        TextView idView = (TextView) view.findViewById(R.id.student_id);
        TextView noregView = (TextView) view.findViewById(R.id.student_noreg);
        TextView nameView = (TextView) view.findViewById(R.id.student_name);
        TextView mailView = (TextView) view.findViewById(R.id.student_email);
        TextView phoneView = (TextView) view.findViewById(R.id.student_phone);

        //set content
        idView.setText(student.getId()+"");
        noregView.setText(student.getNoreg());
        nameView.setText(student.getName());
        mailView.setText(student.getMail());
        phoneView.setText(student.getPhone());
        return view;
    }
}
