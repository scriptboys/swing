package pane;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import javax.print.DocFlavor.READER;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import service.Encryption;
import service.FileHandle;

public class EncryptionPanel {
	private JPanel p=null;
	private JButton encryptButton;
	private JButton decryptButton;
	private JLabel srcLabel;
	private JLabel destLabel;
	private JButton srcButton;
	private JButton destButton;
	private JTextField srcField;
	private JTextField destField;
	
	File srcFile=null;
	File destFile=null;
	FileHandle fileHandle=new FileHandle();
	byte[] temp=null;
	public EncryptionPanel() {
		encryptButton=new JButton("加密");
		decryptButton=new JButton("解密");
		srcLabel=new JLabel("请选择将要加密的文件");
		destLabel=new JLabel("选择文件加密后的保存位置");
		srcButton=new JButton("浏览");
		destButton=new JButton("保存到这里");
		srcField=new JTextField();
		destField=new JTextField();
		//在创建面板时，直接在上面画背景
		p=new JPanel(){
			public void paintComponent(Graphics g){
				ImageIcon icon=new ImageIcon("src/image/hack.jpg");
				g.drawImage(icon.getImage(), 0, 0,400,400,p);
			}
		};
		p.setLayout(null);
		p.setOpaque(false);
		srcLabel.setBounds(50, 40, 130, 20);
		srcLabel.setForeground(Color.white);
		srcField.setBounds(50, 65, 150, 20);
		srcButton.setBounds(210, 65, 50, 20);
		
		destLabel.setBounds(50, 110, 160, 20);
		destLabel.setForeground(Color.white);
		destField.setBounds(50, 135, 150, 20);
		destButton.setBounds(210, 135, 75, 20);
		
		encryptButton.setBounds(70, 190, 50, 20);
		decryptButton.setBounds(130, 190, 50, 20);
		//按钮上的文字前后间距默认为0
		srcButton.setMargin(new Insets(0, 0, 0, 0));
		destButton.setMargin(new Insets(0, 0, 0, 0));
		encryptButton.setMargin(new Insets(0, 0, 0, 0));
		decryptButton.setMargin(new Insets(0, 0, 0, 0));
		p.add(srcLabel);
		p.add(srcField);
		p.add(srcButton);
		p.add(destLabel);
		p.add(destField);
		p.add(destButton);
		p.add(encryptButton);
		p.add(decryptButton);
		
		srcButton.addActionListener(actionListener);
		destButton.addActionListener(actionListener);
		encryptButton.addActionListener(actionListener);
		decryptButton.addActionListener(actionListener);
	}
	public JPanel getPanel() {
		return p;
	}
	ActionListener actionListener=new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser=new JFileChooser();
			switch(e.getActionCommand()){
				case "浏览":
							fileChooser.setCurrentDirectory(new File("C:/"));
							fileChooser.showOpenDialog(null);
							srcFile=fileChooser.getSelectedFile();
							if(srcFile!=null&&srcFile.length()!=0){
								srcField.setText(srcFile.getAbsolutePath());
								temp=fileHandle.read(srcFile);
							}
							break;
				case "保存到这里":
							if(srcField.getText().length()==0){
								JOptionPane.showMessageDialog(null, "请先点击上方浏览按钮来选择将要加密的文件");
							}else{
								fileChooser.setCurrentDirectory(new File("c:/"));
								fileChooser.showSaveDialog(null);
								destFile=fileChooser.getSelectedFile();
								if(destFile!=null&&srcFile.length()!=0){
									destField.setText(destFile.getAbsolutePath());
								}
							}
							break;
				case "加密":
							if(srcField.getText().length()==0||destField.getText().length()==0){
								JOptionPane.showMessageDialog(null, "请选择要加密的文件或加密后的保存路径");
							}else{
								Encryption enc=new Encryption();
								Map<String, Object> confidential=enc.getEncryptionByte(temp);
								byte[] encrypted=(byte[]) confidential.get("dest");
								if(destFile.exists()){
									int result=JOptionPane.showConfirmDialog(null, "文件已存在，是否覆盖？");
									if(JOptionPane.OK_OPTION==result){
										fileHandle.write(encrypted, destFile);
										JOptionPane.showMessageDialog(null, "加密成功！");
									}
								}else{
									fileHandle.write(encrypted, destFile);
									JOptionPane.showMessageDialog(null, "加密成功！");
								}
							}
							break;
				case "解密":
							fileChooser.setCurrentDirectory(new File("C:/"));
							int flag=fileChooser.showSaveDialog(null);
							try {
								File decryptionFile=fileChooser.getSelectedFile();
								byte[] b=fileHandle.read(decryptionFile);
								Encryption e1=new Encryption();
								byte[] dest=e1.getDecryptionByte(b);
								if(flag==0){
									fileHandle.write(dest, decryptionFile);
									JOptionPane.showMessageDialog(null, "解密成功！");
								}
							} catch (Exception e2) {
								
							}
							break;
			}
			fileHandle.close();
		}
	};
}
