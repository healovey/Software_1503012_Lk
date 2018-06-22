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
		FileSystemView fsv = FileSystemView.getFileSystemView(); //注意了，这里重要的一句
		System.out.println(fsv.getHomeDirectory()); //得到桌面路径
		fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
		fileChooser.setDialogTitle("请选择要上传的文件...");
		fileChooser.setApproveButtonText("确定");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		result = fileChooser.showOpenDialog(null);//chatframe
		if (JFileChooser.APPROVE_OPTION == result) {
		path=fileChooser.getSelectedFile().getPath();
		System.out.println("path: "+path);
		}
/*		//这是另外一种方法得到桌面路径：
		File desktop = new File(System.getProperty("user.home")+System.getProperty("file.separator")+"XX");
		fileChooser.setCurrentDirectory(desktop);
		//我的文档 路径： 
		fsv.getDefaultDirectory());
		user.name 
		//用户的账户名称
		user.home 
		//用户的主目录
		user.dir 
		//用户的当前工作目录
		java.version Java 
		//运行时环境版本
		java.vendor Java 
		//运行时环境供应商
		java.vendor.url Java 
		//供应商的 URL
		java.home Java 
		//安装目录
		java.vm.specification.version Java 
		//虚拟机规范版本
		java.vm.specification.vendor Java 
		//虚拟机规范供应商
		java.vm.specification.name Java 
		//虚拟机规范名称
		java.vm.version Java 
		//虚拟机实现版本
		java.vm.vendor Java 
		//虚拟机实现供应商
		java.vm.name Java 
		//虚拟机实现名称
		java.specification.version Java 
		//运行时环境规范版本
		java.specification.vendor Java 
		//运行时环境规范供应商
		java.specification.name Java 
		//运行时环境规范名称
		java.class.version Java 
		//类格式版本号
		java.class.path Java 
		//类路径
		java.library.path 
		//加载库时搜索的路径列表
		java.io.tmpdir 
		//默认的临时文件路径
		java.compiler 要使用的  
		//JIT编译器的名称
		java.ext.dirs 
		//一个或多个扩展目录的路径
		os.name 
		//操作系统的名称
		os.arch 
		//操作系统的架构
		os.version 
		//操作系统的版本
		 */
	}
	
}
