package com.sharedmemforoutput;

import com.XmlEl.NmapJobsResults;
import com.XmlParser.ScanInfo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class SharedMemOut {
    BlockingQueue <ScanInfo> myStack=new LinkedBlockingQueue<ScanInfo>();
    List<NmapJobsResults> sendlist=new LinkedList<>();
    /**
     *sender threads gets all elements currently in the list and prints them
     */
    public synchronized void get(){


            while (!myStack.isEmpty()) {

                ScanInfo newinfo = this.myStack.remove();
                newinfo.printMyInfo().getPorts();
                this.sendlist.add(newinfo.printMyInfo());
            }
            if(!this.sendlist.isEmpty()){
            postResultServer(this.sendlist);
            }

            this.myStack=new LinkedBlockingQueue<>();
            this.sendlist=new LinkedList<>();
            return;

    }
    /**
     *periodic and one_time jobs puts the elements in the list that sender_thread contacts
     */
    public void postResultServer(List<NmapJobsResults> mylist){

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        ObjectMapper mapper=new ObjectMapper();

        WebResource webResource = client.resource("http://localhost:8081/myapp/result/sendresults");
        try {
            webResource.type("application/json").post(ClientResponse.class, mapper.writeValueAsString(mylist));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public synchronized void put(ScanInfo scan){
        this.myStack.add(scan);
    }
}
