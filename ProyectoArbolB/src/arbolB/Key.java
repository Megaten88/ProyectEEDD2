/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolB;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Agile PC
 */
public class Key implements Serializable{
    // Key is our class that will act as an index
    private Comparable key;
    private int rrn;

    public Key(Comparable key, int rrn) {
        this.key = key;
        this.rrn = rrn;
    }

    public Comparable getKey() {
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
    // Comparable is used to sort keys with class Collections
    
    @Override
    public String toString() {
        return ""+key;
    }
    
    public String toString2(){
        return "Key: " + this.key + "; RRN: "+this.rrn+"\n";
    }
    
}
