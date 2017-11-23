package DataSources.DataSourcesImpl;

import DataSources.AndroidClientImpl.AndroidClient;
import DataSources.JobsImpl.NmapJobs;
import DataSources.JobsImpl.NmapJobsDaoImpl;
import DataSources.SAImpl.SaInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mystore {
	private static Map<String, SaInfo> regstore;
	private static Map<String, SaInfo> regsaved;
	private static Map<String,AndroidClient> clientstore;
	private static Map<String,List<NmapJobs>> maplist;
	private static Map<String,List<NmapJobs>> historymaplist;
	private static Map<String,Long> sastatus;
	private static int lastidnmap;
	private static Mystore instance = null;
	
	private Mystore() {
		Mystore.clientstore=new HashMap<>();
		Mystore.regstore = new HashMap<>();
		Mystore.regsaved=new HashMap<>();
		Mystore.maplist=new HashMap<>();
		Mystore.sastatus=new HashMap<>();
		NmapJobsDaoImpl nmdap=new NmapJobsDaoImpl();
		Mystore.lastidnmap=nmdap.returnLast();
		Mystore.historymaplist=new HashMap<>();
	}
	
	public static Mystore getStore() {
		if(instance==null) {
			instance = new Mystore();
		}
		return instance;
	}
	public  Map<String, SaInfo> getRegister(){
		return Mystore.regstore;
	}
	public Map<String, SaInfo> getRegSaved(){
		return Mystore.regsaved;
	}
	public Map<String, List<NmapJobs>> getMaplist(){
		return Mystore.maplist;
	}

	public static Map<String, AndroidClient> getClientstore() {
		return clientstore;
	}


	public Map<String, List<NmapJobs>> getHistorymaplist(){
		return Mystore.historymaplist;
	}
	public Map<String,Long> getSastatus(){
		return Mystore.sastatus;
	}
	public int getLastidNmap(){
		int i=lastidnmap;
		lastidnmap++;
		return i;
	}
	public void stopMystore(){
		this.instance=null;
	}
	
}
