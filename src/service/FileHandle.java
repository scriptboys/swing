package service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;

public class FileHandle {
	private FileInputStream fis;
	private FileOutputStream ois;
	private byte[] arr=null;
	public void close(){
		try {
			if(fis!=null){
				fis.close();
			}
			if(ois!=null){
				ois.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public byte[] read(File srcFile){
		try {
			if(srcFile!=null){
				fis=new FileInputStream(srcFile);
				arr=new byte[(int) srcFile.length()];
				fis.read(arr, 0, arr.length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	public void write(byte[] arr,File destFile){
		try {
			if(arr.length!=0){
				ois=new FileOutputStream(destFile);
				ois.write(arr, 0, arr.length);
				ois.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
