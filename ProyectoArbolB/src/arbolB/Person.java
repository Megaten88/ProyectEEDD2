/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda.fsrf;

import java.io.Serializable;

/**
 *
 * @author k_k_r
 */
public class Person implements Serializable {
    private char[] full_name;
    private char[] birth_date;
    private int ID;
    private float salary; 

    public Person(String full_name, String birth_date, int ID, int Salary) {
        this.full_name = full_name.toCharArray();
        this.birth_date = birth_date.toCharArray();
        this.ID = ID;
        this.salary = Salary;
    }

    Person() {
         //To change body of generated methods, choose Tools | Templates.
    }

    public char[] getFull_name() {
        return full_name;
    }

    public void setFull_name(char[] full_name) {
        this.full_name = full_name;
    }

    public char[] getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(char[] birth_date) {
        this.birth_date = birth_date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() { 
        
        return String.copyValueOf(full_name) + " " + String.copyValueOf(birth_date) + " " + ID + " " + salary  ;
    }

    
}





