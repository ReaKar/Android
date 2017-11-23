package StartingMain;

import com.XmlEl.NmapJobs;
import com.XmlEl.SaInfo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by giannis on 12/17/15.
 */
public class Registration {
    public boolean checkReg(int registrationtries, int sleepinterval,String saname ){

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        WebResource webResource = client
                .resource("http://localhost:8081/myapp/register/getsaved/"+saname);

        int i=0;
        while(i<registrationtries){
            String registration = webResource.accept("application/json").get(String.class);

            if(registration.equals("registered")){
                return true;
            }
            else{
                try {
                    i++;
                    Thread.sleep(sleepinterval);
                }catch (InterruptedException e){
                    throw new RuntimeException("error sleep");
                }
            }
        }
        return false;
    }
    public List<NmapJobs> getJobs(String sa){
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getClasses().add(JacksonJaxbJsonProvider.class);
          clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
           Client client = Client.create(clientConfig);


        WebResource webResource = client
                .resource("http://localhost:8081/myapp/jobresource/getjobs/"+sa);
        List<NmapJobs> list = webResource.accept("application/json").get(new GenericType<List<NmapJobs>>(){});
        return list;
    }
    public void sendReq(SaInfo sa){
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        ObjectMapper mapper=new ObjectMapper();
        WebResource webResource2 = client.resource("http://localhost:8081/myapp/register/regreq");
        try {
            webResource2.type("application/json").post(ClientResponse.class, mapper.writeValueAsString(sa));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
