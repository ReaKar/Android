package DataSources.SAImpl;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SaInfo {

	private String hashkey;
	private String deviceName;
	private String interfaceIP;
	private String interfaceMacAddr;
	private String OsVersion;
	private String NmapVersion;
	private int mainperiod;

	public SaInfo() {} // JAXB needs this

	public SaInfo(String hashkey ,String deviceName,String interfaceIP,String interfaceMacAddr,String OsVersion,String NmapVersion,int mainperiod)
	{
		this.hashkey=hashkey;
		this.deviceName=deviceName;
		this.interfaceIP=interfaceIP;
		this.interfaceMacAddr=interfaceMacAddr;
		this.OsVersion=OsVersion;
		this.NmapVersion=NmapVersion;
		this.mainperiod=mainperiod;
	}

	public int getMainperiod() {
		return mainperiod;
	}

	public void setMainperiod(int mainperiod) {
		this.mainperiod = mainperiod;
	}

	public String getHashkey() {
		return hashkey;
	}


	public void setHashkey(String hashkey) {
		this.hashkey = hashkey;
	}


	public String getDeviceName() {
		return deviceName;
	}


	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}


	public String getInterfaceIP() {
		return interfaceIP;
	}


	public void setInterfaceIP(String interfaceIP) {
		this.interfaceIP = interfaceIP;
	}


	public String getInterfaceMacAddr() {
		return interfaceMacAddr;
	}


	public void setInterfaceMacAddr(String interfaceMacAddr) {
		this.interfaceMacAddr = interfaceMacAddr;
	}


	public String getOsVersion() {
		return OsVersion;
	}


	public void setOsVersion(String osVersion) {
		this.OsVersion = osVersion;
	}


	public String getNmapVersion() {
		return NmapVersion;
	}


	public void setNmapVersion(String nmapVersion) {
		this.NmapVersion = nmapVersion;
	}

	@Override
	public String toString(){
		return "Name: " + this.deviceName + ", hash: " + this.hashkey;
	}
}	

