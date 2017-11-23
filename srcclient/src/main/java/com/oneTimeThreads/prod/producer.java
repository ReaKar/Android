package com.oneTimeThreads.prod;

import com.MyHook.Stopper;
import com.XmlEl.NmapJobs;
import com.oneTimeThreads.mySharedMem.sharedMemOfPool;

import java.util.concurrent.BlockingQueue;

/**
 * Created by giannis on 10/20/15.
 */

public class producer implements Runnable{
    //h lista pou stelnei h main ston producer
    final BlockingQueue<NmapJobs> myqueue;
    sharedMemOfPool shared;
    Integer id;
    Stopper stopp;

    public producer(sharedMemOfPool shared, Integer id, Stopper stopp, BlockingQueue<NmapJobs> myqueue) {
        this.shared=shared;
        this.id=id;
        this.stopp=stopp;
        this.myqueue=myqueue;

    }
    /**
     *Gets from the list shared with main and put in list shared with consumers
     */
    public void run(){
        try {
            while (this.stopp.isChecker()) {
                this.shared.put(this.myqueue.take());
            }
            System.out.println("feygw");
            this.shared.put(new NmapJobs(0,"exit","true",10,"me"));
            System.out.println("den feygw");
            return;
        }catch (Exception e){
            new RuntimeException("Interrupted",e);
        }
    }
    /**
     *Main uses this to put items in producer
     */
    public void addtomyQueue(NmapJobs job){
        try {
            NmapJobs newj=(NmapJobs) ((NmapJobs)job).clone();
            this.myqueue.add(job);
        }
        catch (CloneNotSupportedException e){
            throw new RuntimeException("Clone not support");
        }

    }
}

