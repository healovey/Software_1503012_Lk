package com.iflytek.view;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.iflytek.util.DrawableUtils;

public class LoginView extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton btnNormalLogin;
	private JButton btnRegister;
	
	private JTextArea txtUserName;
	private JTextArea txtUserId;
	private JTextArea txtUserPasswd;
	private JTextArea txtUserPd;
	private JLabel  txtTitle;
	private JLabel  txtHint;
	
	public LoginView() {
		
		ImageIcon imgNormalLogin = new ImageIcon("res/btn.png");
		btnNormalLogin = DrawableUtils.createImageButton("登陆", imgNormalLogin, "center");
		btnNormalLogin.setBounds(10, 280, imgNormalLogin.getIconWidth(), imgNormalLogin.getIconHeight());
		DrawableUtils.setMouseListener(btnNormalLogin, "res/btn");

		btnRegister = DrawableUtils.createImageButton("注册", imgNormalLogin, "center");
		btnRegister.setBounds(320, 280, imgNormalLogin.getIconWidth(), imgNormalLogin.getIconHeight());
		DrawableUtils.setMouseListener(btnRegister, "res/btn");
		
		Font font = new Font("宋体", Font.BOLD, 30);

		txtTitle = new JLabel("用户登入",SwingConstants.CENTER);
		txtTitle.setBounds(100, 30, 400, 50);
		txtTitle.setOpaque(false);
		txtTitle.setLayout(getLayout());
		txtTitle.setForeground(Color.BLACK);
		txtTitle.setFont(new Font("宋体", Font.BOLD, 45));
		
		//登入错误提示
		txtHint = new JLabel("",SwingConstants.CENTER);
		txtHint.setBounds(25, 230, 550, 50);
		txtHint.setOpaque(false);
		txtHint.setLayout(getLayout());
		txtHint.setForeground(Color.BLACK);
		txtHint.setFont(new Font("宋体", Font.BOLD, 18));
		
		txtUserName = new JTextArea("用户名:");
		txtUserName.setBounds(30, 100, 110, 50);
		txtUserName.setOpaque(false);
		txtUserName.setForeground(Color.BLACK);
		txtUserName.setFont(font);
		txtUserName.setEnabled( false );


		txtUserId = new JTextArea("");
		txtUserId.setBounds(140, 100, 430, 50);
		txtUserId.setForeground(Color.BLACK);
		txtUserId.setFont(font);


		txtUserPasswd = new JTextArea("密码:");
		txtUserPasswd.setBounds(30, 170, 110, 50);
		txtUserPasswd.setOpaque(false);
		txtUserPasswd.setForeground(Color.BLACK);
		txtUserPasswd.setFont(font);
		txtUserPasswd.setEnabled( false );
		
		
		txtUserPd = new JTextArea("");
		txtUserPd.setBounds(140, 170, 430, 50);
		txtUserPd.setForeground(Color.BLACK);
		txtUserPd.setFont(font);
		
		setOpaque(false);
		setLayout(null);
		
		add(btnNormalLogin);
		add(btnRegister);
		add(txtUserId);
		add(txtUserPd);
		add(txtUserPasswd);
		add(txtUserName);
		add(txtTitle);
		add(txtHint);

		btnNormalLogin.addActionListener(this);
		btnRegister.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnNormalLogin) {
			// 过滤掉不合法的用户名
			String userid = txtUserId.getText().toString();
			String userpw = txtUserPd.getText().toString();
			if (null==userid || null==userpw || ""==userid || ""==userpw) {
				txtHint.setText("用户名或密码不能为空");
				return;
			} else {
				Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
				Matcher m1 = p.matcher(userid);
				Matcher m2 = p.matcher(userpw);
				if (m1.find() || m2.find()) {
					txtHint.setText("不支持中文字符");
					return;
				} else if (userid.contains(" ") || userpw.contains(" ")) {
					txtHint.setText("不能包含空格");
					return;
				} else if (!userid.matches("^[a-zA-Z][a-zA-Z0-9_]{5,17}")) {
					txtHint.setText("用户名应为6-18个字母、数字或下划线的组合，以字母开头");
					return;
				} else if (!userpw.matches("^[a-zA-Z0-9_]{5,17}")) {
					txtHint.setText("密码应为6-18个字母、数字或下划线的组合"); 
					return;
				}		
			}
			
			txtHint.setText( "" );
			setTxtUserId("");
			setTxtUserPasswd("");
			if(Judge(userid, userpw)){
				JFrame frame = MainView.getFrame();
				frame.getContentPane().remove(this);
				frame.getLayeredPane().add(MainView.getlabel_main(), new Integer(Integer.MIN_VALUE));
				frame.getLayeredPane().remove(MainView.getlabel_login());
				frame.getContentPane().add( ((MainView) frame).getMainJpanel(), BorderLayout.CENTER);
				frame.getContentPane().validate();
				frame.getContentPane().repaint();
			} else{
				txtHint.setText( "用户名或密码错误" );
			}
			}else if(e.getSource() == btnRegister){
				txtHint.setText( "注册成功" );
			}
	}
	
	public void setTxtUserId(String txtUserId) {
		this.txtUserId.setText(txtUserId);
	}

	public void setTxtUserPasswd(String txtUserPd) {
		this.txtUserPd.setText(txtUserPd);
	}
	
	private boolean Judge(String UserId, String UserPw){
		
		return true;
			
	}
	

}
