package si.fri.mag.converters;

import si.fri.mag.DTO.input.SequenceNew;
import si.fri.mag.DTO.interfaces.SequenceInterface;
import si.fri.mag.DTO.SequenceDTO;
import si.fri.mag.entities.SequenceEntity;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Date;

@ApplicationScoped
public class SequenceConverter {

    public SequenceDTO toDTO(SequenceEntity sequenceEntity) {
        SequenceDTO sequenceDTO = new SequenceDTO();
        sequenceDTO.setSequenceId(sequenceEntity.getSequenceId());
        sequenceDTO.setName(sequenceEntity.getName());
        sequenceDTO.setProjectId(sequenceEntity.getFkProjectId());
        sequenceDTO.setThumbnail(sequenceEntity.getThumbnail());
        sequenceDTO.setStatus(sequenceEntity.getStatus());
        sequenceDTO.setCreatedAt(sequenceEntity.getCreatedAt());
        sequenceDTO.setUpdatedAt(sequenceEntity.getUpdatedAt());

        return  sequenceDTO;
    }

    public SequenceEntity updateEntity(SequenceDTO sequenceDTO, SequenceEntity updateSequence) {
        SequenceEntity sequenceEntity = this.toEntity(sequenceDTO);
        sequenceEntity.setStatus(sequenceDTO.getStatus());
        sequenceEntity.setCreatedAt(updateSequence.getCreatedAt());
        sequenceEntity.setUpdatedAt(new Date(System.currentTimeMillis()));
        return sequenceEntity;
    }

    public SequenceEntity createNewToEntity(SequenceNew sequenceNew) {
        SequenceEntity sequenceEntity = this.toEntity(sequenceNew);
        sequenceEntity.setStatus(1);
        sequenceEntity.setUpdatedAt(new Date(System.currentTimeMillis()));
        sequenceEntity.setCreatedAt(new Date(System.currentTimeMillis()));

        return sequenceEntity;
    }

    private SequenceEntity toEntity(SequenceInterface sequenceNew) {
        SequenceEntity sequenceEntity = new SequenceEntity();
        sequenceEntity.setName(sequenceNew.getName());
        sequenceEntity.setFkProjectId(sequenceNew.getProjectId());
        sequenceEntity.setThumbnail(sequenceNew.getThumbnail());

        return sequenceEntity;
    }

}
