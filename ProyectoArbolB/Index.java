/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda.fsrf;

/**
 *
 * @author k_k_r
 */
public class Index {
    private int key;
    private int rrn;

    public Index(int key, int rrn) {
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
    public String toString() {
        return "Index{" + "key=" + key + ", rrn=" + rrn + '}';
    }
    
}
