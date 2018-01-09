package com.database;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import com.model.Course;

public class CoursesDB {
    private static final String storage = "courses.dat";
    private List<Course> courses;

    public CoursesDB() {
        load();
    }

    public List<Course> getCourses() { return this.courses; }

    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(this.storage));
            this.courses = (List<Course>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            this.courses = new ArrayList<Course>();
            System.err.println("Cannot open file " + this.storage + " for reading.");
        } catch (ClassNotFoundException e) {
            this.courses = new ArrayList<Course>();
            System.err.println("Cannot find class List<Course>.");
        }
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(this.storage));
            oos.writeObject(this.courses);
            oos.close();
        } catch (IOException e) {
            System.err.println("Cannot save to file " + this.storage + ".");
        }
    }
}
