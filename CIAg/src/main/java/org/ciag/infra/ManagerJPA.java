/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciag.infra;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ettore
 */
public class ManagerJPA<E> implements Manager<E> {

    Class<E> modelClass;
    EntityManager entityManager;

    public ManagerJPA(Class<E> clazz, EntityManager entityManager) {
        this.modelClass = clazz;
        this.entityManager = entityManager;
    }

    @Override
    public List<E> list() throws Exception {
        CriteriaQuery<E> query = entityManager.getCriteriaBuilder().createQuery(modelClass);
        Root<E> root = query.from(modelClass);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void delete(E e) throws Exception {
        entityManager.remove(e);
    }

    @Override
    public void delete(Long id) throws Exception {
        entityManager.remove(load(id));
    }

    @Override
    public void save(E e) throws Exception {
        entityManager.persist(e);
    }

    @Override
    public void update(E e) throws Exception {
        entityManager.merge(e);
    }

    @Override
    public E load(Long id) throws Exception {
        return entityManager.find(modelClass, id);
    }

    @Override
    public E load(E e) throws Exception {
        entityManager.refresh(e);
        return e;
    }

    @Override
    public void begin() throws Exception {
        this.entityManager.getTransaction().begin();
    }

    @Override
    public void rollback() throws Exception {
        this.entityManager.getTransaction().rollback();
    }

    @Override
    public void commit() throws Exception {
        this.entityManager.getTransaction().commit();
    }

}
