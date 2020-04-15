package si.fri.mag.api.controllers.v1;

import si.fri.mag.api.controllers.MainController;
import si.fri.mag.services.SequenceMediaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/v1/sequence")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SequenceMediaController extends MainController {

    @Inject
    SequenceMediaService sequenceMediaService;

    @POST
    @Path("{sequenceId}/media/{mediaId}")
    public Response addMediaToSequence(@PathParam("sequenceId") Integer sequenceId, @PathParam("mediaId") Integer mediaId) {

        boolean isAdded = sequenceMediaService.addMediaToSequence(sequenceId, mediaId);

        if (!isAdded) {
            this.responseError(500, "Error when adding media: " + mediaId + " to sequence: " + sequenceId);
        }

        return this.responseOk("Media: " + mediaId + " added to sequence: " + sequenceId, isAdded);
    }

    @DELETE
    @Path("{sequenceId}/media/{mediaId}")
    public Response deleteMediaInSequence(@PathParam("sequenceId") Integer sequenceId, @PathParam("mediaId") Integer mediaId) {

        boolean isDeleted = sequenceMediaService.deleteMediaInSequence(sequenceId, mediaId);

        if (!isDeleted) {
            this.responseError(500, "Error when deleting media: "  + mediaId + " from sequence: " +sequenceId);
        }

        return this.responseOk("Media: " + mediaId + " deleted from sequence: " + sequenceId, isDeleted);
    }
}
