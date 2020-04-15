package si.fri.mag.utils;

import si.fri.mag.interfaces.MainEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@ApplicationScoped
public class EntityUtils {
    @Inject
    private EntityManager em;

    @Inject
    private EntityTransactions entityTransactions;

    public Query createQuery(String queryName) {
        return em.createNamedQuery(queryName);
    }

    public MainEntity createNewEntity(MainEntity entity) {
        try {
            entityTransactions.beginTx();
            em.persist(entity);
            entityTransactions.commitTx();
        } catch (Exception e) {
            entityTransactions.rollbackTx();
            return null;
        }

        return  entity;
    }

}
