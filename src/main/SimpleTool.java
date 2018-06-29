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
		//各种功能业务类
		ScanPanel scanPanel=new ScanPanel();
		EncryptionPanel encryptionPanel=new EncryptionPanel();
		ScreenShotPane screenShotPane=new ScreenShotPane(this);
		//各个功能的面板
		JPanel p=scanPanel.getPane();
		JPanel p1=encryptionPanel.getPanel();
		JPanel p2=screenShotPane.getPanel();
		
		tabbedPane.addTab("扫描目标盘符", p);
		tabbedPane.addTab("加密/解密",p1);
		tabbedPane.addTab("截图工具",p2);
	}
	//初始化系统托盘
	public void setSystemTray(){
		ImageIcon icon=new ImageIcon("src/image/eye1.png");
		Image image=icon.getImage();
		//初始化托盘图标
		trayIcon=new TrayIcon(image);
		//初始化托盘
		systemTray=SystemTray.getSystemTray();
		PopupMenu popupMenu=new PopupMenu();
		MenuItem cancel=new MenuItem("取消");
		MenuItem exit=new MenuItem("退出");
		try {
			trayIcon.setToolTip("小东西真别致");
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
			//监听关闭窗口
			public void windowClosing(WindowEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "是否退出?");
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
		//从托盘还原窗口
		MouseAdapter mouseAdapter=new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2){
					setExtendedState(JFrame.NORMAL);
					setVisible(true);
				}
				//右键单击托盘弹出菜单
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
