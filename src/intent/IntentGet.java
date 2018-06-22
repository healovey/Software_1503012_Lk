package intent;


public class IntentGet {
	
	public String origin;
	public String originname;
	public String name;
	public String intent;
	public float score;
	public String type;
	
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
		getSoftwareName();
		System.out.println("\tintent: " + intent + "\tscore: " + score + "\tnametype: "+ this.type +"\tname: " + name);
	}
	
	public String getSoftwareName() {
		if(intent.equals("设备控制.打开应用")) {
			return getSoftwareName(true);
		}else {
			this.type = "intenterror";
			return "";
		}
	}
	
	public String getSoftwareName(boolean doit) {
		if(this.name != null) return this.name;
		String name;
		if(this.originname == "") {
			name = origin;
			doit = true;
		}else {
			name = this.originname;
		}
			
		if(doit) {
			String[] r = name.split("打开");
			if (r.length != 1) {
				name = r[r.length - 1];
				r = name.split("好吗|。");
				name = r[0];
			}
		}
		if(set.controlnames.get(name) != null) {
			name = set.controlnames.get(name);
			type = "control";
			this.name = name;
			return name;
		}

		name = set.aliasname.getOrDefault(name, name);

		if(set.useexename) {
			float[] nameper = new float[set.exenames.length];
			for(int i = 0; i < set.exenames.length; i++) {
				if( set.exenames[i].indexOf(name) != -1 ) {
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
				type = "list";
			}else {
				loging.log("unknow name \"" + name + "\"");
				name = "";
				type = "null";
			}
		}
		this.name = name;
		return name;
	}
}
