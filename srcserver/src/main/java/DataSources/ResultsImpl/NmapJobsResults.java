package DataSources.ResultsImpl;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NmapJobsResults {

	private String scanning;
	private String verboseLevel;
	private String debuggingLevel;
	private String trace;
	private String status;
	private String address;
	private String hostnames;
	private String ports;
	private String os;
	private String uptime;
	private String tcpsequence;
	private String ipidsequence;
	private String tcptssequence;
	private String sa_hashkey;
	private int nmapjobs_idnmapjobs;

	public NmapJobsResults() {} // JAXB needs this

	public NmapJobsResults(String scanning,
						   String verboseLevel, String debuggingLevel, String trace,
						   String status, String address, String hostnames, String ports,
						   String os, String uptime, String tcpsequence, String ipidsequence,
						   String tcptssequence, int nmapjobs_idnmapjobs, String sa_hashkey) {

		this.scanning = scanning;
		this.verboseLevel = verboseLevel;
		this.debuggingLevel = debuggingLevel;
		this.trace = trace;
		this.status = status;
		this.address = address;
		this.hostnames = hostnames;
		this.ports = ports;
		this.os = os;
		this.uptime = uptime;
		this.tcpsequence = tcpsequence;
		this.ipidsequence = ipidsequence;
		this.tcptssequence = tcptssequence;
		this.nmapjobs_idnmapjobs = nmapjobs_idnmapjobs;
		this.sa_hashkey=sa_hashkey;
	}

	public String getSa_hashkey() {
		return this.sa_hashkey;
	}


	public void setSa_hashkey(String hashkey) {
		this.sa_hashkey = hashkey;
	}


	public String getScanning() {
		return scanning;
	}


	public void setScanning(String scanning) {
		this.scanning = scanning;
	}


	public String getVerboseLevel() {
		return verboseLevel;
	}


	public void setVerboseLevel(String verboseLevel) {
		this.verboseLevel = verboseLevel;
	}


	public String getDebuggingLevel() {
		return debuggingLevel;
	}


	public void setDebuggingLevel(String debuggingLevel) {
		this.debuggingLevel = debuggingLevel;
	}


	public String getTrace() {
		return trace;
	}


	public void setTrace(String trace) {
		this.trace = trace;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getHostnames() {
		return hostnames;
	}


	public void setHostnames(String hostnames) {
		this.hostnames = hostnames;
	}


	public String getPorts() {
		return ports;
	}


	public void setPorts(String ports) {
		this.ports = ports;
	}


	public String getOs() {
		return os;
	}


	public void setOs(String os) {
		this.os = os;
	}


	public String getUptime() {
		return uptime;
	}


	public void setUptime(String uptime) {
		this.uptime = uptime;
	}


	public String getTcpsequence() {
		return tcpsequence;
	}


	public void setTcpsequence(String tcpsequence) {
		this.tcpsequence = tcpsequence;
	}


	public String getIpidsequence() {
		return ipidsequence;
	}


	public void setIpidsequence(String ipidsequence) {
		this.ipidsequence = ipidsequence;
	}


	public String getTcptssequence() {
		return tcptssequence;
	}


	public void setTcptssequence(String tcptssequence) {
		this.tcptssequence = tcptssequence;
	}





	public int getNmapjobs_idnmapjobs() {
		return nmapjobs_idnmapjobs;
	}


	public void setNmapjobs_idnmapjobs(int nmapjobs_idnmapjobs) {
		this.nmapjobs_idnmapjobs = nmapjobs_idnmapjobs;
	}
	@Override
	public String toString(){
		return "Nmap job results "+this.address+" "+this.debuggingLevel+" "+this.sa_hashkey;
	}
}
