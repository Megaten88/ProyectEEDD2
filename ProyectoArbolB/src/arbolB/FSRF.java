/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolB;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author k_k_r
 */
public class FSRF {

    private int recordSize;
    private File source;
    private int RRN;
    private int header_pos;

    private ArrayList<Integer> AvailList;
    private ArrayList<Person> PersonList;
    private ArrayList<Key> IDIndex;
    RandomAccessFile file;

    public FSRF() {
        this.recordSize = 60;
        this.source = new File("./neo.txt");
        this.RRN = 0;
        this.AvailList = new ArrayList();
        this.PersonList = new ArrayList();

        try {
            this.file = new RandomAccessFile(this.source, "rw");
            //System.out.println(file.readLine());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FSRF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FSRF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Init() {
        // First thing to do: get header (last position in which something was inserted).
        //If header = -1, this means this was our first interaction with the data file;

        try {
            this.file.seek(0 + Integer.BYTES);
            int current_rrn = 0;
            if (source.length() > 0) {
                while (file.getFilePointer() < file.length()) {
                    String tempName = file.readUTF();
                    String tempDate = file.readUTF();
                    int tempID = file.readInt();
                    float tempSalary = file.readFloat();
                    
                    if (tempName.charAt(0) == '*') {
                        this.AvailList.add(current_rrn);
                    }
                    
                    this.IDIndex.add(new Key(tempID,current_rrn));
                    Collections.sort(this.IDIndex);
                    current_rrn++;

                };

            }
        } catch (IOException ex) {
            Logger.getLogger(FSRF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean Insert(Person temp) {
        //boolean success = false;
        if (this.AvailList.isEmpty()) {
            try {

                file.seek(file.length());
                file.writeUTF(String.copyValueOf(temp.getFull_name()));
                file.writeUTF(String.copyValueOf(temp.getBirth_date()));
                file.writeInt(temp.getID());
                file.writeFloat(temp.getSalary());

                return true;

            } catch (IOException ex) {
                Logger.getLogger(FSRF.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                file.seek(0);
                int first_available_position = this.AvailList.remove(0);
                int new_position = 0;
                file.seek(this.recordSize * (first_available_position - 1) + Integer.BYTES);
                file.writeUTF(String.copyValueOf(temp.getFull_name()));
                file.writeUTF(String.copyValueOf(temp.getBirth_date()));
                file.writeInt(temp.getID());
                file.writeFloat(temp.getSalary());

                return true;
            } catch (IOException ex) {
                Logger.getLogger(FSRF.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return false;
    }

    public void PrintList() {
        try {
            this.file.seek(0);
            System.out.println(file.readInt());
            this.file.seek(0 + Integer.BYTES);
            if (source.length() > 0) {
                while (file.getFilePointer() < file.length()) {
                    System.out.println(file.readUTF() + " " + file.readUTF() + " " + Integer.toString(file.readInt()) + " " + Float.toString(file.readFloat()));
                }
                file.seek(0);
            }
        } catch (IOException ex) {
        }
    }

    public Person ReadRecord() {

        return null;
    }

    public boolean search(int key) {

        try {
            file.seek(0 + Integer.BYTES);

            while (file.getFilePointer() < file.length()) {
                String tempName = file.readUTF();
                String tempDate = file.readUTF();
                int tempID = file.readInt();
                float tempSalary = file.readFloat();

                if (key == tempID) {
                    System.out.println(tempName + " " + tempDate + " " + Integer.toString(tempID) + " " + Float.toString(tempSalary));
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FSRF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void DeleteRecord(int number) {

        try {
            file.seek(/*60*/this.recordSize * number + Integer.BYTES);
            String delName = file.readUTF();
            String delDate = file.readUTF();
            int delID = file.readInt();
            float delFloat = file.readFloat();

            System.out.println(delName + " " + delDate + " " + delID + " " + delFloat);

            file.seek(/*60*/this.recordSize * number + Integer.BYTES);
            char[] nombre = delName.toCharArray();
            nombre[0] = '*';

            file.writeUTF(String.copyValueOf(nombre));
            file.writeUTF(delDate);
            file.writeInt(delID);
            file.writeFloat(delFloat);

        } catch (IOException ex) {
            Logger.getLogger(FSRF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getRecordSize() {
        try {
            String temp = file.readLine();
            System.out.println(temp);
            System.out.println(Arrays.toString(temp.getBytes("UTF-8")));
            file.seek(66 * 5);
            System.out.println(file.readLine().getBytes("UTF-8").length);
            System.out.println(file.readLine());

        } catch (IOException ex) {
            Logger.getLogger(FSRF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
