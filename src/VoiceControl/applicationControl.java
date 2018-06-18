package VoiceControl;

public class applicationControl {
	private fileData data = new fileData();
	private OpenFile execFile = new OpenFile();
	private String fileName;
	private String saveFileName = "temp.txt";
	
	public applicationControl(String saveFileName){
		FileSave save = new FileSave(saveFileName);
	}
	
	public applicationControl(){
		FileSave save = new FileSave(saveFileName);
	}
	
	public int fileOpen(String fileName){
		for(int i = 0;i < data.getSize();i++){
			if(fileName.matches(data.getFileName().get(i).toString())){
				//System.out.println(data.getFileAddress().get(i).toString());
				execFile.openWindowsExe(data.getFileAddress().get(i).toString());
				System.out.println("ok");
				return 1;
			}
		}
		return 0;
	}
	
	public int fileOpen(){
		for(int i = 0;i < data.getSize();i++){
			if(fileName.matches(data.getFileName().get(i).toString())){
				//System.out.println(data.getFileAddress().get(i).toString());
				execFile.openWindowsExe(data.getFileAddress().get(i).toString());
				System.out.println("ok");
				return 1;
			}
		}
		return 0;
	}
	
	public void setFileName(String filename){
		this.fileName = filename;
	}
	
	public static void main(String argc[]){
		applicationControl app = new applicationControl("temp");
		if(app.fileOpen("TIM") == 1){
			System.out.println("open successful");
		}
	}
}
