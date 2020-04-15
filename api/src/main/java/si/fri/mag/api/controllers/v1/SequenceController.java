package si.fri.mag.api.controllers.v1;

import com.google.gson.Gson;
import si.fri.mag.DTO.SequenceDTO;
import si.fri.mag.DTO.input.SequenceNew;
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

        return this.responseOk("", sequenceService.getProjectSequences(projectId));
    }

    @GET
    @Path("{sequenceId}")
    public Response getSequenceMedia(@PathParam("sequenceId") Integer sequenceId) {

        return this.responseOk("", sequenceService.getOneSequenceMedia(sequenceId));
    }

    @POST
    @Path("")
    public Response newProjectSequence(String body) {
        Gson gson = new Gson();
        SequenceNew sequenceNew;
        try {
            sequenceNew = gson.fromJson(body, SequenceNew.class);
        } catch (Exception e) {
            return  this.responseError(500, e.getMessage());
        }

        return this.responseOk("", sequenceService.createNewSequence(sequenceNew));
    }

    @PUT
    @Path("")
    public Response updateSequence(String body) {
        Gson gson = new Gson();
        SequenceDTO sequenceDTO;
        try {
            sequenceDTO = gson.fromJson(body, SequenceDTO.class);
        } catch (Exception e) {
            return  this.responseError(500, e.getMessage());
        }
        return this.responseOk("",  sequenceService.updateSequence(sequenceDTO));
    }

    @DELETE
    @Path("{sequenceId}")
    public Response deleteSequence(@PathParam("sequenceId") Integer sequenceId) {

        boolean isDeleted = sequenceService.deleteSequence(sequenceId);

        if (!isDeleted) {
            this.responseError(500, "Sequence with id: " + sequenceId + " not deleted");
        }

        return this.responseOk("sequence with id: " + sequenceId + " deleted", isDeleted);
    }

}
