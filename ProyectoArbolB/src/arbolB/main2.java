/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolB;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author k_k_r
 */
public class main2 {

    public static void main(String[] args) {
        ArrayList<Person> list;
        System.out.println(Charset.defaultCharset());
        FSRF prueba = new FSRF();
        prueba.Init();
        String uno = "holis";
        String dos = "19961108";
        //prueba.Insert(new Person(uno ,dos ,12345678, (int) 400.0) );
        prueba.PrintList();
        System.out.println(prueba.search(56842819));
        //insertHeader();
        //prueba.DeleteRecord(0);
        //prueba.getRecordSize();
        //prueba.CreateBinFile();
    }

}
