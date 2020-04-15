package si.fri.mag.api.controllers;

import si.fri.mag.DTO.responses.generic.ResponseErrorDTO;
import si.fri.mag.DTO.responses.generic.ResponseOkDTO;

import javax.ws.rs.core.Response;

public class MainController {
    public Response responseOk(String message, Object data) {
        return Response.status(200).entity(new ResponseOkDTO(200, message, data)).build();
    }

    public Response responseError(Integer status, String message) {
        return Response.status(status).entity(new ResponseErrorDTO(status, message)).build();
    }
}
