package Controler;
import java.util.ArrayList;
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
	
}
