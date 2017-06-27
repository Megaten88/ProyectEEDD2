/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

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
    BTree tree;
    RandomAccessFile file;
    DefaultTableModel model;

    public FSRF() {
        this.recordSize = 60;
        this.source = new File("./neo.bin");
        this.RRN = 0;
        this.AvailList = new ArrayList();
        this.PersonList = new ArrayList();
        this.tree = new BTree(512);

        model = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Name", "Birth Date", "ID", "Salary"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        try {
            this.file = new RandomAccessFile(this.source, "rw");
            //System.out.println(file.readLine());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FSRF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
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
                    System.out.println(tempName + tempDate + tempID + tempSalary);
                    if (tempName.charAt(0) == '*') {
                        this.AvailList.add(current_rrn);
                        tempName = file.readUTF();
                        tempDate = file.readUTF();
                        tempID = file.readInt();
                        tempSalary = file.readFloat();
                        current_rrn++;

                    } else if (tempName.charAt(0) != '*') {
                        tree.insert(tree.getRoot(), new Key(tempID, current_rrn));
                        //this.IDIndex.add(new Key(tempID, current_rrn));
                        model.addRow(new Object[]{tempName, tempDate, tempID, tempSalary});
                        current_rrn++;
                    }

                };

                System.out.println(tree.getRoot().toString());
                System.out.println(tree.getRoot().getChildren().toString() + " ");

            }
        } catch (IOException ex) {
            Logger.getLogger(FSRF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean Insert(Person temp) {
        if (this.AvailList.isEmpty()) {
            try {

                file.seek(file.length());
                file.writeUTF(String.copyValueOf(temp.getFull_name()));
                file.writeUTF(String.copyValueOf(temp.getBirth_date()));
                file.writeInt(temp.getID());
                file.writeFloat(temp.getSalary());
                tree.insert(tree.getRoot(), new Key(temp.getID(), (int) ((file.length() - 2) / this.recordSize)));
                file.seek(0);
                file.writeInt((int) ((file.length() - 2) / this.recordSize));
                return true;

            } catch (IOException ex) {
                Logger.getLogger(FSRF.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                file.seek(0);
                int first_available_position = this.AvailList.remove(0);
                int new_position = 0;
                file.seek(this.recordSize * (first_available_position) + Integer.BYTES);
                file.writeUTF(String.copyValueOf(temp.getFull_name()));
                file.writeUTF(String.copyValueOf(temp.getBirth_date()));
                file.writeInt(temp.getID());
                file.writeFloat(temp.getSalary());
                tree.insert(tree.getRoot(), new Key(temp.getID(), first_available_position));
                file.seek(0);
                file.writeInt(first_available_position);

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

    public boolean searchByKey(int key) {
        Node Temp = this.tree.search(tree.getRoot(), key);

        if (Temp != null) {;
            int rrn = getRecordRNN(key);
            if (rrn > -1) {
                try {
                    file.seek(recordSize * (rrn) + Integer.BYTES);
                    String tempName = file.readUTF();
                    String tempDate = file.readUTF();
                    int tempID = file.readInt();
                    float tempSalary = file.readFloat();
                    System.out.println(tempName + " " + tempDate + " " + Integer.toString(tempID) + " " + Float.toString(tempSalary));
                    return true;
                } catch (IOException ex) {
                    Logger.getLogger(FSRF.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public int getRecordRNN(int key) {
        int rnn = -1;
        Key keySearch = null;

        Node temp = this.tree.search(tree.getRoot(), key);
        for (int i = 0; i < temp.getKeys().size(); i++) {
            if (temp.getKeys().get(i).getKey().compareTo(key) == 0) {
                keySearch = temp.getKeys().get(i);
            }
        }
        if (keySearch != null) {
            return keySearch.getRrn();
        }
        return -1;

    }

    public boolean DeleteRecord(int key) {

        Node Temp = this.tree.search(tree.getRoot(), key);
        System.out.println(key);
        System.out.println("hola");
        System.out.println(this.getRecordRNN(key));

        if (Temp != null) {;
            int rrn = getRecordRNN(key);
            System.out.println(key);
            System.out.println(rrn);
            if (rrn >= 0) {
                try {

                    file.seek(recordSize * (rrn) + Integer.BYTES);
                    String tempName = file.readUTF();
                    String tempDate = file.readUTF();
                    int tempID = file.readInt();
                    float tempSalary = file.readFloat();

                    if (tempName.charAt(0) == '*') {
                        return false;
                    }

                    //System.out.println(tempName + " " + tempDate + " " + Integer.toString(tempID) + " " + Float.toString(tempSalary));
                    file.seek(this.recordSize * rrn + Integer.BYTES);

                    char[] nombre = tempName.toCharArray();
                    nombre[0] = '*';
                    file.writeUTF(String.copyValueOf(nombre));
                    file.writeUTF(tempDate);
                    file.writeInt(tempID);
                    file.writeFloat(tempSalary);
                    tree.delete(Temp, key);
                    this.AvailList.add(rrn);

                    return true;
                } catch (IOException ex) {
                    Logger.getLogger(FSRF.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return false;

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
