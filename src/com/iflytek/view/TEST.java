package com.iflytek.view;

import VoiceControl.applicationControl;
import intent.HttpGet;
import intent.IntentDefaultSet;
import intent.IntentGet;

public class TEST {
	public static void main(String args[]) throws Throwable {
		
		
		FileLinkOpen fi = new FileLinkOpen();
		String allFilename [] = new String[fi.AllFileSize()];
		for (int i = 0; i<fi.AllFileSize(); i++){
			allFilename[i] = (String) fi.getAllFileName().get(i);
		}
		
		IntentDefaultSet intentDefaultSet = new IntentDefaultSet(allFilename);
		
		//System.out.println(allFilename[168]);
		String str = "打开腾讯qq。";
		
		IntentGet result = HttpGet.getIntent(str, intentDefaultSet);
		result.out();
		System.out.println(result.getSoftwareName());
	}
	
	
	
	
/*public static void main(String args[]) throws Throwable {
		
		
		applicationControl on = new applicationControl();
		on.fileOpen("腾讯QQ");
	}*/


}
