package com.student.core;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentCollection {
    private Collection<Student> studentList;

    public Collection<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(Collection<Student> studentList) {
        this.studentList = studentList;
    }

}
