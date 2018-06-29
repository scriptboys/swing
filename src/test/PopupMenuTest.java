package test;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class PopupMenuTest extends JFrame{
	public PopupMenuTest() {
		JPanel p=new JPanel();
		JPopupMenu jpm=new JPopupMenu();
		
		JMenu m1=new JMenu("子菜单");
		JMenuItem item=new JMenuItem("你好");
		JMenuItem item1=new JMenuItem("世界");
		m1.add(item);
		m1.add(item1);
		
		JMenuItem item2=new JMenuItem("直接菜单项");
		JMenuItem item3=new JMenuItem("退出");
		
		jpm.add(m1);
		jpm.add(item2);
		jpm.add(item3);
		
		p.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
				if(e.getButton()==MouseEvent.BUTTON3){
					System.out.println("右边");
					jpm.show(p, 50, 50);
				}else if(e.getButton()==MouseEvent.BUTTON2){
					System.out.println("中间");
				}else {
					System.out.println("哈哈");
				}
			}
		});
		this.add(p);
		this.setSize(400,400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		PopupMenuTest pmt=new PopupMenuTest();
	}
}
