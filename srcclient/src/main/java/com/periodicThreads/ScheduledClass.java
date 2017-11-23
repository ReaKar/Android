package com.periodicThreads;

import com.MyHook.Stopper;
import com.XmlEl.NmapJobs;
import com.XmlParser.MyXmlParser;
import com.sharedmemforoutput.SharedMemOut;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by giannis on 11/4/15.
 */
public class ScheduledClass extends TimerTask{
    NmapJobs job;
    MyXmlParser xmlp;
    Stopper stopp;
    Timer time;
    boolean jobinter;
    public ScheduledClass(NmapJobs job, SharedMemOut shm, Timer time, Stopper stopp, boolean jobinter){
        this.xmlp=new MyXmlParser(shm);
        this.job=job;
        this.time=time;
        this.stopp=stopp;
        this.jobinter=jobinter;
    }
    /**
     *Runs repeatitevely each job for periodic threads
     */
     public void run(){
          try {
              if (!this.stopp.isChecker()) {
                  this.time.cancel();
                  this.time.purge();
              }
              else if(!this.jobinter){
                  System.out.println("kati trexei");
                  this.time.cancel();
                  this.time.purge();
              }
              else {
                  String line;
                  Process p;

                  if (this.job.getNmapjobscol().contains("-oX - ")) {
                      p = Runtime.getRuntime().exec("nmap " + this.job.getNmapjobscol());
                  } else {
                      p = Runtime.getRuntime().exec("nmap -oX - " + this.job.getNmapjobscol());
                  }
                  BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                  String outputstring = "";
                  while ((line = input.readLine()) != null) {
                      outputstring += line;
                  }
                  if (outputstring.contains("</nmaprun>")) {
                      this.xmlp.parseXML(job,outputstring);
                  }
                  input.close();

              }
          }
        catch (Exception e){
            e.printStackTrace();
        }
     }

    public void setJobinter(boolean jobinter) {
        this.jobinter = jobinter;
    }
}
