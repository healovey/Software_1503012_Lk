package intent;

import java.io.FileOutputStream;
import java.io.*;

public class loging {
	public static void log(String data) {
		BufferedWriter out = null;    
		try{
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/log.txt", true)));    
			out.write(data);
			out.write("\n");
		}catch (Exception e){
			e.printStackTrace();  
		}finally {
            try {     
                if(out != null){  
                    out.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }    
		}
		 
	}
}
