package Controler;

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class ExplorerTree extends JPanel {

	public static void ConstrutTree(){
		int result = 0;
		File file = null;
		String path = null;
		JFileChooser fileChooser = new JFileChooser();
		FileSystemView fsv = FileSystemView.getFileSystemView(); //ע���ˣ�������Ҫ��һ��
		System.out.println(fsv.getHomeDirectory()); //�õ�����·��
		fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
		fileChooser.setDialogTitle("��ѡ��Ҫ�ϴ����ļ�...");
		fileChooser.setApproveButtonText("ȷ��");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		result = fileChooser.showOpenDialog(null);//chatframe
		if (JFileChooser.APPROVE_OPTION == result) {
		path=fileChooser.getSelectedFile().getPath();
		System.out.println("path: "+path);
		}
/*		//��������һ�ַ����õ�����·����
		File desktop = new File(System.getProperty("user.home")+System.getProperty("file.separator")+"XX");
		fileChooser.setCurrentDirectory(desktop);
		//�ҵ��ĵ� ·���� 
		fsv.getDefaultDirectory());
		user.name 
		//�û����˻�����
		user.home 
		//�û�����Ŀ¼
		user.dir 
		//�û��ĵ�ǰ����Ŀ¼
		java.version Java 
		//����ʱ�����汾
		java.vendor Java 
		//����ʱ������Ӧ��
		java.vendor.url Java 
		//��Ӧ�̵� URL
		java.home Java 
		//��װĿ¼
		java.vm.specification.version Java 
		//������淶�汾
		java.vm.specification.vendor Java 
		//������淶��Ӧ��
		java.vm.specification.name Java 
		//������淶����
		java.vm.version Java 
		//�����ʵ�ְ汾
		java.vm.vendor Java 
		//�����ʵ�ֹ�Ӧ��
		java.vm.name Java 
		//�����ʵ������
		java.specification.version Java 
		//����ʱ�����淶�汾
		java.specification.vendor Java 
		//����ʱ�����淶��Ӧ��
		java.specification.name Java 
		//����ʱ�����淶����
		java.class.version Java 
		//���ʽ�汾��
		java.class.path Java 
		//��·��
		java.library.path 
		//���ؿ�ʱ������·���б�
		java.io.tmpdir 
		//Ĭ�ϵ���ʱ�ļ�·��
		java.compiler Ҫʹ�õ�  
		//JIT������������
		java.ext.dirs 
		//һ��������չĿ¼��·��
		os.name 
		//����ϵͳ������
		os.arch 
		//����ϵͳ�ļܹ�
		os.version 
		//����ϵͳ�İ汾
		 */
	}
	
}
