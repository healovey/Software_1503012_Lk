package com.iflytek.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.iflytek.util.DrawableUtils;

public class DeterminedYourChoose extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//new 
		private JButton jbtnYes;
		private JButton jbtnNo;
		private JTextArea resultArea;
	//new
		
	public DeterminedYourChoose(){
		
		jbtnYes = addButton("res/button.png", "确定", 0, 320, 330, -1,
				"res/button");
		jbtnNo = addButton("res/button.png", "取消", 330, 320, 330, -1,
				"res/button");
		
		resultArea = new JTextArea("");
		resultArea.setBounds(30, 100, 540, 400);
		resultArea.setOpaque(false);
		resultArea.setEditable(false);
		resultArea.setLineWrap(true);
		resultArea.setForeground(AsrSpeechView.getTextArea().getForeground());
		resultArea.setFont(AsrSpeechView.getTextArea().getFont());
		
		setOpaque(false);
		setLayout(null);
		add(jbtnYes);
		add(jbtnNo);
		add(resultArea);
		jbtnYes.addActionListener(this);
		jbtnNo.addActionListener(this);
	}
		
	public JButton addButton(String imgName, String btnName, int x, int y,
			int imgWidth, int imgHeight, String iconPath) {

		JButton btn = null;
		ImageIcon img = new ImageIcon(imgName);
		btn = DrawableUtils.createImageButton(btnName, img, "center");
		int width = imgWidth, height = imgHeight;
		if (width == 1)
			width = img.getIconWidth();
		else if (width == -1)
			width = img.getIconHeight() * 4 / 5;

		if (height == 1)
			height = img.getIconWidth();
		else if (height == -1)
			height = img.getIconHeight() * 4 / 5;

		btn.setBounds(x, y, width, height);

		DrawableUtils.setMouseListener(btn, iconPath);

		return btn;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jbtnYes) {
			AsrSpeechView.openExE();
			
			JFrame frame = MainView.getFrame();
			AsrSpeechView asrSpeechView = MainView.getAsrSpeechView();
			
			asrSpeechView.getTextArea().append("\n(用户确定，打开应用)");
			
			frame.getContentPane().remove(this);
			frame.getContentPane().add(asrSpeechView);
			frame.getContentPane().validate();
			frame.getContentPane().repaint();

		} else if (e.getSource() == jbtnNo) {
			JFrame frame = MainView.getFrame();
			AsrSpeechView asrSpeechView = MainView.getAsrSpeechView();
			
			asrSpeechView.getTextArea().append("\n(用户取消，可能由于语音识别出错)");
			
			frame.getContentPane().remove(this);
			frame.getContentPane().add(asrSpeechView);
			frame.getContentPane().validate();
			frame.getContentPane().repaint();
		}
	}
	
	
	
	public void resultArea_setText(String str){
		resultArea.setText(str);
	}
	
	public void resultArea_append(String str){
		resultArea.append(str);
	}
}
