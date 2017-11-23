package gr.uoa.di.server.jersey_server;

import DataSources.AndroidClientImpl.AndroidClient;
import DataSources.AndroidClientImpl.AndroidClientDao;
import DataSources.DataSourcesImpl.DaoController;
import DataSources.DataSourcesImpl.Mystore;
import DataSources.JobsImpl.AndroidJobs;
import DataSources.JobsImpl.NmapJobs;
import DataSources.JobsImpl.NmapJobsDaoImpl;
import DataSources.ResultsImpl.AndroidResults;
import DataSources.ResultsImpl.NmapJobsResults;
import DataSources.SAImpl.SaInfo;
import DataSources.SAImpl.SaState;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by giannis on 1/24/16.
 */
@Path("android")
public class AndroidResources {
    //get all clients -> from gui
    @GET
    @Path("/getallsa")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SaState> getall() {
        return new DaoController().getSaState();
    }
    @GET
    @Path("/gethistoryjobs/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NmapJobs> getallrunningJobs(@PathParam("param")String saname) {
        return Mystore.getStore().getHistorymaplist().get(saname);
    }
    @POST
    @Path("/sendjobs")
    @Consumes(MediaType.APPLICATION_JSON)
    public void postIt(AndroidJobs job) {
        System.out.println(job);
        if((!job.getNmapjobscol().equals("Stop"))&&(!job.getNmapjobscol().equals("exit(0)"))) {
            NmapJobs nmj=new NmapJobs(Mystore.getStore().getLastidNmap(),job.getNmapjobscol(),job.getFlagperiodic(),job.getTimeperiodic(),job.getSa_hashkey());
            String name=job.getSa_hashkey();
            if (Mystore.getStore().getMaplist().containsKey(name)) {
                Mystore.getStore().getMaplist().get(name).add(nmj);

                Mystore.getStore().getHistorymaplist().get(name).add(nmj);

                new NmapJobsDaoImpl().insert(nmj);

            } else {
                List<NmapJobs> mylist = new LinkedList<>();
                mylist.add(nmj);
                Mystore.getStore().getMaplist().put(name, mylist);
                List<NmapJobs> mylist1 = new LinkedList<>();
                mylist1.add(nmj);

                Mystore.getStore().getHistorymaplist().put(name, mylist1);

                new NmapJobsDaoImpl().insert(nmj);

            }
        }else{
            Mystore.getStore().getMaplist().get(job.getSa_hashkey()).add(new NmapJobs(
                    job.getIdnmapjobs(),job.getNmapjobscol(),job.getFlagperiodic(),job.getTimeperiodic(),job.getSa_hashkey()
            ));
        }

        return;
    }

    @GET
    @Path("/getsares/{param}/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AndroidResults> getresultsSa(@PathParam("param")String saname,@PathParam("number")String num) {

        return new DaoController().androidSaResults(saname,Integer.parseInt(num));
    }
    @GET
    @Path("/getres/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AndroidResults> gettotalRes(@PathParam("number")int num) {

        return new DaoController().androidTotalres(num);
    }

    @GET
    @Path("/getalljobs")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NmapJobs> getalljobs() {
        return new DaoController().getalljobs();
    }


    //register more than one clients
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public void postIt(AndroidClient client) {
       Mystore.getStore().getClientstore().put(client.getUsername(),client);
    }

    @GET
    @Path("/checkregister/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String check(@PathParam("param") String client) {
        ResultSet set=new AndroidClientDao().select(client);
        try {
            return set.getString(2);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return "";
    }

}
