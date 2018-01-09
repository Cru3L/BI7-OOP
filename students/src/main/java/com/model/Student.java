package com.model;

import java.io.Serializable;

public class Student implements Serializable
{
    protected String name;
    protected String gender;
    protected String dob;

    public Student(String name, String gender, String dob)
    {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public String getName() { return this.name; }
    public String getGender() { return this.gender; }
    public String getDob() { return this.dob; }
}
