package com.example.giannis.anaptiksi.Pojo;

public class AndroidResults {

	private String verboseLevel;
	private String debuggingLevel;
	private String status;
	private String address;
	private String hostnames;
	private String ports;
	private String sa_hashkey;
	private int nmapjobs_idnmapjobs;

	public AndroidResults() {} // JAXB needs this

	public AndroidResults(
						  String verboseLevel, String debuggingLevel,
						  String status, String address, String hostnames, String ports,
						   int nmapjobs_idnmapjobs, String sa_hashkey) {


		this.verboseLevel = verboseLevel;
		this.debuggingLevel = debuggingLevel;

		this.status = status;
		this.address = address;
		this.hostnames = hostnames;
		this.ports = ports;

		this.nmapjobs_idnmapjobs = nmapjobs_idnmapjobs;
		this.sa_hashkey=sa_hashkey;
	}

	public String getSa_hashkey() {
		return this.sa_hashkey;
	}


	public void setSa_hashkey(String hashkey) {
		this.sa_hashkey = hashkey;
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






	public int getNmapjobs_idnmapjobs() {
		return nmapjobs_idnmapjobs;
	}


	public void setNmapjobs_idnmapjobs(int nmapjobs_idnmapjobs) {
		this.nmapjobs_idnmapjobs = nmapjobs_idnmapjobs;
	}
	@Override
	public String toString(){
		return ""+this.address+" "+this.debuggingLevel+" "+this.ports+" "+this.hostnames+" "+this.status;
	}
}
