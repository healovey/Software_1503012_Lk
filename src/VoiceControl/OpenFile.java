package VoiceControl;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class OpenFile {
	
	public static void main(String[] args) throws IOException{
		openWindowsExe("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\��Ѷ���\\TIM\\TIM.lnk");
	}
	
	public OpenFile(){
		
	}
	public static void test() throws IOException{
		if(Desktop.isDesktopSupported()){
			Desktop desk = Desktop.getDesktop();
			//File fp = new File("C:\\Program Files (x86)\\Tencent\\TIM\\Bin\\QQScLauncher.exe");
			//String str = ""
			File fp = new File("C:\\Users\\Rick_zhu\\Desktop\\Steam.lnk");
			desk.open(fp);
		}
		else
			System.out.println("System do not support Desktop class");
	}
	
	public static boolean openWindowsExe(String fileAddress) {  
	    final Runtime runtime = Runtime.getRuntime();  
	    Process process = null;  
	    try {  
	        fileAddress = "\""+fileAddress+"\"";
	    	final String command = "cmd /c "+fileAddress;
	        process = runtime.getRuntime().exec(command);
	    	return true;
	    } catch (final Exception e) {  
	        System.out.println("Error win exec!");
	        return false;
	    }  
	}
}


