/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KNearestNeighbour;

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
public class Testing {

    private ArrayList jarak = new ArrayList<>();
    private File f;
    private File HasilTesting;

    public Testing(File f, File HasilTesting) {
        this.f = f;
        this.HasilTesting = HasilTesting;
    }
        
    public void getJarak(int k) throws IOException, BiffException, WriteException {
        ArrayList tampung = new ArrayList<>();
        double tmp = 0;
        int countHoax = 0;
        int countNo = 0;
        jarak.removeAll(jarak);
        WritableWorkbook wrwb = Workbook.createWorkbook(HasilTesting); //Nulis hasil Testing
        WritableSheet ws = wrwb.createSheet("Sheet", 3);
        //untuk K-Fold
        Workbook wb = Workbook.getWorkbook(f); //Baca Training
        Sheet datatraining = wb.getSheet(0);
        
        Workbook wbtesting = Workbook.getWorkbook(f); //Baca Testing
        Sheet datatesting = wbtesting.getSheet(1);
        
        for (int i = 1; i < datatesting.getRows(); i++) {
            Cell like = datatesting.getCell(1, i);
            Cell provokasi = datatesting.getCell(2, i);
            Cell komen = datatesting.getCell(3, i);
            Cell emosi = datatesting.getCell(4, i);
            int li1 = Integer.parseInt(like.getContents());
            int pr1 = Integer.parseInt(provokasi.getContents());
            int ko1 = Integer.parseInt(komen.getContents());
            int em1 = Integer.parseInt(emosi.getContents());
            for (int j = 1; j < datatraining.getRows(); j++) {
                Cell like2 = datatraining.getCell(1, j);
                Cell provokasi2 = datatraining.getCell(2, j);
                Cell komen2 = datatraining.getCell(3, j);
                Cell emosi2 = datatraining.getCell(4, j);
                int li2 = Integer.parseInt(like2.getContents());
                int pr2 = Integer.parseInt(provokasi2.getContents());
                int ko2 = Integer.parseInt(komen2.getContents());
                int em2 = Integer.parseInt(emosi2.getContents());
                tmp = Math.sqrt(Math.pow(li1 - li2, 2) + Math.pow(pr1 - pr2, 2) + Math.pow(ko1 - ko2, 2) + Math.pow(em1 - em2, 2));
                jarak.add(tmp);
                tampung.add(tmp);
                System.out.println(j);
            }
//            jarak.forEach(o -> System.out.println(o));
//            System.out.println(i);
            Comparator c = null;
            tampung.sort(c);
//            tampung.forEach(o -> System.out.println(o));
            for (int p = 0; p < k; p++) {
                Object o = tampung.get(p);
                int index = jarak.indexOf(o);
                Cell hoax = datatraining.getCell(5, index + 1);
                if (hoax.getContents().equals("0")) {
                    countNo++;
                } else if (hoax.getContents().equals("1")) {
                    countHoax++;
                }
            }
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
    }
}
