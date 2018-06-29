package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PaintTest extends JFrame{
	private JPanel p=null;
	private JLabel label=null;
	private JButton b=null;
	int x1=0;
	int y1=10;
	int x2=50;
	int y2=50;
	public PaintTest() {
		label=new JLabel();
		b=new JButton("click");
		MyPaint p1=new MyPaint();
		this.add(p1);
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		p1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				p1.setX2(e.getX());
				p1.setY2(e.getY());
				p1.repaint();
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				p1.setX1(e.getX());
				p1.setY1(e.getY());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		p1.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				p1.setX2(e.getX());
				p1.setY2(e.getY());
				p1.repaint();
			}
		});
	}
	public static void main(String[] args) {
		PaintTest pt=new PaintTest();
	}
}
class MyPaint extends JPanel{
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawLine(getX1(),getY1(),getX2(),getY2());
		g.drawRect(x1, y1, x2-x1, y2-y1);
	}
}
