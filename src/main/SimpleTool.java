package main;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import pane.EncryptionPanel;
import pane.ScanPanel;
import pane.ScreenShotPane;
import service.ScreenShot;


public class SimpleTool extends JFrame{
	private JTabbedPane tabbedPane=new JTabbedPane();
	private	SystemTray systemTray=null;
	private TrayIcon trayIcon=null;
	public SimpleTool() {
		init();
		this.add(tabbedPane);
		this.setTitle("CustomTool");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	public void init(){
		setSystemTray();
		//���ֹ���ҵ����
		ScanPanel scanPanel=new ScanPanel();
		EncryptionPanel encryptionPanel=new EncryptionPanel();
		ScreenShotPane screenShotPane=new ScreenShotPane(this);
		//�������ܵ����
		JPanel p=scanPanel.getPane();
		JPanel p1=encryptionPanel.getPanel();
		JPanel p2=screenShotPane.getPanel();
		
		tabbedPane.addTab("ɨ��Ŀ���̷�", p);
		tabbedPane.addTab("����/����",p1);
		tabbedPane.addTab("��ͼ����",p2);
	}
	//��ʼ��ϵͳ����
	public void setSystemTray(){
		ImageIcon icon=new ImageIcon("src/image/eye1.png");
		Image image=icon.getImage();
		//��ʼ������ͼ��
		trayIcon=new TrayIcon(image);
		//��ʼ������
		systemTray=SystemTray.getSystemTray();
		PopupMenu popupMenu=new PopupMenu();
		MenuItem cancel=new MenuItem("ȡ��");
		MenuItem exit=new MenuItem("�˳�");
		try {
			trayIcon.setToolTip("С���������");
			popupMenu.add(cancel);
			popupMenu.add(exit);
			systemTray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);		
			}
		});
		WindowAdapter winAdapter=new WindowAdapter() {
			//�����رմ���
			public void windowClosing(WindowEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "�Ƿ��˳�?");
				if(JOptionPane.OK_OPTION==result){
					System.exit(0);
				}else if(JOptionPane.NO_OPTION==result){
					dispose();
				}else{
					setExtendedState(JFrame.NORMAL);
					setVisible(true);
				}
			}
		};
		//�����̻�ԭ����
		MouseAdapter mouseAdapter=new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2){
					setExtendedState(JFrame.NORMAL);
					setVisible(true);
				}
				//�Ҽ��������̵����˵�
				if(e.getButton()==MouseEvent.BUTTON3){
					trayIcon.setPopupMenu(popupMenu);
				}
			}
		};
		
		this.addWindowListener(winAdapter);
		trayIcon.addMouseListener(mouseAdapter);
	}
	public static void main(String[] args) {
		SimpleTool st=new SimpleTool();
	}
}
