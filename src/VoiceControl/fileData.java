package VoiceControl;
import java.util.ArrayList;
import java.awt.Frame;
import java.io.Serializable;

public class fileData implements Serializable{
	private ArrayList fileName;
	private ArrayList fileAddress;
	public fileData() {
		FileLinkOpen fileLink = new FileLinkOpen();
		fileName = fileLink.getAllFileName();
		fileAddress = fileLink.getAllFileAddress();
	}
	
	public ArrayList getFileName(){
		return fileName;
	}
	
	public ArrayList getFileAddress(){
		return fileAddress;
	}
	
	public int getSize(){
		return fileName.size();
	}
//	public static void main(String[] args) {
//		fileData data = new fileData();
//		for(int i = 0;i < data.getSize();i++)
//			System.out.println(data.getFileName().get(i));
//	}
	
}
