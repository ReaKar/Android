package StartingMain;

import com.MyHook.MyShutDownHook;
import com.MyHook.Stopper;
import com.SenderThreadsClasses.senderThread;
import com.XmlEl.NmapJobs;
import com.XmlEl.SaInfo;
import com.oneTimeThreads.cons.consumer;
import com.oneTimeThreads.mySharedMem.sharedMemOfPool;
import com.oneTimeThreads.prod.producer;
import com.periodicThreads.periodicThread;
import com.sharedmemforoutput.SharedMemOut;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by giannis on 10/17/15.
 */
public class Main {

    public static void main(String[] args) {
        try {
            String fileName=args[0];

            StopMain stopm=new StopMain();
            Stopper stopp=new Stopper();
            final BlockingQueue<NmapJobs> myqueue = new LinkedBlockingQueue<>();
            List consumerthreads=new LinkedList();
            List periodthreads=new LinkedList();
            Map<Integer,PeriodicThreadWrapper> periodmap=new HashMap<>();
            sharedMemOfPool shar = new sharedMemOfPool();
            SharedMemOut sharout=new SharedMemOut();
            senderThread send=new senderThread(3*1000,0,sharout,stopp);
            Thread sendt=new Thread(send,"sender");

            producer myprod = new producer(shar, 1, stopp,myqueue);
            Thread prodt=new Thread(myprod,"producer");


            MyShutDownHook myhook = new MyShutDownHook(consumerthreads,periodthreads,Thread.currentThread(),stopm,stopp,sendt,prodt);
            Runtime.getRuntime().addShutdownHook(myhook);
            try {

                FileReader fileReader = new FileReader(fileName);

                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String array1[] = bufferedReader.readLine().split("=");
                Integer numofThreads = Integer.parseInt(array1[1]);
                array1 = bufferedReader.readLine().split("=");
                Integer mainperiod=Integer.parseInt(array1[1]);
                array1 = bufferedReader.readLine().split("=");
                Integer registrationtries = Integer.parseInt(array1[1]);
                array1 = bufferedReader.readLine().split("=");
                Integer sleepinterval = Integer.parseInt(array1[1]);
                array1 = bufferedReader.readLine().split("=");
                String devicename=array1[1];
                array1 = bufferedReader.readLine().split("=");
                String interfaceip=array1[1];
                array1 = bufferedReader.readLine().split("=");
                String macaddrr=array1[1];
                array1 = bufferedReader.readLine().split("=");
                String osversion=array1[1];
                array1 = bufferedReader.readLine().split("=");
                String nmapversion=array1[1];
                Integer i = 0;
                String stringToEncrypt=devicename+interfaceip+macaddrr+osversion+nmapversion;
                int hash= stringToEncrypt.hashCode();
                String encryptedString =""+hash;
                SaInfo currentsa=new SaInfo(encryptedString,devicename,interfaceip,macaddrr,osversion,nmapversion,mainperiod);
                Registration regform=new Registration();
                System.out.println("Submiting Registration");
                regform.sendReq(currentsa);
                System.out.println("Submited");
                System.out.println("Awaiting Registration");
                if(regform.checkReg(registrationtries,sleepinterval*1000,currentsa.getHashkey())) {
                    System.out.println("Registered");
                    sendt.start();
                    prodt.start();
                    while (i < numofThreads) {
                        consumer cons = new consumer(shar, i, stopp, sharout);
                        i++;
                        Thread consumert = new Thread(cons, "consumer" + i);
                        consumert.start();
                        consumerthreads.add(consumert);

                    }
                    Integer j = 0;

                    while (stopm.isStopmain()) {
                       List<NmapJobs> list=regform.getJobs(currentsa.getHashkey());
                        System.out.println("Requesting Jobs "+list.size());
                        for(NmapJobs nmap:list) {

                            if(nmap.getNmapjobscol().equals("Stop")){

                                periodmap.get(nmap.getIdnmapjobs()).getMyperiodic().stoptimer();
                                periodmap.get(nmap.getIdnmapjobs()).getMythread().stop();
                                periodmap.get(nmap.getIdnmapjobs()).getMythread().join();

                                //System.out.println("Joined"+periodmap.get(nmap.getIdnmapjobs()).getMythread().getState());
                            }
                            else if(nmap.getNmapjobscol().equals("exit(0)")){
                                System.exit(0);

                            }
                            else
                            {
                                if (nmap.getFlagperiodic().equals("false")) {

                                    myprod.addtomyQueue(new NmapJobs(nmap.getIdnmapjobs(),nmap.getNmapjobscol(),nmap.getFlagperiodic(),nmap.getTimeperiodic(),nmap.getSa_hashkey()));
                                } else {
                                    periodicThread perd = new periodicThread(new NmapJobs(nmap.getIdnmapjobs(),nmap.getNmapjobscol(),nmap.getFlagperiodic(),nmap.getTimeperiodic(),nmap.getSa_hashkey()), sharout,stopp);
                                    Thread pdt = new Thread(perd, "periodic" + j);
                                    pdt.start();
                                    periodthreads.add(pdt);
                                    periodmap.put(nmap.getIdnmapjobs(),new PeriodicThreadWrapper(pdt,perd));
                                    j++;

                                }
                              }
                            }
                        Thread.sleep(mainperiod * 1000);
                            }

                        }
                return;
            } catch (FileNotFoundException ex) {
                System.out.println(
                        "Unable to open file '" +
                                fileName + "'");
                System.out.println(ex);
            } catch (IOException ex) {
                System.out.println(
                        "Error reading file '"
                                + fileName + "'");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
