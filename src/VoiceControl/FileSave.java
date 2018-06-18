package VoiceControl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileSave {
	private String fileName;
	
	public FileSave(){
		
	}
	
	public FileSave(String fileName){
		this.fileName=fileName;
	}
	
	/*
	 * ��person���󱣴浽�ļ���
	 * params:
	 * 	p:person�����
	 */
	public void saveObjToFile(fileData p){
		try {
			//д�������Ķ���
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(fileName));
			
			oos.writeObject(p);                 //��Person����pд�뵽oos��
			oos.flush();
			oos.close();                        //�ر��ļ���
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/*
	 * ���ļ��ж������󣬲��ҷ���Person����
	 */
	public fileData getObjFromFile(){
		try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fileName));
			
			fileData person=(fileData)ois.readObject();              //��������
			
			return person;                                       //���ض���
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}

