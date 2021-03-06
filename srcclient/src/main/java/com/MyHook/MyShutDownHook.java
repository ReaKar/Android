package com.MyHook;

import StartingMain.StopMain;

import java.util.List;

/**
 * Created by giannis on 11/4/15.
 */
public class MyShutDownHook extends Thread{
    boolean checker;
    List consumerthreads;
    List periodicthreads;
    Thread prod;
    Thread send;
    Thread mainthr;
    StopMain stopmain;
    Stopper stopp;
    public MyShutDownHook(List consumerthreads, List periodicthreads, Thread mainthr, StopMain stopmain,Stopper stopp,Thread send,Thread prod)
    {
        this.stopp=stopp;
        this.stopmain=stopmain;
        this.consumerthreads=consumerthreads;
        this.periodicthreads=periodicthreads;
        this.mainthr=mainthr;
        this.checker=true;
        this.send=send;
        this.prod=prod;
    }

    /**
     *Run Method of Shutdown Hook-joins all threads
     */
    public void run(){
        try {
        this.stopp.setChecker(false);
        this.stopmain.setStopmain(false);

            System.out.println("Waiting for sender exit code");
            this.send.join();
            System.out.println("Waiting for producer exit code");
            this.prod.interrupt();
            this.prod.join();


            Thread ex;
            System.out.println("Waiting for consumer exit code");
            for(int i=0;i<this.consumerthreads.size();i++){
                ex =(Thread) this.consumerthreads.get(i);
                while (ex.getState().toString().equals("WAITING"))
                {
                    ex.interrupt();
                }
               ex.join(3000);
            }
            System.out.println("Waiting for periodic exit code");
            for(int i=0;i<this.periodicthreads.size();i++){
                ex =(Thread) this.periodicthreads.get(i);
                //ex.interrupt();
                ex.join();
            }


            System.out.println("Waiting for main thread exit code");
            this.mainthr.interrupt();
            this.mainthr.join();
            System.out.println("Exiting");
        }catch (InterruptedException e)
        {

            e.printStackTrace();
        }

    }

}
