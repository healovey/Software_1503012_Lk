package intent;


public class IntentGet {
	
	public String origin;
	public String name;
	public String intent;
	public float score;
	
	private IntentDefaultSet set;
	
	public IntentGet(IntentDefaultSet set) {
		this.set = set;
	}
	public IntentGet() {
		this.set = new IntentDefaultSet();
	}
	
	public boolean ifname() {
		if(name=="")
			return false;
		else
			return true;
	}
	
	public void out() {
		System.out.println("strs: "+ origin);
		System.out.println("\tintent: " + intent + "\tscore: " + score + "\tname: " + getSoftwareName(true));
	}
	
	public String getSoftwareName() {
		return getSoftwareName(false);
	}
	public String getSoftwareName(boolean doit) {
		String name;
		if(this.name == "") {
			name = origin;
			doit = true;
		}else {
			name = this.name;
		}
			
		if(doit) {
			String[] r = name.split("打开");
			if (r.length != 1) {
				name = r[r.length - 1];
				r = name.split("好吗|。");
				name = r[0];
			}
		}
		name = set.aliasname.getOrDefault(name, name);
		if(set.useexename) {
			float[] nameper = new float[set.exenames.length];
			for(int i = 0; i < set.exenames.length; i++) {
				if(set.exenames[i].matches(name)) {
					nameper[i] = (float)name.length() / set.exenames[i].length();
				}else {
					nameper[i] = 0;
				}
			}
			int maxid = -1;
			float maxnum = -1;
			for(int i = 0; i < nameper.length; i++) {
				if(nameper[i] > maxnum) {
					maxnum = nameper[i];
					maxid = i;
				}
			}
			if(maxnum > 0) {
				name = set.exenames[maxid];
			}else {
				name = "";
			}
		}
		
		return name;
	}
}
