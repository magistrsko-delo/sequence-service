package si.fri.mag.converters;

import si.fri.mag.entities.SequenceMediaEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SequenceMediaConverter {

    public List<Integer> toDTO(List<SequenceMediaEntity> sequenceMediaEntities) {
        List<Integer> mediaIds = new ArrayList<>();
        for (SequenceMediaEntity sequenceMediaEntity : sequenceMediaEntities) {
            mediaIds.add(sequenceMediaEntity.getMediaId());
        }

        return mediaIds;
    }
}
