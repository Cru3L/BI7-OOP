package com;

import java.util.Scanner;
import com.Command;
import com.model.Course;
import com.database.StudentsDB;
import com.database.CoursesDB;

public class App {
    public static void main(String[] args) {
        Scanner scanner;
        Command command;
        StudentsDB studentsDB;
        CoursesDB coursesDB;
        int nstudents;
        int ncourses;

        System.out.println("USTH Students v1.0");
        System.out.println("");

        System.out.println("Loading databases...");
        studentsDB = new StudentsDB();
        coursesDB = new CoursesDB();
        nstudents = studentsDB.getStudents().size();
        for (Course course: coursesDB.getCourses()) 
            if (course.getMarks().length != nstudents) {
                System.out.println("Course data corrupted. Clearing old records...");
                coursesDB.getCourses().clear();
                coursesDB.save();
                break;
            }
        ncourses = coursesDB.getCourses().size();

        System.out.println("Found " + nstudents + " students.");
        System.out.println("Found " + ncourses + " courses.");
        System.out.println("");
        System.out.println("Help Command : ");
        System.out.println("");
        System.out.println("-List students(l)");
        System.out.println("-List courses(o)");
        System.out.println("-Change course(c)");
        System.out.println("-Print marks(p)");
        System.out.println("-Edit a student's mark(e)");
        System.out.println("-Edit attendance(a");
        System.out.println("-Edit midterm(m)");
        System.out.println("-Edit final(f)");
        System.out.println("-Add a new course(n)");
        System.out.println("-Delete a course(d)");
        System.out.println("-Save changes to disk(w)");
        System.out.println("-Save & Quit(q)");
        System.out.println("");
        scanner = new Scanner(System.in);
        command = new Command(studentsDB, coursesDB);
        while (true) {
            command.prompt();
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                command.execute(line.toLowerCase().charAt(0));
            }
            System.out.println("");
        }
    }
}
