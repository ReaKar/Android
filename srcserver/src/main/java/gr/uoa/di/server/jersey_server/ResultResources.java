package gr.uoa.di.server.jersey_server;

import DataSources.ResultsImpl.NmapJobsResults;
import DataSources.ResultsImpl.NmapJobsResultsDaoImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("result")
public class ResultResources {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "application/json" media type.
     *
     * @return String that will be returned as a application/json response.
     */
    @POST
    @Path("/sendresults")
    @Consumes(MediaType.APPLICATION_JSON)
    public void postIt(List<NmapJobsResults> mylist) {
        for(NmapJobsResults nmres:mylist) {

            new NmapJobsResultsDaoImpl().insert(nmres);
        }
    }

}
