package com;

import java.util.Scanner;
import com.model.Student;
import com.model.Course;
import com.database.StudentsDB;
import com.database.CoursesDB;

public class Command
{
    private StudentsDB studentsDB;
    private CoursesDB coursesDB;
    private Scanner scanner;
    private int current;

    public Command(StudentsDB studentsDB, CoursesDB coursesDB)
    {
        this.studentsDB = studentsDB;
        this.coursesDB = coursesDB;
        this.scanner = new Scanner(System.in);
        this.current = 0;
    }

    public void prompt()
    {
        if (current != 0)
        {
            System.out.print("Course [" + current + "] :" + coursesDB.getCourses().get(current - 1).getName() + ". ");
        }
        System.out.print("Command (h for help): ");
    }

    public void execute(char c)
    {
        switch (c)
        {
        case 'l':
            System.out.println("");
            System.out.println("List of Student : ");
            System.out.println("");
            list();
            break;
        case 'o':
            System.out.println("");
            System.out.println("List of Courses : ");
            System.out.println("");
            courses();
            break;
        case 'c':
            course();
            break;
        case 'p':
            print();
            break;
        case 'e':
            edit();
            break;
        case 'a':
            edit("attendance");
            break;
        case 'm':
            edit("midterm");
            break;
        case 'f':
            edit("final");
            break;
        case 'n':
            add();
            break;
        case 'd':
            del();
            break;
        case 'w':
            save();
            break;
        case 'q':
            quit();
            break;
        case 'h':
            help();
            break;
        }
    }

    public void list()
    {
        int nstudents = studentsDB.getStudents().size();
        if (nstudents == 0)
        {
            System.out.println("There are no students.");
            return;
        }

        int i = 1;
        System.out.format("%6s  %-20s  %6s  %6s%n", "Number", "Name", "Gender", "DOB");
        for (Student student: studentsDB.getStudents())
        {
            System.out.format("%4d    %-20s  %3s     %10s%n", i++, student.getName(), student.getGender(), student.getDob());
        }
    }

    public void courses()
    {
        if (coursesDB.getCourses().size() == 0)
        {
            System.out.println("There are no courses.");
            return;
        }

        int i = 1;
        System.out.println("Number  Name");
        for (Course course: coursesDB.getCourses())
        {
            System.out.format((i != current) ? "%4d    " : "%4d *  ", i++);
            System.out.println(course.getName());
        }
    }

    public void course()
    {
        int ncourses = coursesDB.getCourses().size();
        if (ncourses == 0)
        {
            System.out.println("There are no courses.");
            return;
        }

        System.out.print("Course number (1-" + ncourses + "): ");
        int i = scanner.nextInt();
        if (!isInRange(i, 1, ncourses))
        {
            System.out.println("Index out of bounds.");
            return;
        }
        current = i;
        scanner.nextLine();
    }

    public void print()
    {
        if (current == 0)
        {
            System.out.println("No courses chosen.");
            return;
        }

        int nstudents = studentsDB.getStudents().size();
        if (nstudents == 0)
        {
            System.out.println("There are no students.");
            return;
        }

        Course course = coursesDB.getCourses().get(current - 1);
        int i = 0;
        System.out.format("%6s %-20s %6s %10s %3s %3s %3s %4s%n", "Number", "Name", "Gender", "DOB    ", "Att", "Mdt", "Fnl", "GPA");
        for (Student student: studentsDB.getStudents())
        {
            System.out.format("%4d   %-20s %3s    %10s %3d %3d %3d %4.1f%n", i + 1, student.getName(), student.getGender(), student.getDob(),
                              course.getAttend(i), course.getMidterm(i), course.getFinal(i), course.getGpa(i));
            i++;
        }
    }

    public void edit()
    {
        if (current == 0)
        {
            System.out.println("No courses chosen.");
            return;
        }

        int nstudents = studentsDB.getStudents().size();
        if (nstudents == 0)
        {
            System.out.println("There are no students.");
            return;
        }

        System.out.print("Student number (1-" + nstudents + "): ");
        int i = scanner.nextInt();
        if (!isInRange(i, 1, nstudents))
        {
            System.out.println("Index out of bounds.");
            return;
        }

        i--;
        Student student = studentsDB.getStudents().get(i);
        Course course = coursesDB.getCourses().get(current - 1);
        System.out.println("Editing " + student.getName() + ":");
        System.out.print("Attendance (" + course.getAttend(i) + "): ");
        course.setAttend(i, scanner.nextInt());
        System.out.print("Midterm (" + course.getMidterm(i) + "): ");
        course.setMidterm(i, scanner.nextInt());
        System.out.print("Final (" + course.getFinal(i) + "): ");
        course.setFinal(i, scanner.nextInt());
        System.out.println("Done.");
        scanner.nextLine();
    }

    public void edit(String type)
    {
        if (current == 0)
        {
            System.out.println("No courses chosen.");
            return;
        }

        int nstudents = studentsDB.getStudents().size();
        if (nstudents == 0)
        {
            System.out.println("There are no students.");
            return;
        }

        Course course = coursesDB.getCourses().get(current - 1);
        int i = 0;
        System.out.println("Editing " + type + ":");
        for (Student student: studentsDB.getStudents())
        {
            if (type.equals("attendance"))
            {
                System.out.print(student.getName() + " (");
                System.out.print(course.getAttend(i));
                System.out.print("): ");
                course.setAttend(i, scanner.nextInt());
            } else if (type.equals("midterm"))
            {
                System.out.print(student.getName() + " (");
                System.out.print(course.getMidterm(i));
                System.out.print("): ");
                course.setMidterm(i, scanner.nextInt());
            } else
                {
                System.out.print(student.getName() + " (");
                System.out.print(course.getFinal(i));
                System.out.print("): ");
                course.setFinal(i, scanner.nextInt());
                }
            i++;
        }
        System.out.println("Done.");
        scanner.nextLine();
    }

    public void add()
    {
        System.out.print("Course name: ");
        String name = this.scanner.nextLine();
        if (name.length() == 0)
        {
            System.out.println("Name is empty.");
            return;
        }
        coursesDB.getCourses().add(new Course(name, studentsDB.getStudents()));
    }

    public void del()
    {
        int ncourses = coursesDB.getCourses().size();
        if (ncourses == 0)
        {
            System.out.println("There are no courses.");
            return;
        }

        System.out.print("Course number (1-" + ncourses + "): ");
        int i = scanner.nextInt();
        if (!isInRange(i, 1, ncourses))
        {
            System.out.println("Index out of bounds.");
            return;
        }

        coursesDB.getCourses().remove(i - 1);
        ncourses--;
        if (current > ncourses)
        {
            current--;
        }
        scanner.nextLine();
    }

    public void save()
    {
        coursesDB.save();
    }

    public void quit()
    {
        save();
        System.exit(0);
    }

    public void help()
    {
        System.out.println(" Help Command : ");
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
    }

    public boolean isInRange(int i, int start, int stop) {
        return i >= start && i <= stop;
    }
}
