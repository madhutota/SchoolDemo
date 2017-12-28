package bornbaby.com.schooldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import bornbaby.com.schooldemo.helper.StudentDataBase;
import bornbaby.com.schooldemo.helper.StudentsAdapter;
import bornbaby.com.schooldemo.model.Student;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lv_studets)
    ListView lv_studets;

    StudentDataBase studentDataBase;

    private StudentsAdapter studentsAdapter;
    private ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        studentDataBase = new StudentDataBase(this);

        students = (ArrayList<Student>) studentDataBase.getAllStudents();

        studentsAdapter = new StudentsAdapter(students, this);
        lv_studets.setAdapter(studentsAdapter);


    }
}
