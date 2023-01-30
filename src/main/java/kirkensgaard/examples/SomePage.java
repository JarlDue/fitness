package kirkensgaard.examples;

import io.quarkus.qute.Template;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import static java.util.Objects.requireNonNull;

@Path("/some-page")
public class SomePage {

    private final Template fitnessJarl;
    private final Template fitness;


    public SomePage(Template fitness, Template fitnessJarl) {
        this.fitness = requireNonNull(fitness, "page is required");
        this.fitnessJarl = requireNonNull(fitnessJarl, "page is required");

    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String get(@QueryParam("name") String name) {
        if(name == null){
            name = "";
        }
        if(name.equals("Jarl")){
            return fitnessJarl.data("submit-url", "http://localhost:8085/some-page").render();
        }
        return fitness.data("submit-url", "http://localhost:8085/some-page").render();
    }

    @POST
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void msg(@FormParam("name") String name) {
        System.out.println(name);
    }

}
