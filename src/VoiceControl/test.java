package Controler;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class test {
	
	public static void main(String[] args) throws IOException{
		fileData filedata = new fileData();
		FileSave save = new FileSave("temp.txt");
		save.saveObjToFile(filedata);
		System.out.println(save.getObjFromFile().getFileName().get(1));
	}
	/*
	public static void test() throws IOException{
		if(Desktop.isDesktopSupported()){
			Desktop desk = Desktop.getDesktop();
			//File fp = new File("C:\\Program Files (x86)\\Tencent\\TIM\\Bin\\QQScLauncher.exe");
			File fp = new File("C:\\Users\\Rick_zhu\\Desktop\\ATM.docx");
			desk.open(fp);
		}
		else
			System.out.println("System do not support Desktop class");
	}
	
	public static void openWindowsExe() {  
	    final Runtime runtime = Runtime.getRuntime();  
	    Process process = null;  
	    try {  
	        //final String command = "C:\\Program Files (x86)\\Tencent\\TIM\\Bin\\QQScLauncher.exe";
	        //process = runtime.exec(command);
	    	//System.out.println("openfile");
	    	test();
	    } catch (final Exception e) {  
	        System.out.println("Error win exec!");  
	    }  
	}
	*/
}


