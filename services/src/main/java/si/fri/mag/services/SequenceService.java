package si.fri.mag.services;

import si.fri.mag.entities.SequenceEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class SequenceService {

    @Inject
    EntityManager em;

    public void getProjectSequences(Integer projectId) {

        SequenceEntity sequenceEntity = em.find(SequenceEntity.class, 1);

        System.out.println("project: " + projectId);
        System.out.println("sequence " + sequenceEntity);
        System.out.println("sequence name" + sequenceEntity.getName());

    }

}
