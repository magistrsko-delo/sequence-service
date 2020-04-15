package si.fri.mag.api.controllers.v1;

import si.fri.mag.api.controllers.MainController;
import si.fri.mag.services.SequenceService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/v1/sequence")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SequenceController extends MainController {

    @Inject
    private SequenceService sequenceService;

    @GET
    @Path("project/{projectId}")
    public Response getProjectSequences(@PathParam("projectId") Integer projectId) {
        sequenceService.getProjectSequences(projectId);

        return this.responseOk("", projectId);
    }

}
