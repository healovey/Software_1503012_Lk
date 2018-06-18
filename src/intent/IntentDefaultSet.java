package intent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import java.io.*;
import java.util.HashMap;

public class IntentDefaultSet {
	
	public HashMap<String, String> aliasname;
	public String[] exenames;
	public boolean useexename;
	
	public IntentDefaultSet() {
		readAlias();
		useexename = false;
	}
	
	public IntentDefaultSet(String[] exenames) {
		
		this.exenames = exenames;
		useexename = true;
		readAlias();

	}
	
	private void readAlias() {
		aliasname = new HashMap<String, String>();
		try {
			File file = new File("data/aliasname");  
			BufferedReader reader = new BufferedReader(new FileReader(file));  
			String tempString = null;  
			while ((tempString = reader.readLine()) != null) {  
				 String[] t = tempString.split(":");
				 if(t.length != 2) {
//					 System.out.println("error in aliasname: " + tempString);
					 continue;
				 }
//				 System.out.println("add alias: " + t[0] +" -> "+t[1]);
				 aliasname.put(t[0], t[1]);
			}
			reader.close();
		}catch (IOException e) {
			// TODO: handle exception
		}finally {
			
		}
	}
	
}
