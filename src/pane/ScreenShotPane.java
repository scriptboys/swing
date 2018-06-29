package pane;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import service.ScreenShot;

public class ScreenShotPane {
	private JButton screenShot=null;
	private JLabel img=null;
	private JPanel p=null;
	private JFrame jfFrame=null;
	public ScreenShotPane(JFrame jfFrame) {
		this.jfFrame=jfFrame;
		p=new JPanel();
		screenShot=new JButton("µã»÷½ØÍ¼");
		img=new JLabel();
		p.setLayout(null);
		screenShot.setBounds(50, 50, 150, 30);
		img.setBounds(60, 100, 150, 150);
		p.add(screenShot);
		screenShot.addActionListener(actionListener);
	}
	public JPanel getPanel(){
		return p;
	}
	
	ActionListener actionListener=new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				jfFrame.dispose();
				ScreenShot screenShot=new ScreenShot();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	};
}
