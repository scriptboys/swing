package service;

import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class ScanDisk implements Runnable{
	private File[] files;
	private JTextArea area;
	private JProgressBar bar;
	private JComboBox<String> box;
	private int emptyFile=0;
	private int emptyDir=0;
	public String information="";
	public int i=0;
	public ScanDisk(File[] files,JTextArea area,JProgressBar bar,JComboBox<String> box) {
		this.files=files;
		this.area=area;
		this.bar=bar;
		this.box=box;
	}
	public void run() {
		scan(files,area,bar);
		area.append("扫描完毕！");
		bar.setValue(bar.getMaximum());
		bar.setStringPainted(true);
		JOptionPane.showMessageDialog(null, "有"+emptyDir+"个空文件夹!"+" "+emptyFile+"个空文件！已经被删除");
		box.setSelectedItem("请选择盘符");
	}
	public synchronized void scan(File[] files,JTextArea area,JProgressBar bar){
		bar.setValue(100/files.length);
		bar.setStringPainted(true);
		for(File sub:files){
			if(sub.isFile()){
				if(sub.isHidden()){
					continue;
				}else{
					if(sub.length()==0){
						bar.setValue(bar.getValue()+1);
						bar.setStringPainted(true);
						area.append("正在扫描"+sub.getAbsolutePath()+"...");
						emptyFile++;
						sub.delete();
					}
					area.append("正在扫描"+sub.getAbsolutePath()+"...\n");
				}
			}
			if(sub.isDirectory()){
				if(sub.isHidden()){
					continue;
				}else{ 
					if(sub.listFiles().length!=0){
						scan(sub.listFiles(), area,bar);
					}
					bar.setValue(bar.getValue()+1);
					bar.setStringPainted(true);
					sub.delete();
				}
			}
		}
	}
	 
}
