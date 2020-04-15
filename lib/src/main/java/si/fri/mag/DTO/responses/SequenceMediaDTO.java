package si.fri.mag.DTO.responses;

import si.fri.mag.DTO.SequenceDTO;

import java.util.List;

public class SequenceMediaDTO {
    private SequenceDTO sequence;
    private List<Integer> mediaIds;

    // getters


    public SequenceDTO getSequence() {
        return sequence;
    }

    public List<Integer> getMediaIds() {
        return mediaIds;
    }

    // setters


    public void setSequence(SequenceDTO sequence) {
        this.sequence = sequence;
    }

    public void setMediaIds(List<Integer> mediaIds) {
        this.mediaIds = mediaIds;
    }
}
