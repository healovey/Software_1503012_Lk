package Controler;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
 
public class SystemSoftware {  
    private JFrame f = new JFrame("��ϵͳ�Ѿ���װ������б�");
    private JTextPane textPane = new JTextPane();
    private MyTable myTable=new MyTable();
    public static Charset charset = Charset.forName("GBK");
    public SystemSoftware() {
        f.setLocation(300, 200);
        f.setSize(800,500);
        JScrollPane jScrollPane = new JScrollPane(myTable.getTable());
        f.add(jScrollPane);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

        try {
            check();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void check() throws Exception {
        textPane.setText("���Ѿ���װ�������");
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        //process = runtime
         //       .exec("cmd /c reg query HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\");
        process = runtime
                .exec("cmd /c reg query HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\");
        BufferedReader in = new BufferedReader(new InputStreamReader(process
                .getInputStream(),"GBK"));
        String string = null;
        while ((string = in.readLine()) != null) {
            process = runtime.exec("cmd /c reg query " + string
                    + " /v DisplayName");
            BufferedReader name = new BufferedReader(new InputStreamReader(
                    process.getInputStream(),"GBK"));
            String[] message = queryValue(string);
            if(message!=null) myTable.addRow(message);
            f.repaint();
        }
        in.close();
        process.destroy();

    }

    //�����ѯÿһ���������ϸ��Ϣ
    private String[] queryValue(String string) throws IOException {
        String nameString = "";
        String versionString = "";
        
        String publisherString="";
        String uninstallPathString = "";
        
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        BufferedReader br = null;
        
        process = runtime.exec("cmd /c reg query " + string + " /v DisplayName");
        br = new BufferedReader(new InputStreamReader(process
                .getInputStream(),"GBK"));
        br.readLine();br.readLine();//ȥ��ǰ����������Ϣ
        if((nameString=br.readLine())!=null){
            nameString=nameString.replaceAll("DisplayName    REG_SZ    ", "");    //ȥ��������Ϣ
        }
        

        process = runtime.exec("cmd /c reg query " + string + " /v DisplayVersion");
        br = new BufferedReader(new InputStreamReader(process
                .getInputStream(),"GBK"));
        br.readLine();br.readLine();//ȥ��ǰ����������Ϣ
        if((versionString=br.readLine())!=null){
            versionString=versionString.replaceAll("DisplayVersion    REG_SZ    ", "");    //ȥ��������Ϣ
        }
        
        process = runtime.exec("cmd /c reg query " + string + " /v Publisher");
        br = new BufferedReader(new InputStreamReader(process
                .getInputStream(),"GBK"));
        br.readLine();br.readLine();//ȥ��ǰ����������Ϣ
        if((publisherString=br.readLine())!=null){
            publisherString =publisherString.replaceAll("Publisher    REG_SZ    ", "");    //ȥ��������Ϣ
        }
        
        process = runtime.exec("cmd /c reg query " + string + " /v InstallLocation");//��װ���λ��
        br = new BufferedReader(new InputStreamReader(process
                .getInputStream(),"GBK"));
        br.readLine();br.readLine();//ȥ��ǰ����������Ϣ
        if((uninstallPathString=br.readLine())!=null){
            uninstallPathString=uninstallPathString.replaceAll("InstallLocation    REG_SZ    ", "");    //ȥ��������Ϣ
        }
        
        String[] resultString=new String[4];
        resultString[0]= nameString ;//== null ? null : new String(nameString.getBytes(),"GB-2312");
        resultString[1]= versionString ;//== null ? null : new String(versionString.getBytes(),"GB-2312");
        resultString[2]= publisherString ;//== null ? null : new String(publisherString.getBytes(),"GB-2312");
        resultString[3]= uninstallPathString ;//== null ? null : new String(uninstallPathString.getBytes(),"GB-2312");
        if(resultString[0]==null) resultString=null;    //û�����ֵĲ���ʾ
        return resultString;
    }
    
    //�б�
    private class MyTable{
        private JTable jTable;
        private Object[][] data=new Object[100][4];
        private Object[] colNames= { "�������","�汾��","������","�����װλ��"};
        private int p=-1;
        
        public MyTable(){
            
        }
        
        public void addRow(Object[] data){
            p++;
            if(p>=100) return ;
            this.data[p]=data;
        }
        
        
        public JTable getTable(){
            jTable=new JTable(data,colNames);
            return jTable;
        }
        
    }
    
    public static void main(String[] args) {
        new SystemSoftware();
    }
}
