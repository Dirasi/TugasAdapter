package com.example.dian.tugasadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dian.tugasadapter.com.example.dian.user.Student;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    private StudentArrayAdapter studentAdapter;
    private ListView list_item;
    private TextView empty_view;
    static ArrayList<Student> students;
    private FloatingActionButton button_Add;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        if (students == null) students = new ArrayList<Student>();
        studentAdapter = new StudentArrayAdapter(this, students);
        list_item = (ListView) findViewById(R.id.list_item);
        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student studentItem = students.get(position);
                Intent i = new Intent(StudentActivity.this, StudentAddActivity.class);
                i.putExtra("student", (Serializable) studentItem); // ini passing object
                i.putExtra("isEdit", true);
                i.putExtra("position", position);
                startActivity(i);
            }
        });
        button_Add = (FloatingActionButton) findViewById(R.id.addInput);
        button_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentActivity.this, StudentAddActivity.class);
                i.putExtra("isEdit", false);
                startActivity(i);
            }
        });


    }

    private void populateStudentDummies() {
        int i = students.size();
        students.add(new Student(i,"3145136194","Dian Rakasiwi","082233602367","dian@gmail.com"));
        students.add(new Student(i+1,"3145136210","Jono Jono","081334567892","jono@gmail.com"));
    }

    public void createDummy(){
        populateStudentDummies();
        studentAdapter = new StudentArrayAdapter(this, students);
        list_item.setAdapter(studentAdapter);
    }

    public void clearData(){
        students.clear();
        studentAdapter = new StudentArrayAdapter(this, students);
        list_item.setAdapter(studentAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_student_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        empty_view = (TextView) findViewById(R.id.emptyText);
        if(students.size() == 0) {
            studentAdapter = new StudentArrayAdapter(this, new ArrayList<Student>());
            list_item.setEmptyView(empty_view);
            empty_view.setText("Student Not Found");
        } else {
            studentAdapter = new StudentArrayAdapter(this, students);
            empty_view.setText("");
        }
        list_item.setAdapter(studentAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.create_dummy:
                createDummy();
                return true;
            case R.id.clear_list:
                clearData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
