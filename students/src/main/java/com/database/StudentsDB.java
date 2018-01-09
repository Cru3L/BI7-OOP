package com.database;

import com.model.Student;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ArrayList;

public class StudentsDB {

    private static final String storage = "students.txt";
    private List<Student> students;

    public StudentsDB () {
        load();
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void load() {
        this.students = new ArrayList<Student>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(this.storage), Charset.defaultCharset());
            lines.forEach(line -> {
                    String[] info = line.split(",");
                    if (info.length == 3) this.students.add(new Student(info[0], info[1], info[2]));
                });
        } catch (IOException e) {
            System.err.println("Cannot open file " + this.storage + " for reading.");
        }
    }
}
