package si.fri.mag.DTO.responses.generic;

public class ResponseErrorDTO extends ResponseAbstract {
    public ResponseErrorDTO(Integer status, String message) {
        super(status, message);
    }
}
