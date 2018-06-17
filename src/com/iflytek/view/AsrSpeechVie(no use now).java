package com.iflytek.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import org.json.*;

import com.iflytek.cloud.speech.ErrorCode;
import com.iflytek.cloud.speech.GrammarListener;
import com.iflytek.cloud.speech.RecognizerListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechRecognizer;
import com.iflytek.util.DebugLog;
import com.iflytek.util.DrawableUtils;

public class AsrSpeechView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	// 语法文件
	//private final static String mGrammar = "#ABNF 1.0 UTF-8;\nlanguage zh-CN;\nmode voice;\nroot $main;\n$main = [请帮我打开][请打开][帮我打开][打开][我要打开]$place2 ;\n$place2 =  腾讯QQ  | 微信 | 百度 | 爱奇艺 | 迅雷 | 浏览器 | 文件夹 | 开始菜单 | Eclipse | 酷狗 | word | excel| Xshell;";
	private String mGrammar_head = "#ABNF 1.0 UTF-8;\nlanguage zh-CN;\nmode voice;\nroot $main;\n$main = [请帮我打开][请打开][帮我打开][打开][我要打开]$place2 ;\n$place2 = ";
	private String mGrammar = "";
	//public static String mGrammar = "";	
	
	
	private JButton jbtnRecognizer;
	private JButton jbtnHome;
	private JLabel labelWav;
	private static JTextArea resultArea;
	
	//字体设置按钮所用到的相关元件
	private JPopupMenu mSettingMenu = new JPopupMenu( "设置" );	//主菜单
	JTable table;    
	DefaultTableModel tableM;    
    JScrollPane jsp; 
    JMenuItem jm1, jm2, jm3, jm4, sizeS, sizeM , sizeL;
    private JButton jbtnSet;
	
    //所提取到的文字信息
  	private static String str = null;
    //所提取到的应用程序的名称
	private static String exe = null;
	
	private String mGrammarId = null;
	private SpeechRecognizer mAsr = null;

	/**
	 * 初始化按钮. 初始化按钮图片背景、大小、鼠标点击事件
	 */
	public AsrSpeechView() {
		ImageIcon img = new ImageIcon("res/mic_01.png");
		labelWav = new JLabel(img);
		labelWav.setBounds(0, 0, img.getIconWidth(),
				img.getIconHeight() * 4 / 5);

		jbtnRecognizer = addButton("res/button.png", "开始识别", 50, 300, 500, -1,
				"res/button");
		jbtnRecognizer.add(labelWav, BorderLayout.WEST);
		jbtnRecognizer.setEnabled(false);
		
		jbtnHome = addButton("res/home.png", "", 20, 20, 1, 1, "res/home");
		jbtnSet = addButton( "res/setting.png", "", 534, 20, 1, 1, "res/setting" );
		
		resultArea = new JTextArea("");
		
		resultArea.setBounds(30, 100, 540, 400);
		resultArea.setOpaque(false);
		resultArea.setEditable(false);
		resultArea.setLineWrap(true);
		resultArea.setForeground(Color.BLACK);
		Font font = new Font("宋体", Font.BOLD, 21);
		resultArea.setFont(font);
		setOpaque(false);
		setLayout(null);

		add(jbtnRecognizer);
		add(resultArea);
		add(jbtnHome);
		add(jbtnSet);
		
		// 初始化识别对象
		mAsr = SpeechRecognizer.createRecognizer();
		
		jbtnRecognizer.addActionListener(this);
		jbtnHome.addActionListener(this);
		jbtnSet.addActionListener(this);
		

		FileLinkOpen softList = new FileLinkOpen();
		mGrammar = mGrammar_head + softList.AllToString();
		
		//上传语法
		
		mAsr.setParameter(SpeechConstant.ENGINE_TYPE, "cloud");
		mAsr.setParameter(SpeechConstant.TEXT_ENCODING, "utf-8");
		int ret = mAsr.buildGrammar("abnf", mGrammar, grammarListener);
		if (ret != ErrorCode.SUCCESS)
			DebugLog.Log("语法构建失败,错误码：" + ret);
		
		JMenuInit();
}

	
	public void	JMenuInit(){    
		
        JMenu TextColor = new JMenu ("设置颜色");  
        JMenu TextSize = new JMenu ("设置大小"); 
        
        sizeS = new JMenuItem("小");  
        sizeM = new JMenuItem("中");  
        sizeL = new JMenuItem("大");
       
        TextSize.add(sizeS);
        TextSize.add(sizeM);
        TextSize.add(sizeL);
        
        jm1 = new JMenuItem("蓝");  
        jm2 = new JMenuItem("绿");  
        jm3 = new JMenuItem("红"); 
        jm4 = new JMenuItem("黑");
        
     /*//设置选项的颜色
        jm1.setBackground(Color.blue);  
        jm2.setBackground(Color.red);  
        jm3.setBackground(Color.green);  
        jm4.setBackground(Color.black);
        
        */

        
        TextColor.add(jm1);
        TextColor.add(jm2);
        TextColor.add(jm3);
        TextColor.add(jm4);
        

        mSettingMenu.add(TextColor);  
        mSettingMenu.add(TextSize);
 
        jm1.addActionListener(this);  
        jm2.addActionListener(this);  
        jm3.addActionListener(this);
        jm4.addActionListener(this);
        
        sizeS.addActionListener(this);  
        sizeM.addActionListener(this);  
        sizeL.addActionListener(this);
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

	/**
	 * 按钮监听器实现
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == jbtnRecognizer) {
			if (mGrammarId != null) {
				// 设置云端返回结果为json格式（默认返回josn格式，可设置为xml）
				mAsr.setParameter(SpeechConstant.RESULT_TYPE, "json");
				mAsr.setParameter(SpeechConstant.ASR_AUDIO_PATH, "./asr_test.pcm");
				// 设置云端识别使用的语法id
				mAsr.setParameter(SpeechConstant.CLOUD_GRAMMAR, mGrammarId);
				if (!mAsr.isListening())
					mAsr.startListening(recognizerListener);
				else {
					mAsr.stopListening();
					asrSpeechInitUI();
				}
			}

		} else if (e.getSource() == jbtnHome) {
			if (null != mAsr) {
				mAsr.cancel();
				mAsr.destroy();
			}
			
			MainView.resetAsrSpeechView();
			JFrame frame = MainView.getFrame();
			
			frame.getContentPane().remove(this);
			JPanel panel = ((MainView) frame).getMainJpanel();
			frame.getContentPane().add(panel);
			frame.getContentPane().validate();
			frame.getContentPane().repaint();
		
		} else if( jbtnSet.equals(e.getSource()) ){
			
			DebugLog.Log( "actionPerformed setting" );
			mSettingMenu.show( this, this.jbtnSet.getX(), this.jbtnSet.getY()+50 );
		
		} else if (e.getSource().equals(jm1)){  
		
			resultArea.setForeground(Color.BLUE);
			
		} else if(e.getSource().equals(jm2)){ 
	
			resultArea.setForeground(Color.green);  
       
		} else if(e.getSource().equals(jm3)){  
	
			resultArea.setForeground(Color.red);  
			
        } else if(e.getSource().equals(jm4)){  
	
			resultArea.setForeground(Color.black);  
			
        } else if (e.getSource().equals(sizeS)){  
    		
				Font newFont = new Font( "宋体", 0, 12 );
				resultArea.setFont( newFont );
				
			
        } else if(e.getSource().equals(sizeM)){ 
    	
				Font newFont = new Font( "宋体", 0, 20 );
				resultArea.setFont( newFont );
		
        } else if(e.getSource().equals(sizeL)){  
    		
				Font newFont = new Font( "宋体", 0, 30 );
				resultArea.setFont( newFont );
				} 

	}

	/**
	 * 构建语法监听器
	 */
	private GrammarListener grammarListener = new GrammarListener() {
		@Override
		public void onBuildFinish(String grammarId, SpeechError error) {
			if (error == null) {
				mGrammarId = grammarId;
				resultArea.setText("语法构建成功");
				jbtnRecognizer.setEnabled(true);
				DebugLog.Log("语法构建成功：" + grammarId);
			} else {
				DebugLog.Log("语法构建失败,错误码：" + error.getErrorCode());
				resultArea.setText( "语法构建失败,错误码：" + error.getErrorDescription(true) );
			}
		}
	};
	
	AsrSpeechView asrSpeechView = this;
	private RecognizerListener recognizerListener = new RecognizerListener() {

		/**
		 * 获取识别结果. 获取RecognizerResult类型的识别结果，并对结果进行累加，显示到Area里
		 */
		@Override
		public void onResult(RecognizerResult results, boolean islast) {
			// 结果返回为默认json格式,但讯飞没有提供 提取 results内部的json文件的方法
			// 强行将result 文件转化为json
			String text = results.getResultString();
			JSONObject json = null;
			try {
				json = new JSONObject(text);
				
				//str 是从json中提取的文字信息， 例如："打开微信"
				str = json.getJSONArray("ws").getJSONObject(0).getJSONArray("cw").getJSONObject(0).getString("w");
				
				//强行提取 str 中的关键字， 例如： exe = "微信"
				exe = getIdentity(str);
				
				if( exe.length() == 0 && islast ){
					resultArea.append("\n没有此应用");
					asrSpeechInitUI();
				}else if( islast ){
					//显示文字信息
					resultArea.append("\n" + str);
					
					//如果存在应用exe
					//页面跳转， 询问是否打开exe？
					JFrame frame = MainView.getFrame();
					DeterminedYourChoose determinedYourChoose = new DeterminedYourChoose();
					
					determinedYourChoose.resultArea_setText( "是否打开" + exe + "?");
					
					frame.getContentPane().remove(asrSpeechView);
					frame.getContentPane().add(determinedYourChoose);
					frame.getContentPane().validate();
					frame.getContentPane().repaint();
					
					asrSpeechInitUI();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		public void onVolumeChanged(int volume) {
			if (volume == 0)
				volume = 1;
			else if (volume >= 6)
				volume = 6;
			labelWav.setIcon(new ImageIcon("res/mic_0" + volume + ".png"));
		}

		@Override
		public void onError(SpeechError error) {
			if (null != error){
				DebugLog.Log("onError Code：" + error.getErrorCode());
				AsrSpeechView.this.resultArea.append( error.getErrorDescription(true) );
			}
			asrSpeechInitUI();
		}

		@Override
		public void onEvent(int eventType, int arg1, int agr2, String msg) {

		}

		@Override
		public void onBeginOfSpeech() {
			((JLabel) jbtnRecognizer.getComponent(0)).setText("请说话...");
		}

		@Override
		public void onEndOfSpeech() {
			((JLabel) jbtnRecognizer.getComponent(0)).setText("等待结果");
		}
	};

	/**
	 * 识别结束，恢复初始状态
	 */
	public void asrSpeechInitUI() {

		labelWav.setIcon(new ImageIcon("res/mic_01.png"));
		((JLabel) jbtnRecognizer.getComponent(0)).setText("开始识别");
	}
	
	public static JTextArea getTextArea() {
		return resultArea;
	}
	
	public static String getStr() {
		return str;
	}
	
	public static String getExe() {
		return exe;
	}
	
	public static void openExE(){
		System.out.println(exe);
	}
	
	private String getIdentity(String str){
		String ret = "";
		if(str.indexOf("微信") != -1)
			ret = "微信";
		if(str.indexOf("腾讯") != -1)
			ret = "腾讯";
		if(str.indexOf("QQ") != -1)
			ret = "QQ";
		if(str.indexOf("百度") != -1)
			ret = "百度";
		if(str.indexOf("爱奇艺") != -1)
			ret = "爱奇艺 ";
		if(str.indexOf("迅雷") != -1)
			ret = "迅雷 ";
		if(str.indexOf("浏览器") != -1)
			ret = "浏览器";
		if(str.indexOf("文件夹") != -1)
			ret = "文件夹";
		if(str.indexOf("开始菜单") != -1)
			ret = "开始菜单";
		if(str.indexOf("Eclipse") != -1)
			ret = "Eclipse";
		if(str.indexOf("酷狗") != -1)
			ret = "酷狗 ";
		if(str.indexOf("word") != -1)
			ret = "word";
		if(str.indexOf("excel") != -1)
			ret = "excel";
			return ret;
	}

}
