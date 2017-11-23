package gr.uoa.di.server.jersey_server;

import DataSources.DataSourcesImpl.Mystore;
import DataSources.SAImpl.SaInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


/**
 * Root resource (exposed at "music" path)
 */
@Path("register")
public class RegisterResources {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "application/json" media type.
     *
     * @return String that will be returned as a application/json response.
     */
    //request registration ->from client
	@POST
    @Path("/regreq")
	@Consumes("application/json")
    public void postIt(SaInfo sa) {

	  Mystore.getStore().getRegister().put(sa.getHashkey(),sa);
    }
    //get more information about sa ->gui
    @GET
    @Path("/getsa/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt(@PathParam("param") String key) {
    	SaInfo sa;
    	  if(Mystore.getStore().getRegister().containsKey(key))
    		  sa=Mystore.getStore().getRegister().get(key);
    	  else
    		  sa=new SaInfo();
		return Response.status(200).entity(sa).build();
    }
    //check registation successful ->from client
    @GET
    @Path("/getsaved/{param}")
    @Produces("application/json")
    public String getSaved(@PathParam("param") String key) {

        if(Mystore.getStore().getRegSaved().containsKey(key))
           return "registered";
        else
            return "not-registered";
    }
}
