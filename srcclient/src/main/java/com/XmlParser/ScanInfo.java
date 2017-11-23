package com.XmlParser;

import com.XmlEl.NmapJobsResults;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by giannis on 11/7/15.
 */
public class ScanInfo {
    Map newverlist;
    Map newdebugginglist;
    Map newaddresslist;
    Map newstatuslist;
    Map newtracelist;
    Map newportlist;
    Map newextraportlist;
    Map newhostnames;
    Map newosclass;
    Map newosmatch;
    Map newuptimelist;
    Map newtcpseqlist;
    Map newipidlist;
    Map newtcptslist;
    String hashkey;
    int id;
    /**
     *Scaninfo constructor-Gets the Mapings of xmlfiles
     */
    public ScanInfo(Map newverlist , Map newdebugginglist,Map newaddresslist,Map newstatuslist,Map newtracelist,
                    Map newportlist, Map newextraportlist,Map newhostnames,Map newosclass,Map newosmatch,
                    Map newuptimelist,Map newtcpseqlist,Map newipidlist,Map newtcptslist,String hashkey,int id)
    {
        this.newverlist=newverlist;
        this.newdebugginglist=newdebugginglist;
        this.newaddresslist=newaddresslist;
        this.newstatuslist=newstatuslist;
        this.newtracelist=newtracelist;
        this.newportlist=newportlist;
        this.newextraportlist=newextraportlist;
        this.newhostnames=newhostnames;
        this.newosclass=newosclass;
        this.newosmatch=newosmatch;
        this.newuptimelist=newuptimelist;
        this.newtcpseqlist=newtcpseqlist;
        this.newipidlist=newipidlist;
        this.newtcptslist=newtcptslist;
        this.hashkey=hashkey;
        this.id=id;

    }
    /**
     *Calls printMap to Print xmls infos one by one
     */
    public NmapJobsResults printMyInfo(){
         String scanning="success";
         String verboseLevel=printMap("verbose",this.newverlist);
         String debuggingLevel=printMap("debug",this.newdebugginglist);
         String trace=printMap("trace",this.newtracelist);;
         String status= printMap("status",this.newstatuslist);
         String address=printMap("address",this.newaddresslist);
         String hostnames=printMap("hostnames",this.newhostnames);
         String ports= printMap("port",this.newportlist)+printMap("extraport",this.newextraportlist);
         String os=printMap("osclass",this.newosclass)+ printMap("osmatch",this.newosmatch);
         String uptime= printMap("uptime",this.newuptimelist);
         String tcpsequence=printMap("tcpsequence",this.newtcpseqlist);
         String ipidsequence=printMap("ipidsequence",this.newipidlist);
         String tcptssequence=printMap("tcptssequence",this.newtcptslist);
         String sa_hashkey=this.hashkey;
         int nmapjobs_idnmapjobs=this.id;

         return new NmapJobsResults(scanning,verboseLevel,debuggingLevel,trace,status,address,hostnames,ports,os,uptime,tcpsequence,ipidsequence,
                 tcptssequence,nmapjobs_idnmapjobs,sa_hashkey);
    }

    /**
     *Print each Map individually
     */
    public String printMap(String name, Map curmap){
        String finalstring="";
        Iterator<Map.Entry<String,Map<String,Map<String,String>>>> iter0 = curmap.entrySet().iterator();
        while(iter0.hasNext()){
            String key = iter0.next().getKey();
            Map<String,Map<String,String>> newmap=(Map)curmap.get(key);
            Iterator<Map.Entry<String,Map<String,String>>> iter1=newmap.entrySet().iterator();
            while(iter1.hasNext()){

                String key1 = iter1.next().getKey();

                Map<String,String> newmap1=(Map)newmap.get(key1);
                Iterator<Map.Entry<String,String>> iter2=newmap1.entrySet().iterator();
                while(iter2.hasNext()){
                    String key2 = iter2.next().getKey();
                    String value=(String) newmap1.get(key2);
                    finalstring+=value;

                }
            }
        }
        return finalstring;
    }
}
