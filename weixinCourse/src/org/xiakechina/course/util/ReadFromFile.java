package org.xiakechina.course.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class ReadFromFile {
    public static void readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            ReadFromFile.showAvailableBytes(in);
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static String readFileByChars(String fileName) {
    	StringBuffer sbString=new StringBuffer();
        File file = new File(fileName);
        Reader reader = null;
        try {
            System.out.println("锟斤拷锟街凤拷为锟斤拷位锟斤拷取锟侥硷拷锟斤拷锟捷ｏ拷一锟轿讹拷一锟斤拷锟街节ｏ拷");
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                if (((char) tempchar) != '\r') {
                    sbString.append((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbString.toString();
    }

    /**
     * 锟斤拷锟斤拷为锟斤拷位锟斤拷取锟侥硷拷锟斤拷锟斤拷锟斤拷锟节讹拷锟斤拷锟斤拷锟叫的革拷式锟斤拷锟侥硷拷
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("锟斤拷锟斤拷为锟斤拷位锟斤拷取锟侥硷拷锟斤拷锟捷ｏ拷一锟轿讹拷一锟斤拷锟叫ｏ拷");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一锟轿讹拷锟斤拷一锟叫ｏ拷直锟斤拷锟斤拷锟斤拷null为锟侥硷拷锟斤拷锟斤拷
            while ((tempString = reader.readLine()) != null) {
                // 锟斤拷示锟叫猴拷
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void readFileByRandomAccess(String fileName) {
        RandomAccessFile randomFile = null;
        try {
            randomFile = new RandomAccessFile(fileName, "r");
            long fileLength = randomFile.length();
            int beginIndex = (fileLength > 4) ? 4 : 0;
            randomFile.seek(beginIndex);
            byte[] bytes = new byte[10];
            int byteread = 0;
            while ((byteread = randomFile.read(bytes)) != -1) {
                System.out.write(bytes, 0, byteread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("锟斤拷前锟街斤拷锟斤拷锟斤拷锟斤拷锟叫碉拷锟街斤拷锟斤拷为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileName = "C:/Users/Administrator/Desktop/code.txt";
        String code=ReadFromFile.readFileByChars(fileName);
        String[] codeArray=code.split(",");
        Map<String,String> codeMap=new HashMap<String, String>();
        for(String c:codeArray){
        	String [] arr=c.split(":");
        	codeMap.put(arr[0], arr[1]);
        	System.out.println(arr[0]+"\n"+arr[1]);
        }
        
    }
}