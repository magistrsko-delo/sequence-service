package si.fri.mag.converters;

import si.fri.mag.entities.SequenceEntity;
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

    public SequenceMediaEntity toEntity(Integer mediaId, SequenceEntity sequenceEntity) {
        SequenceMediaEntity sequenceMediaEntity =  new SequenceMediaEntity();
        sequenceMediaEntity.setMediaId(mediaId);
        sequenceMediaEntity.setSequenceEntity(sequenceEntity);

        return sequenceMediaEntity;

    }
}
