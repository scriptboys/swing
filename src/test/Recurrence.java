package test;

import java.io.File;

import org.omg.Messaging.SyncScopeHelper;

public class Recurrence {
	static File file=new File("G:");
	
	int i=0;
	public void doSomeThing(File[] subFile){
		 for(int i=0;i<subFile.length;i++){
			 
			if(subFile[i].isFile()){
				System.out.println(subFile[i].getAbsolutePath());
			}else if((subFile[i].isDirectory())&&(subFile[i].length()!=0)){
				doSomeThing(subFile[i].listFiles());
			}else{
				System.out.println(subFile[i].getName()+"是空文件夹");
				continue;
			}
		}
	}
	public void test(){
		File file=new File("C:");
		File[] files=file.listFiles();
		for(File dest:files){
			if(dest.isDirectory()){
				//System.out.println(dest.getName()+" "+"可读？"+dest.canRead()+" "+"可写？"+dest.canWrite());
				for(int i=0;i<dest.listFiles().length;i++){
					System.out.println(dest.getName());
					System.out.println(dest.listFiles()[i].getName());
				}
			}
		}
	}
	
	public void test1(File[] dest){
		for(File f:dest){
			if(f.isFile()){
				f.delete();
			}else{
				File[] sub=f.listFiles();
				test1(sub);
			}
		}
	}
	public void test2(File[] dest){
		for(File f:dest){
			if(f.isFile()){
				f.delete();
			}else{
				File[] sub=f.listFiles();
				test1(sub);
			}
		}
	}
	public void test3(File[] dest){
		for(File f:dest){
			if(f.isFile()){
				f.delete();
			}else{
				File[] sub=f.listFiles();
				test1(sub);
			}
		}
	}
	public static void main(String[] args) {
		Recurrence recurrence=new Recurrence();
		//recurrence.test();
		//File file1=new File("C:/Documents and Settings/Administrator/Local Settings/Temp");
		//recurrence.test1(file1.listFiles());
		//recurrence.test2();
		//recurrence.test3();
	}
}
