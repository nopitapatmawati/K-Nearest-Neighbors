/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KNearestNeighbour;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author ASUS
 */
public class BacaExcel {
    private ArrayList like = new ArrayList<>();
    private ArrayList provokasi = new ArrayList<>();
    private ArrayList emosi = new ArrayList<>();
    private ArrayList komentar = new ArrayList<>();
    private ArrayList hoax = new ArrayList<>();
    private File f;
    private int nosheet;


    public BacaExcel(File f, int nosheet) throws BiffException, IOException {
        this.f = f;
        this.nosheet = nosheet;
        
    }

    public ArrayList getLike() {
        return like;
    }

    public ArrayList getProvokasi() {
        return provokasi;
    }

    public ArrayList getEmosi() {
        return emosi;
    }

    public ArrayList getKomentar() {
        return komentar;
    }
    
    public void inputAtribut() throws BiffException, IOException{
        int count=0;
        Workbook wb = Workbook.getWorkbook(f);
        Sheet s = wb.getSheet(nosheet);
        for(int i=1; i<s.getRows(); i++){
            Cell c = s.getCell(1, i);
//            System.out.println(c.getContents());
            like.add(Integer.parseInt(c.getContents()));
//            count++;
        }
        for(int i=1; i<s.getRows(); i++){
            Cell c = s.getCell(2, i);
            provokasi.add(Integer.parseInt(c.getContents()));
        }
        for(int i=1; i<s.getRows(); i++){
            Cell c = s.getCell(3, i);
            komentar.add(Integer.parseInt(c.getContents()));
        }
        for(int i=1; i<s.getRows(); i++){
            Cell c = s.getCell(4, i);
            emosi.add(Integer.parseInt(c.getContents()));
        }
        for(int i=1; i<s.getRows(); i++){
            Cell c = s.getCell(5, i);
            hoax.add(Integer.parseInt(c.getContents()));
        }
//        System.out.println(count);
    }
    
    //    public void hitungCost() throws IOException, BiffException{
//        Workbook wb = Workbook.getWorkbook(f);
//        Sheet s = wb.getSheet(nosheet);
//        for(int i=1; i<s.getRows(); i++){
//            Cell like = s.getCell(1, i);
//            Cell provokasi = s.getCell(2, i);
//            Cell komen = s.getCell(3, i);
//            Cell emosi = s.getCell(4, i);
//            int l = Integer.parseInt(like.getContents());
//            int p = Integer.parseInt(provokasi.getContents());
//            int k = Integer.parseInt(komen.getContents());
//            int e = Integer.parseInt(emosi.getContents());
////            System.out.println(emosi.getContents());
//            cost = Math.sqrt(Math.pow(1,2)+Math.pow(p,2)+Math.pow(k,2)+Math.pow(e,2));
//            jarak.add(cost);
//        } 
//    }
}
