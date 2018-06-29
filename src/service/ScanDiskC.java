package service;

import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class ScanDiskC implements Runnable{
	private File[] files;
	private JTextArea area;
	private JProgressBar bar;
	private JComboBox<String> box;
	private int emptyFile=0;
	private int emptyDir=0;
	public String information="";
	public ScanDiskC(File[] files,JTextArea area,JProgressBar bar,JComboBox<String> box){
		this.files=files;
		this.area=area;
		this.bar=bar;
		this.box=box;
	}

	public void run() {
		scan(files,area,bar);
		area.append("ɨ����ϣ�");
		bar.setValue(bar.getMaximum());
		bar.setStringPainted(true);
		JOptionPane.showMessageDialog(null, "��"+emptyDir+"�����ļ���!"+" "+emptyFile+"�����ļ����Ѿ���ɾ��");
		box.setSelectedItem("��ѡ���̷�");
	}
	
	public void scan(File[] files,JTextArea area,JProgressBar bar){
		bar.setValue(100/files.length);
		bar.setStringPainted(true);
		for(File sub:files){
			area.append("����ɨ��"+sub.getAbsolutePath()+"...\n");
			if(sub.isFile()){
				if(sub.isHidden()){
					continue;
				}else{
					if(sub.length()==0){
						bar.setValue(bar.getValue()+1);
						bar.setStringPainted(true);
						emptyFile++;
						sub.delete();
					}
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
