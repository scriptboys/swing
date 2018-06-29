package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LisenerTest extends JFrame{
	public LisenerTest() {
		JButton b1=new JButton("a");
		JButton b2=new JButton("b");
		b1.setName("你好");
		
		this.setLayout(null);
		b1.setBounds(10, 10, 100, 20);
		b2.setBounds(30, 30, 100, 30);
		this.add(b1);
		this.add(b2);
		this.setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		b1.addActionListener(listener);
		b2.addActionListener(listener);
		
	}
	ActionListener listener=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if("a".equals(e.getActionCommand())){
				System.out.println("点击了a");
			}else{
				System.out.println("点击了B");
			}
			
		}
	};
	
	public static void main(String[] args) {
		LisenerTest t=new LisenerTest();
	}
}
