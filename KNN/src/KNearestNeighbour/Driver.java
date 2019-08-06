/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KNearestNeighbour;

import java.io.File;
import java.io.IOException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

/**
 *
 * @author ASUS
 */
public class Driver {

    public static void main(String[] args) throws IOException, BiffException, WriteException {
        File f = new File("D:\\Kuliah\\Semester 5\\AI\\TuPro3\\Dataset.xls");
        File hasilTrain = new File("D:\\Kuliah\\Semester 5\\AI\\TuPro3\\DataHasilTraining.xls");
        File Testing = new File("D:\\Kuliah\\Semester 5\\AI\\TuPro3\\hasilTesting.xls");
//        BacaExcel train = new BacaExcel(f, 0);
//        train.inputAtribut();
        int maxk = 143;
        int k = 91;
        double accurate = 0;
        double maxAccurate = 0;
//        maxAccurate=61.949999999999996;
//        maxk=143;
        
//        Atribut t = new Atribut(f, hasilTrain, 0);
////        t.CariJarak(1, 2001, 2002, 4001, 5);
//        while ((accurate < 100)&&(k<1000)) {
//            accurate = t.CrossValidation(k);
//            if (accurate > maxAccurate) {
//                maxAccurate = accurate;
//                maxk = k;
//            }
//            System.out.println(k);
//            System.out.println(maxAccurate);
//            System.out.println(maxk);
//            k = k + 2;
//        }
//        System.out.println(maxAccurate);
//        t.getJarak().forEach(o -> System.out.println(o));

        //testing
        Testing test = new Testing(f, Testing);
        test.getJarak(maxk);
    }
}
