package com.oneTimeThreads.mySharedMem;

import com.XmlEl.NmapJobs;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by giannis on 10/25/15.
 */
public class sharedMemOfPool {
   List<NmapJobs> myStack=new LinkedList<>();
    boolean valueSet=true;
    /**
     * consumer get from shared mem
     */
    public synchronized NmapJobs get(){

           NmapJobs curJob=null;

                while (this.myStack.isEmpty()) {
                    try {
                        System.out.println("Stamathsa");
                        wait();
                    } catch (InterruptedException e) {
                        new RuntimeException("Interupt",e);
                        break;
                    }
                }

                if(!this.myStack.isEmpty()) {
                        curJob =this.myStack.get(0);
                        this.myStack.remove(0);
                        System.out.println("Pira" + curJob.getNmapjobscol());
                }else {
                    return new NmapJobs(0,"","",0,"");
                }

            return curJob;


    }
    /**
     * producer put in shared mem
     */
    public synchronized void put(NmapJobs nmapJobs) {
            if (!nmapJobs.getNmapjobscol().equals("exit")) {
                try {
                    this.myStack.add((NmapJobs) nmapJobs.clone());
                    System.out.println("noty");
                    notify();
                }
                catch (CloneNotSupportedException e){
                    throw new RuntimeException("Clone not supported");
                }
            }
        else
            {
                System.out.println("I will notify");
                notifyAll();
            }

        return;

    }
}
