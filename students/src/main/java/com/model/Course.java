package com.model;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {
    protected String name;
    protected int[][] marks;

    public Course(String name, List<Student> students) {
        this.name = name;
        this.marks = new int[students.size()][3];
    }

    public String getName() { return this.name; }
    public int[][] getMarks() { return this.marks; }

    public int getAttend(int i) { return this.marks[i][0]; }
    public int getMidterm(int i) { return this.marks[i][1]; }
    public int getFinal(int i) { return this.marks[i][2]; }
    public double getGpa(int i) {
        return 0.1*getAttend(i) + 0.4*getMidterm(i) + 0.5*getFinal(i);
    }

    public void setAttend(int i, int mark) { this.marks[i][0] = mark < 0 ? mark = 0 : mark > 20? mark = 20 : mark; }
    public void setMidterm(int i, int mark) { this.marks[i][1] = mark < 0 ? mark = 0 : mark > 20? mark = 20 : mark; }
    public void setFinal(int i, int mark) { this.marks[i][2] = mark < 0 ? mark = 0 : mark > 20? mark = 20 : mark; }
}
