package si.fri.mag.services;

import si.fri.mag.DTO.input.SequenceNew;
import si.fri.mag.DTO.SequenceDTO;
import si.fri.mag.DTO.responses.SequenceMediaDTO;
import si.fri.mag.converters.SequenceConverter;
import si.fri.mag.converters.SequenceMediaConverter;
import si.fri.mag.entities.SequenceEntity;
import si.fri.mag.entities.SequenceMediaEntity;
import si.fri.mag.utils.EntityTransactions;
import si.fri.mag.utils.EntityUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.ws.rs.InternalServerErrorException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SequenceService {

    @Inject
    EntityManager em;

    @Inject
    EntityUtils entityUtils;

    @Inject
    EntityTransactions entityTransactions;

    @Inject
    SequenceConverter sequenceConverter;

    @Inject
    SequenceMediaConverter sequenceMediaConverter;

    public List<SequenceDTO> getProjectSequences(Integer projectId) {
        Query query = entityUtils.createQuery("getProjectSequences").setParameter(1, projectId);
        List<SequenceEntity> sequenceEntities = (List<SequenceEntity>) query.getResultList();

        List<SequenceDTO> sequenceDTOS = new ArrayList<SequenceDTO>();

        for (SequenceEntity sequenceEntity : sequenceEntities) {
            sequenceDTOS.add(sequenceConverter.toDTO(sequenceEntity));
        }

        return sequenceDTOS;
    }

    public SequenceMediaDTO getOneSequenceMedia (Integer sequenceId) {
        SequenceEntity sequenceEntity = em.find(SequenceEntity.class, sequenceId);
        if (sequenceEntity == null) {
            throw new EntityNotFoundException("sequence with id: " + sequenceId + " not found");
        }

        List<SequenceMediaEntity> sequenceMediaEntities = (List<SequenceMediaEntity>)entityUtils
                .createQuery("getSequenceMedias")
                .setParameter(1, sequenceEntity.getSequenceId())
                .getResultList();

        SequenceMediaDTO sequenceMediaDTO = new SequenceMediaDTO();
        sequenceMediaDTO.setSequence(sequenceConverter.toDTO(sequenceEntity));
        sequenceMediaDTO.setMediaIds(sequenceMediaConverter.toDTO(sequenceMediaEntities));
        return sequenceMediaDTO;
    }

    public SequenceMediaDTO createNewSequence (SequenceNew sequenceNew) {
        SequenceEntity sequenceEntity = sequenceConverter.createNewToEntity(sequenceNew);

        sequenceEntity = (SequenceEntity) entityUtils.createNewEntity(sequenceEntity);
        if (sequenceEntity == null ) {
            throw new InternalServerErrorException("failed to create new sequence");
        }

        SequenceMediaDTO sequenceMediaDTO = new SequenceMediaDTO();
        sequenceMediaDTO.setSequence(sequenceConverter.toDTO(sequenceEntity));
        sequenceMediaDTO.setMediaIds(new ArrayList<>());
        return sequenceMediaDTO;
    }

    public SequenceMediaDTO updateSequence (SequenceDTO sequenceDTO) {
        SequenceEntity sequenceEntityFind = em.find(SequenceEntity.class, sequenceDTO.getSequenceId());
        if (sequenceEntityFind == null) {
            throw new EntityNotFoundException("Sequence with id: " + sequenceDTO.getProjectId() + " does not exist");
        }

        SequenceEntity sequenceEntity = sequenceConverter.updateEntity(sequenceDTO, sequenceEntityFind);
        sequenceEntity.setSequenceId(sequenceEntityFind.getSequenceId());

        sequenceEntity = (SequenceEntity) entityUtils.updateEntity(sequenceEntity);
        if (sequenceEntity == null) {
            throw new InternalServerErrorException("error when updating entity");
        }

        List<SequenceMediaEntity> sequenceMediaEntities = (List<SequenceMediaEntity>)entityUtils
                .createQuery("getSequenceMedias")
                .setParameter(1, sequenceEntity.getSequenceId())
                .getResultList();

        SequenceMediaDTO sequenceMediaDTO = new SequenceMediaDTO();
        sequenceMediaDTO.setSequence(sequenceConverter.toDTO(sequenceEntity));
        sequenceMediaDTO.setMediaIds(sequenceMediaConverter.toDTO(sequenceMediaEntities));
        return sequenceMediaDTO;
    }

    public boolean deleteSequence(Integer sequenceId) {
        Query querySequenceMediaDelete = entityUtils.createQuery("deleteSequenceMedia").setParameter(1, sequenceId);
        Query querySequenceDelete = entityUtils.createQuery("deleteSequence").setParameter(1, sequenceId);

        try {
            entityTransactions.beginTx();
            querySequenceMediaDelete.executeUpdate();
            querySequenceDelete.executeUpdate();
            entityTransactions.commitTx();
        } catch (Exception e) {
            entityTransactions.rollbackTx();
            return false;
        }

        return true;
    }
}
