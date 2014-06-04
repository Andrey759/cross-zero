package Controller.Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileClass {
    
    private String filename = null;
    private File file;
    private FileWriter fw;
    //BufferedWriter bw;
    //PrintWriter pw;
    private Scanner sc;
    private boolean inf = false, outf = false;
    
    public void makeDir(String dirname) {
        File dir = new File(dirname);
        try {
            if(!dir.isDirectory()) {
                if(dir.exists()) {
                    dir.delete();
                }
                dir.mkdir();
            }
        } catch (Exception e) {
            ExceptionLog.println("Ошибка создания директории " + dirname);
        }
    }
    
    public boolean issetFile(String filename) {
        File tmp = new File(filename);
        return tmp.isFile();
    }
    
    public void openRd(String filename) {
        try {
            this.close();
            this.filename = filename;
            inf = true;
            file = new File(this.filename);
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            ExceptionLog.println("Ошибка открытия файла " + this.filename + " для чтения");
        } catch(Exception e) {
            ExceptionLog.println("Ошибка в блоке открытия файла " + this.filename + " для чтения");
            ExceptionLog.println(e);
        }
    }
    
    public String readStr() {
        if(inf) {
            if(sc.hasNext())
                return sc.next();
            else 
                return null;
        } else {
            ExceptionLog.println("Попытка записи в неоткрытый файл");
            return null;
        }
    }
    
    public int readUnsignedInt() {
        if(inf) {
            if(sc.hasNextInt())
                return sc.nextInt();
            else 
                return -1;
        } else {
            ExceptionLog.println("Попытка записи в неоткрытый файл");
            return -1;
        }
    }
    
    public void openWr(String filename) {
        try {
            this.close();
            this.filename = filename;
            outf = true;
            file = new File(this.filename);
            fw = new FileWriter(file);
            //bw = new BufferedWriter(fw);
            //pw = new PrintWriter(bw);
        } catch (IOException e) {
            ExceptionLog.println("Ошибка открытия файла " + this.filename + " для записи");
        } catch (Exception e) {
            ExceptionLog.println("Ошибка в блоке открытия файла " + this.filename + " для записи");
            ExceptionLog.println(e);
        }
    }
    
    public void write(String text) {
        if(outf) {
            try {
                fw.write(text);
            } catch (IOException e) {
                ExceptionLog.println("Ошибка записи строки "+text+" в файл "+filename);
            } catch (Exception e) {
                ExceptionLog.println("Ошибка в блоке записи строки "+text+" в файл "+filename);
                ExceptionLog.println(e);
            }
        } else ExceptionLog.println("Попытка записи в неоткрытый файл");
    }
    
    public void write(int integer) {
        if(outf) {
            try {
                fw.write(" "+String.valueOf(integer));
            } catch (IOException e) {
                ExceptionLog.println("Ошибка записи числа "+String.valueOf(integer)+" в файл "+filename);
            } catch (Exception e) {
                ExceptionLog.println("Ошибка в блоке записи числа "+String.valueOf(integer)+" в файл "+filename);
                ExceptionLog.println(e);
            }
        } else ExceptionLog.println("Попытка записи в неоткрытый файл");
    }
    
    public void close() {
        try {
            if(inf) sc.close();
            if(outf) fw.close();
            inf = false;
            outf = false;
            this.filename = null;
        } catch (IOException e) {
            ExceptionLog.println("Ошибка закрытия файла");
        } catch(Exception e) {
            ExceptionLog.println("Ошибка в блоке закрытия файла");
            ExceptionLog.println(e);
        }
    }
    
}
