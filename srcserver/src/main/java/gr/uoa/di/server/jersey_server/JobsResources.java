package gr.uoa.di.server.jersey_server;

import DataSources.DataSourcesImpl.Mystore;
import DataSources.JobsImpl.NmapJobs;
import DataSources.SAImpl.SaInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("jobresource")
public class JobsResources {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "application/json" media type.
     *
     * @return String that will be returned as a application/json response.
     */
    //send a list of jobs to client ->gui
    @POST
    @Path("/sendjobs/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void postIt(@PathParam("param") String key,List<SaInfo> mylist) {

    }
    //get my jobs ->client
    @GET
    @Path("/getjobs/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NmapJobs> getall(@PathParam("param") String key) {
        List<NmapJobs> saJobsList=null;
        Mystore.getStore().getSastatus().put(key,System.currentTimeMillis());
        if(Mystore.getStore().getMaplist().containsKey(key)) {
            saJobsList = Mystore.getStore().getMaplist().get(key);
        }
        else{
            return new LinkedList<>();
        }
        List<NmapJobs> curJobsList=new LinkedList<>();
        for(NmapJobs job:saJobsList){
            curJobsList.add(job);
        }
        Mystore.getStore().getMaplist().get(key).clear();
        return curJobsList;
    }
}
