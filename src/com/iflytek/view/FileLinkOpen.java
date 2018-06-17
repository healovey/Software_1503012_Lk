package com.iflytek.view;




import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileLinkOpen{
	private  ArrayList fileAddress = new ArrayList<String>();
    private ArrayList fileName = new ArrayList<String>();
	public FileLinkOpen() {
		 //    �ڴ�Ŀ¼�����ļ�   
        String baseDIR = "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs";    
        //    ����չ��Ϊlnk���ļ�   
        String file_name = "*.lnk";    
        findFiles(baseDIR, file_name,fileAddress);    
        if (fileAddress.size() == 0) {   
            System.out.println("No File Fount.");   
        } else {
            for (int i = 0; i < fileAddress.size(); i++) {   
                String fileaddress = fileAddress.get(i).toString();
                int length = fileaddress.length();
                char [] filename = new char[length];
                int k = 0;
                for(int j = length - 1;j >= 0; j--){
                	if(fileaddress.charAt(j) == '\\'){
                		break;
                	}
                	filename[k] = fileaddress.charAt(j);
                	k++;
                }
                String file = "";
                for(int j = 0;j < k;j++){
                	if(filename[k - 1 - j] == '.')
                		break;
                	file = file+filename[k - 1 - j];
                }
                fileName.add(i,file);
                //System.out.println(fileAddress.get(i));
            	//��ʾ���ҽ����    
            }
            /*for(int i = 0;i < fileAddress.size();i++){
            	System.out.println("�ļ���Ϊ��"+fileName.get(i)+" ��ַΪ "+ fileAddress.get(i));
            }
            */
        }   

	}
	
	public ArrayList getAllFileAddress(){
		return fileAddress;
	}
	
	public ArrayList getAllFileName(){
		return fileName;
	}
	public int AllFileSize(){
		return fileName.size();
	}
	
	public String AllToString(){
		
		String grammar = "";
		int size = fileName.size()-1;
		for(int i = 0;i <= 20;i++){
			if(i != 20)
			grammar += fileName.get(i).toString()+" | ";
			else {
				grammar += fileName.get(i).toString()+";";
			}
		}
		
		return grammar;
	}
	
	public static void main(String argv[]){
		//FileLinkOpen fileOpen = new FileLinkOpen();
		
	}
	/**  
     * �ݹ�����ļ�  
     * @param baseDirName  ���ҵ��ļ���·��  
     * @param targetFileName  ��Ҫ���ҵ��ļ���  
     * @param fileList  ���ҵ����ļ�����  
     */  
    public static void findFiles(String baseDirName, String targetFileName, ArrayList fileList) {   
      
    	File baseDir = new File(baseDirName);		// ����һ��File����
		if (!baseDir.exists() || !baseDir.isDirectory()) {	// �ж�Ŀ¼�Ƿ����
			System.out.println("�ļ�����ʧ�ܣ�" + baseDirName + "����һ��Ŀ¼��");
		}
        String tempName = null;   
        //�ж�Ŀ¼�Ƿ����   
        File tempFile;
    	File[] files = baseDir.listFiles();
    	for (int i = 0; i < files.length; i++) {
			tempFile = files[i];
			if(tempFile.isDirectory()){
				findFiles(tempFile.getAbsolutePath(), targetFileName, fileList);
			}else if(tempFile.isFile()){
				tempName = tempFile.getName();
				if(wildcardMatch(targetFileName, tempName)){
					// ƥ��ɹ������ļ�����ӵ������
					fileList.add(tempFile.getAbsoluteFile());
				}
			}
		}
    }   
       
    /**  
     * ͨ���ƥ��  
     * @param pattern    ͨ���ģʽ  
     * @param str    ��ƥ����ַ���  
     * @return    ƥ��ɹ��򷵻�true�����򷵻�false  
     */  
    private static boolean wildcardMatch(String pattern, String str) {   
        int patternLength = pattern.length();   
        int strLength = str.length();   
        int strIndex = 0;   
        char ch;   
        for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {   
            ch = pattern.charAt(patternIndex);   
            if (ch == '*') {   
                //ͨ����Ǻ�*��ʾ����ƥ���������ַ�   
                while (strIndex < strLength) {   
                    if (wildcardMatch(pattern.substring(patternIndex + 1),   
                            str.substring(strIndex))) {   
                        return true;   
                    }   
                    strIndex++;   
                }   
            } else if (ch == '?') {   
                //ͨ����ʺ�?��ʾƥ������һ���ַ�   
                strIndex++;   
                if (strIndex > strLength) {   
                    //��ʾstr���Ѿ�û���ַ�ƥ��?�ˡ�   
                    return false;   
                }   
            } else {   
                if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {   
                    return false;   
                }   
                strIndex++;   
            }   
        }   
        return (strIndex == strLength);   
    } 
}

