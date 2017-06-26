/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolB;

import java.io.Serializable;

/**
 *
 * @author Agile PC
 */
public class Key implements Comparable<Key>, Serializable{

    private int key;
    private int rrn;

    public Key(int key, int rrn) {
        this.key = key;
        this.rrn = rrn;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getRrn() {
        return rrn;
    }

    public void setRrn(int rrn) {
        this.rrn = rrn;
    }
    
    @Override
    public int compareTo(Key o) {
        int compareKey = o.getKey();
        return this.key - compareKey;
    }

    @Override
    public String toString() {
        return ""+key;
    }
    
    public String toString2(){
        return "Key: " + this.key + "; RRN: "+this.rrn+"\n";
    }
    
}
