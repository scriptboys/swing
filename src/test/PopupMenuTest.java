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
		
		JMenu m1=new JMenu("�Ӳ˵�");
		JMenuItem item=new JMenuItem("���");
		JMenuItem item1=new JMenuItem("����");
		m1.add(item);
		m1.add(item1);
		
		JMenuItem item2=new JMenuItem("ֱ�Ӳ˵���");
		JMenuItem item3=new JMenuItem("�˳�");
		
		jpm.add(m1);
		jpm.add(item2);
		jpm.add(item3);
		
		p.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
				if(e.getButton()==MouseEvent.BUTTON3){
					System.out.println("�ұ�");
					jpm.show(p, 50, 50);
				}else if(e.getButton()==MouseEvent.BUTTON2){
					System.out.println("�м�");
				}else {
					System.out.println("����");
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
