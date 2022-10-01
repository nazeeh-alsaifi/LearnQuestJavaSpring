package com.student.core;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentCollection {
    @XmlElement(name = "student")
    private Collection<Student> studentList;

    public Collection<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(Collection<Student> studentList) {
        this.studentList = studentList;
    }

}
