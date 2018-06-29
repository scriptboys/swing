package pane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingFormatArgumentException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import service.ScanDisk;
import service.ScanDiskC;

public class ScanPanel extends JFrame{
	//private JTabbedPane jTabbedPane=null;
	private JPanel p=null;
	private JComboBox<String> diskSelectedComboBox=null;
	private JButton showDetail=null;
	private JButton startup=null;
	private JProgressBar progressBar=null;
	private JTextArea infoArea=null;
	private JScrollPane jsp=null;
	private final String[] disks={"«Î—°‘Ò≈Ã∑˚","C≈Ã◊®«Â","D≈Ã","E≈Ã","F≈Ã","G≈Ã"};
	private File file=null;
	private ScanDisk sd=null;
	private ScanDiskC sdc=null;
	private int i=0;
	private File[] deskC=null;
	public ScanPanel(){
		p=new JPanel();
		diskSelectedComboBox=new JComboBox<>(disks);
		showDetail=new JButton("œ‘ æœ∏Ω⁄");
		startup=new JButton("ø™ º…®√Ë");
		progressBar=new JProgressBar();
		infoArea=new JTextArea();
		jsp=new JScrollPane();
		
		p.setLayout(null);
		
		diskSelectedComboBox.setBounds(15, 10, 120, 30);
		startup.setBounds(150,15,60,20);
		startup.setHorizontalAlignment(JButton.LEFT);
		startup.setMargin(new Insets(0,0,0,0));
		
		progressBar.setBounds(15, 45, 280, 20);
		progressBar.setMaximum(100);
		progressBar.setStringPainted(true);
		showDetail.setBounds(296, 45, 60, 20);
		showDetail.setHorizontalAlignment(JButton.LEFT);
		showDetail.setMargin(new Insets(0, 0, 0, 0));
		
		jsp.setBounds(15, 90, 360, 200);
		infoArea.setEditable(false);
		infoArea.setVisible(false);
		jsp.setViewportView(infoArea);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setVisible(false);
		
		p.add(diskSelectedComboBox);
		p.add(startup);
		p.add(showDetail);
		p.add(progressBar);
		p.add(jsp);
		
		
		//ÃÌº”º‡Ã˝
		showDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoArea.setVisible(true);
				jsp.setVisible(true);
			}
		});
		diskSelectedComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e1) {
				if(e1.getStateChange()==ItemEvent.SELECTED){
					String selectedInfo=String.valueOf(diskSelectedComboBox.getSelectedItem());
					switch(selectedInfo){
						case "C≈Ã◊®«Â":
							i=1;
							infoArea.setText("");
							File f1=new File("C:/Documents and Settings/Administrator/Local Settings/Temp");
							File f2=new File("C:/Documents and Settings/Administrator/Local Settings/History");
							File f3=new File("C/User/Documents and Settings/Local Settings/Temporary Internet Files");
							File f4=new File("C/User/Administrator/Local Settings/History");
							File f5=new File("C/User/Administrator/Local Settings/Temp");
							File f6=new File("C/User/Administrator/Local Settings/Temporary Internet Files");
							deskC=new File[]{f1,f2,f3,f4};
							break;
						case "D≈Ã":
							infoArea.setText("");
							file=new File("D:/");
							break;
						case "E≈Ã":
							infoArea.setText("");
							file=new File("E:/");
							break;
						case "F≈Ã":
							infoArea.setText("");
							file=new File("F:/");
							break;
						case "G≈Ã":
							infoArea.setText("");
							file=new File("G:/");
							break;
						case "«Î—°‘Ò≈Ã∑˚":
							file=null;
							deskC=null;
							progressBar.setValue(progressBar.getMinimum());
							progressBar.setStringPainted(true);
							break;
					}
				}
			}
		});
		startup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i==0&&file==null){
					JOptionPane.showMessageDialog(null, "«Î—°‘Ò“™…®√Ëµƒ≈Ã∑˚");
				}else if(i==0&&file!=null){
					sd=new ScanDisk(file.listFiles(),infoArea,progressBar,diskSelectedComboBox);
					Thread t=new Thread(sd);
					t.start();
					//diskSelectedComboBox.setSelectedItem(disks[0]);
				}else if(i==1){
					sdc=new ScanDiskC(deskC, infoArea,progressBar,diskSelectedComboBox);
					Thread t=new Thread(sdc);
					t.start();
					i=0;
				}
			}
		});
	}
	
	public JPanel getPane(){
		return p;
	}
}
