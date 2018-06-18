package com.iflytek.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.util.DrawableUtils;
import com.iflytek.util.Version;

import intent.IntentDefaultSet;

/**
 * MscDemo It's a Sample using MSC SDK, include tts, isr. you can just press
 * button to use it.
 * 
 * @author cyhu 2012-06-14
 */
@SuppressWarnings("serial")
public class MainView extends JFrame implements ActionListener {
	private JPanel mMainJpanel;
	private JPanel mContentPanel;
	private static JFrame mJframe;
	private static IatSpeechView iatSpeechView;
	private static LoginView loginView;
	private static JLabel label_login;
	private static JLabel label_main;
	private JButton jbtnGrammar;
	private JButton jbtnExit;
	
	
	private FileLinkOpen fileLinkOpen ;
	private static IntentDefaultSet intentDefaultSet;
	
	/*
	private JButton jbtnExe1;
	private JButton jbtnExe2;
	private JButton jbtnExe3;
	private JButton jbtnExe4;*/
	
	/**
	 * 界面初始化.
	 * 
	 */
	@SuppressWarnings("deprecation")
	public MainView() {
		// 初始化
		{	StringBuffer param = new StringBuffer();
		param.append( "appid=" + Version.getAppid() );
//		param.append( ","+SpeechConstant.LIB_NAME_32+"=myMscName" );
		SpeechUtility.createUtility( param.toString() );
		}
		
		// 设置界面大小，背景图片
		ImageIcon background = new ImageIcon("res/index_bg.png");
		ImageIcon background1 = new ImageIcon("res/index_bg1.png");

		label_login = new JLabel(background1);
		label_main = new JLabel(background);
		label_login.setBounds(0, 0, background1.getIconWidth(),
				background1.getIconHeight());
		label_main.setBounds(0, 0, background.getIconWidth(),
				background.getIconHeight());
	    
		getLayeredPane().add(label_login, new Integer(Integer.MIN_VALUE));
		
		
		int frameWidth = background.getIconWidth();
		int frameHeight = background.getIconHeight();

		setSize(frameWidth, frameHeight);
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		iatSpeechView = new IatSpeechView();
		loginView = new LoginView();
		
		ImageIcon imgGrammar = new ImageIcon("res/btn_grammar.png");
		jbtnGrammar = this.createImageButton(imgGrammar);
		jbtnGrammar.setBounds(160, 150, imgGrammar.getIconWidth(),
				imgGrammar.getIconHeight());
		DrawableUtils.setMouseListener(jbtnGrammar, "res/btn_grammar");
		
		ImageIcon imgExit = new ImageIcon("res/btn_exit.png");
		jbtnExit = this.createImageButton(imgExit);
		jbtnExit.setBounds(160, 150, imgExit.getIconWidth(),
				imgExit.getIconHeight());
		DrawableUtils.setMouseListener(jbtnExit, "res/btn_exit");
		
		
		GridLayout gridlayout = new GridLayout(0, 2);
		gridlayout.setHgap(10);
		mMainJpanel = new JPanel(gridlayout);
		setLayout(null);
		mMainJpanel.setOpaque(false);
		mMainJpanel.add(jbtnGrammar);
		mMainJpanel.add(jbtnExit);

		jbtnGrammar.addActionListener(this);
		jbtnExit.addActionListener(this);

		mContentPanel = new JPanel(new BorderLayout());
		
		mContentPanel.add(loginView);
	//	mContentPanel.add(mMainJpanel, BorderLayout.CENTER);
		mContentPanel.setOpaque(false);
		
		setLocationRelativeTo(null);
		setContentPane(mContentPanel);
		setVisible(true);
		
		fileLinkOpen = new FileLinkOpen();
		String allFilename [] = new String[fileLinkOpen.AllFileSize()];
		for (int i = 0; i<fileLinkOpen.AllFileSize(); i++){
			allFilename[i] = (String) fileLinkOpen.getAllFileName().get(i);
		}
		intentDefaultSet = new IntentDefaultSet(allFilename);
		
	}

	/**
	 * Demo入口函数.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		mJframe = new MainView();
		
		FileLinkOpen fi = new FileLinkOpen();
		
		System.out.println(fi.getAllFileName().size());
	
		System.out.println(fi.AllToString());
	}

	public static JFrame getFrame() {
		return mJframe;
	}

	public JButton createImageButton(ImageIcon img) {
		JButton button = new JButton("");
		button.setIcon(img);
		button.setSize(img.getIconWidth(), img.getIconHeight());
		button.setBackground(null);

		button.setBorder(null);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);

		return button;
	}
	
	public MainView(int i){
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == jbtnGrammar) {
			mContentPanel.remove(mMainJpanel);
			mContentPanel.add(iatSpeechView);
			mContentPanel.revalidate();
			mContentPanel.repaint();
		} else if(e.getSource() == jbtnExit){
			mContentPanel.remove(mMainJpanel);
			getLayeredPane().add(label_login, new Integer(Integer.MIN_VALUE));
			getLayeredPane().remove(label_main);
			mContentPanel.add(loginView);
			mContentPanel.validate();
			mContentPanel.repaint();
		}
		
	}

	public JPanel getMainJpanel() {
		return mMainJpanel;
	}

	public JPanel getContePanel() {
		return mContentPanel;
	}
	
/*	public static AsrSpeechView getAsrSpeechView() {
		return asrSpeechView;
	}*/
	
	/*public static void resetAsrSpeechView() {
		asrSpeechView = new AsrSpeechView();
	}
	*/
	public static JLabel getlabel_login() {
		return label_login;
	}
	
	public static JLabel getlabel_main() {
		return label_main;
	}
	
	public static IntentDefaultSet getIntentDefaultSet(){
		return intentDefaultSet;
	}
	
}