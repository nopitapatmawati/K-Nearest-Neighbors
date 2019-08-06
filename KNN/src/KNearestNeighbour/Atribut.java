/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KNearestNeighbour;

import static java.awt.SystemColor.text;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author ASUS
 */
public class Atribut {

    private ArrayList jarak = new ArrayList<>();
    private File f;
    private File HasilTrain;
    private int nosheet;
    private double cost;

    public Atribut(File f, File hasilTrain, int nosheet) throws BiffException, IOException {
        this.f = f;
        this.HasilTrain = hasilTrain;
        this.nosheet = nosheet;
    }

    public Atribut() {
    }

    public ArrayList SelectionSort() {
        ArrayList tempe = jarak;
//        ArrayList min;
//        int kecil;
//        for (int i = 0; i < tempe.size() - 1; i++) {
//            kecil = i;
//            for (int j = i + 1; j < tempe.size(); j++) {
//                if ((double) tempe.get(j) < (double) tempe.get(kecil)) {
//                    kecil = j;
//                    double tmp = (double) tempe.get(kecil);
//                    tempe.add(kecil, tempe.get(i));
//                    tempe.add(i, tmp);
//                    System.out.println("cek");
//                }
//            }
//        }
//        tempe.forEach(o -> System.out.println(o));
        return tempe;
    }

    public void CariJarak(int t0, int t1, int t2, int t3, int k) throws IOException, BiffException, WriteException {
        ArrayList tampung = new ArrayList<>();
        double tmp = 0;
        int i = t0;
        int countHoax = 0;
        int countNo = 0;
        jarak.removeAll(jarak);
        WritableWorkbook wrwb = Workbook.createWorkbook(HasilTrain);
        WritableSheet ws = wrwb.createSheet("Sheet", 3);
        //untuk K-Fold
//        while (i < t3) {
        for (i = t0; i < t1; i++) {
            Workbook wb = Workbook.getWorkbook(f);
            Sheet s = wb.getSheet(nosheet);
            Cell like = s.getCell(1, i);
            Cell provokasi = s.getCell(2, i);
            Cell komen = s.getCell(3, i);
            Cell emosi = s.getCell(4, i);
            int li1 = Integer.parseInt(like.getContents());
            int pr1 = Integer.parseInt(provokasi.getContents());
            int ko1 = Integer.parseInt(komen.getContents());
            int em1 = Integer.parseInt(emosi.getContents());
            for (int j = t2; j < t3; j++) {
                Cell like2 = s.getCell(1, j);
                Cell provokasi2 = s.getCell(2, j);
                Cell komen2 = s.getCell(3, j);
                Cell emosi2 = s.getCell(4, j);
                int li2 = Integer.parseInt(like2.getContents());
                int pr2 = Integer.parseInt(provokasi2.getContents());
                int ko2 = Integer.parseInt(komen2.getContents());
                int em2 = Integer.parseInt(emosi2.getContents());
                tmp = Math.sqrt(Math.pow(li1 - li2, 2) + Math.pow(pr1 - pr2, 2) + Math.pow(ko1 - ko2, 2) + Math.pow(em1 - em2, 2));
                jarak.add(tmp);
                tampung.add(tmp);
//                System.out.println(j);
            }
//            jarak.forEach(o -> System.out.println(o));
//            System.out.println(i);
            Comparator c = null;
            tampung.sort(c);
//            tampung.forEach(o -> System.out.println(o));
            for (int p = 0; p < k; p++) {
                Object o = tampung.get(p);
                int index = jarak.indexOf(o);
//                System.out.println(p);
//                System.out.println(jarak.indexOf(tampung.get(p)));
//                System.out.println(tampung.get(p));
//                System.out.println(jarak.get(index).equals(tampung.get(p)));
//                System.out.println(jarak.get(index));
//                System.out.println(i+" "+index+" "+jarak.get(index)+" "+tampung.get(index));
                Cell hoax = s.getCell(5, index + 1);
//                System.out.println(hoax.getContents());
                if (hoax.getContents().equals("0")) {
                    countNo++;
                } else if (hoax.getContents().equals("1")) {
                    countHoax++;
                }
            }
//            System.out.println("masuk");
            if (countHoax > countNo) {
                Label l = new Label(6, i, "1");
                ws.addCell(l);

            } else {
                Label l1 = new Label(6, i, "0");
                ws.addCell(l1);
            }
            countHoax = 0;
            countNo = 0;

            jarak.removeAll(jarak);
            tampung.removeAll(tampung);
        }
        wrwb.write();
        wrwb.close();
//        }
    }

    public double getAkurasi() throws IOException, BiffException {
        Workbook wb = Workbook.getWorkbook(f);
        Sheet s = wb.getSheet(nosheet);

        Workbook wb1 = Workbook.getWorkbook(HasilTrain);
        Sheet s1 = wb1.getSheet(nosheet);
        
        int hitung =0;
        
        for (int i = 1; i < s1.getRows(); i++) {
            Cell dtraining = s.getCell(5, i);
            Cell dhasil = s1.getCell(6, i);
            if(dtraining.getContents().equals(dhasil.getContents())){
//                System.out.println(dtraining.getContents()+" "+dhasil.getContents());
                hitung++;
            }
        }
//        System.out.println(s1.getRows());
//        System.out.println(hitung);
        return (double) hitung/2000;
    }

    public double CrossValidation(int k) throws BiffException, IOException, WriteException {

        double akurasi;

        int t0 = 1;
        int t1 = 2001;
        int t2 = 2002;
        int t3 = 4001;
        CariJarak(t0, t1, t2, t3, k);
        akurasi = getAkurasi();
        System.out.println(akurasi);
        System.out.println(getAkurasi());
        t0 = 2002;
        t1 = 4001;
        t2 = 1;
        t3 = 2001;
        CariJarak(t0, t1, t2, t3, k);
        akurasi += getAkurasi();
        System.out.println(akurasi);
        System.out.println(getAkurasi());
        System.out.println((akurasi/2)*100);
        return (akurasi/2)*100;
    }

    public ArrayList getJarak() {
        return jarak;
    }

}
