package com.example.dian.tugasadapter;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import com.example.dian.tugasadapter.R;
import com.example.dian.tugasadapter.com.example.dian.user.Student;


public class StudentAddActivity extends AppCompatActivity{
    private EditText edit_id, edit_noreg, edit_name, edit_mail, edit_phone;
    private FloatingActionButton button_Done, button_Cancel;
    private boolean isEdit = false;
    ArrayList<Student> studentList;
    private int position;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student_activity);
        studentList = StudentActivity.students;

        edit_id = (EditText) findViewById(R.id.edit_id);
        edit_noreg = (EditText) findViewById(R.id.edit_noreg);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_mail = (EditText) findViewById(R.id.edit_mail);
        edit_phone = (EditText) findViewById(R.id.edit_phone);

        Intent i = getIntent();
        if(i.getBooleanExtra("isEdit", true)) {
            isEdit = true;
            Student student = (Student) i.getSerializableExtra("student");
            edit_id.setText(String.valueOf(student.getId()));
            edit_noreg.setText(student.getNoreg());
            edit_name.setText(student.getName());
            edit_mail.setText(student.getMail());
            edit_phone.setText(student.getPhone());
            position = i.getIntExtra("position", 0);
        }

        button_Done = (FloatingActionButton) findViewById(R.id.button_Done);
        button_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudent();
            }
        });

        button_Cancel = (FloatingActionButton) findViewById(R.id.button_Cancel);
        button_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void saveStudent(){

        if (isEdit){
            Student tmpStudent = new Student(Integer.parseInt(edit_id.getText().toString()), edit_noreg.getText().toString(), edit_name.getText().toString(), edit_mail.getText().toString(), edit_phone.getText().toString());
            studentList.set(position, tmpStudent);
            finish();
        } else {
            Student tmpStudent = new Student(Integer.parseInt(edit_id.getText().toString()), edit_noreg.getText().toString(), edit_name.getText().toString(), edit_mail.getText().toString(), edit_phone.getText().toString());
            studentList.add(tmpStudent);
            finish();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_delete, menu);
        return true;
    }

}
