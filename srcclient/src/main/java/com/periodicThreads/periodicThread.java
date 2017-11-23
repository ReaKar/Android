package com.periodicThreads;

import com.MyHook.Stopper;
import com.XmlEl.NmapJobs;
import com.sharedmemforoutput.SharedMemOut;

import java.util.Timer;

/**
 * Created by giannis on 11/4/15.
 */
public class periodicThread implements Runnable{
    private SharedMemOut shm;

   private Stopper stopp;
    private boolean jobinter;
    NmapJobs nmap;
    ScheduledClass st;
    public periodicThread(NmapJobs nmap, SharedMemOut shm, Stopper stopp){
        this.nmap=nmap;
        this.shm=shm;
        this.stopp=stopp;
        this.nmap=nmap;
        this.jobinter=true;
    }

    public void setJobinter(boolean jobinter) {
        this.jobinter = jobinter;
    }

    /**
     *Create an instance of Timer Object
     *<br>Create an instance of SheduledTask class</br>
     *Run The schuduled class periodically
     */

    public void run(){
        Timer time = new Timer(); // Instantiate Timer Object

        this.st = new ScheduledClass(this.nmap,this.shm,time,this.stopp,this.jobinter); // Instantiate SheduledTask class
        time.schedule(st, 0, this.nmap.getTimeperiodic()); // Create Repetitively task for every 1 secs
    }
    public void stoptimer(){
        this.st.setJobinter(false);
    }

}
