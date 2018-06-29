package test;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ScreenShotTest extends JFrame{
	Toolkit tk=Toolkit.getDefaultToolkit();
	Dimension ds=tk.getScreenSize();
	int width=(int) ds.getWidth();
	int height=(int) ds.getHeight();
	public ScreenShotTest() {		
		JLabel screen=new JLabel();
		JPanel p=new JPanel();
		p.setLayout(null);
		screen.setBounds(400, 200, 500, 400);
		Border border=BorderFactory.createLineBorder(Color.green);
		screen.setBorder(border);
		p.add(screen);
		this.add(p);
		this.setUndecorated(true);
		this.setOpacity(0.7f);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height-50);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_1){
					//setOpacity(0.3f);
					Image im=getImage();
					Border border=BorderFactory.createLineBorder(Color.red);
					screen.setBorder(border);
					screen.setIcon(new ImageIcon(im));
					
				}
				
			}
		});
	}
	public BufferedImage getImage(){
		Robot r=null;
		BufferedImage bi=null;
		try {
			r=new Robot();
			bi=r.createScreenCapture(new Rectangle(0, 0, 500,400));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bi;
	}
	public static void main(String[] args) {
		ScreenShotTest st=new ScreenShotTest();
	}
}
