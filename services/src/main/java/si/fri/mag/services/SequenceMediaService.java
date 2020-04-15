package si.fri.mag.services;

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

@ApplicationScoped
public class SequenceMediaService {

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

    public boolean addMediaToSequence(Integer sequenceId, Integer mediaId) {
        SequenceEntity sequenceEntityFind = em.find(SequenceEntity.class, sequenceId);
        if (sequenceEntityFind == null) {
            throw new EntityNotFoundException("Sequence with id: " + sequenceId + " does not exist");
        }

        SequenceMediaEntity sequenceMediaEntity = sequenceMediaConverter.toEntity(mediaId, sequenceEntityFind);
        sequenceMediaEntity = (SequenceMediaEntity) entityUtils.createNewEntity(sequenceMediaEntity);

        if (sequenceMediaEntity == null) {
            throw new InternalServerErrorException("failed to create new media sequence");
        }

        return true;
    }

    public boolean deleteMediaInSequence(Integer sequenceId, Integer mediaId) {
        SequenceEntity sequenceEntityFind = em.find(SequenceEntity.class, sequenceId);
        if (sequenceEntityFind == null) {
            throw new EntityNotFoundException("Sequence with id: " + sequenceId + " does not exist");
        }
        Query querySequenceMediaDelete = entityUtils.createQuery("deleteOneMediaInSequence").setParameter(1, sequenceId).setParameter(2, mediaId);
        try {
            entityTransactions.beginTx();
            querySequenceMediaDelete.executeUpdate();
            entityTransactions.commitTx();
        } catch (Exception e) {
            entityTransactions.rollbackTx();
            throw new InternalServerErrorException("Error deleting media in sequence");
        }

        return true;
    }

}
