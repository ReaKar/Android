package com.example.giannis.anaptiksi.Pojo;

public class AndroidJobs implements Cloneable{

    private int idnmapjobs;
    private String nmapjobscol;
    private String flagperiodic;
    private int timeperiodic;
    private String sa_hashkey;

    public AndroidJobs() {} // JAXB needs this

    public AndroidJobs(int idnmapjobs, String nmapjobscol, String flagperiodic,
                    int timeperiodic, String sa_hashkey) {

        this.idnmapjobs = idnmapjobs;
        this.nmapjobscol = nmapjobscol;
        this.flagperiodic = flagperiodic;
        this.timeperiodic = timeperiodic;
        this.sa_hashkey = sa_hashkey;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public int getIdnmapjobs() {
        return idnmapjobs;
    }

    public void setIdnmapjobs(int idnmapjobs) {
        this.idnmapjobs = idnmapjobs;
    }

    public String getNmapjobscol() {
        return nmapjobscol;
    }

    public void setNmapjobscol(String nmapjobscol) {
        this.nmapjobscol = nmapjobscol;
    }

    public String getFlagperiodic() {
        return flagperiodic;
    }

    public void setFlagperiodic(String flagperiodic) {
        this.flagperiodic = flagperiodic;
    }

    public int getTimeperiodic() {
        return timeperiodic;
    }

    public void setTimeperiodic(int timeperiodic) {
        this.timeperiodic = timeperiodic;
    }

    public String getSa_hashkey() {
        return sa_hashkey;
    }

    public void setSa_hashkey(String sa_hashkey) {
        this.sa_hashkey = sa_hashkey;
    }


    @Override
    public String toString(){
        return "Name: " + this.idnmapjobs + ", hash: " + this.nmapjobscol;
    }



}