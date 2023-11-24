/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EnderFire.PALogisticsWeb.Controllers;

import com.EnderFire.PALogisticsWeb.Utils.GenericEntity;
import com.EnderFire.PALogisticsWeb.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Oscar2
 */
public class GenericJpaController<T extends GenericEntity> implements Serializable {
    static protected EntityManagerFactory staticEMF = Persistence.createEntityManagerFactory("PALogisticsWebPU");
    public static EntityManagerFactory getEMF(){
        return staticEMF;
    }

    public GenericJpaController(Class<T> eClass){
        this(eClass,staticEMF);
    }
    public GenericJpaController(Class<T> eClass, EntityManagerFactory emf) {
        this.emf = emf;
        this.eClass = eClass;
    }
    private EntityManagerFactory emf = null;
    private Class<T> eClass;
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(T entity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(T entity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entity = em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = entity.getId();
                if (findEntity(id) == null) {
                    throw new NonexistentEntityException("The client with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            T entity;
            try {
                entity = em.getReference(eClass, id);
                entity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The client with id " + id + " no longer exists.", enfe);
            }
            em.remove(entity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<T> findEntityEntities() {
        return findEntityEntities(true, -1, -1);
    }

    public List<T> findEntityEntities(int maxResults, int firstResult) {
        return findEntityEntities(false, maxResults, firstResult);
    }

    private List<T> findEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(eClass));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public T findEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(eClass, id);
        } finally {
            em.close();
        }
    }

    public int getEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<T> rt = cq.from(eClass);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
