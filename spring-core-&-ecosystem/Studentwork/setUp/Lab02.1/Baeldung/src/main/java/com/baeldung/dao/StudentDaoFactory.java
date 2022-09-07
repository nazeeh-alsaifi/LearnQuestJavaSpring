package com.baeldung.dao;

public class StudentDaoFactory {
    public static StudentDao getDaoStatic(){
        //
        return new StudentDaoImpl();
    } 
    public StudentDao getDao(){
        //
        return new StudentDaoImpl();
    }
}
