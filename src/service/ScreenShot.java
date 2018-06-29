package service;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ScreenShot extends JFrame{
	static Toolkit toolkit=Toolkit.getDefaultToolkit();
	static Dimension screen=toolkit.getScreenSize();
	static int width=screen.width;
	static int height=screen.height;
	private LabelPaint screenImage=null;
	private int destX=0;
	private int destY=0;
	private JPopupMenu pm=null;
	private JMenuItem confirm=null;
	private JMenuItem cancel=null;
	private JMenuItem dosomething=null;
	public BufferedImage getBufferedImage(int x,int y,int width,int height){
		BufferedImage bufferedImage=null;
		try {
			Robot robot = new Robot();
			bufferedImage=robot.createScreenCapture(new Rectangle(x, y, width, height));
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bufferedImage;
	}
	public ScreenShot() {
		try {
			Image img=getBufferedImage(0, 0, width, height);
			screenImage=new LabelPaint();
			Border border=BorderFactory.createLineBorder(Color.red,5);
			screenImage.setBorder(border);
			screenImage.setIcon(new ImageIcon(img));
			pm=new JPopupMenu();
			confirm=new JMenuItem("保存");
			cancel=new JMenuItem("退出截屏");
			dosomething=new JMenuItem("dosomething");
			pm.add(confirm);
			pm.add(cancel);
			pm.add(dosomething);
			pm.setFocusable(true);

			
			this.add(screenImage);
			this.setUndecorated(true);
			this.setSize(width, height);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
			
			screenImage.addMouseListener(mouseAdapter);
			screenImage.addMouseMotionListener(mouseAdapter);
			cancel.addActionListener(actionListener);
			confirm.addActionListener(actionListener);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	//弹出菜单的点击事件
	ActionListener actionListener=new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==cancel){
				setVisible(false);
			}else if(e.getSource()==confirm){
				try {
					JFileChooser chooser=new JFileChooser();
					chooser.setSelectedFile(new File(".png"));
					chooser.setFileFilter(new FileNameExtensionFilter("png",new String[]{".png",".jpg"}));
					int result=chooser.showSaveDialog(null);
					if(result==0){
						File destFile=chooser.getSelectedFile();
						
						BufferedImage bi=getBufferedImage(destX, destY, screenImage.getX2()-destX, screenImage.getY2()-destY);
						ImageIO.write(bi, "png", destFile);
					}
					setVisible(false);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	};
	//监听截图时鼠标事件
	MouseAdapter mouseAdapter=new MouseAdapter() {
		public void mousePressed(MouseEvent e){
			screenImage.setBorder(null);
			screenImage.setX1(e.getX());
			screenImage.setY1(e.getY());
			destX=e.getX();
			destY=e.getY();
		}
		public void mouseDragged(MouseEvent e) {
			screenImage.setX2(e.getX());
			screenImage.setY2(e.getY());
			screenImage.repaint();
		}
		public void mouseReleased(MouseEvent e){
			try {
				screenImage.setX2(e.getX());
				screenImage.setY2(e.getY());
				screenImage.repaint();
				pm.show(screenImage, screenImage.getX2()-100, screenImage.getY2()+10);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};
}
class LabelPaint extends JLabel{
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
	public LabelPaint() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		super.paintComponent(g2d);
		//设置画笔粗细
		g2d.setStroke(new BasicStroke(3.0f));
		g2d.setColor(Color.red);
		g2d.drawRect(getX1(), getY1(), getX2()-getX1(), getY2()-getY1());
	}
}
