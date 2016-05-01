/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciag.infra;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ettore
 */
@Singleton
public class ManagerProducer {

    Map<String, EntityManager> entityManagers = new HashMap<>();
    Map<Class<?>, Manager> managers = new HashMap<>();

    public ManagerProducer() {
        System.err.println("Starting...");
    }

    public EntityManager getEntityManager(String name) {
        if (entityManagers.containsKey(name)) {
            return entityManagers.get(name);
        }
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(name);
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManagers.put(name, entityManager);
        return entityManager;
    }

    public <E> Manager<E> makeManager(Class<E> modelClass) {
        Connection connection = modelClass.getAnnotation(Connection.class);
        String connectionName = connection == null ? "default" : connection.value();;
        EntityManager entityManager = getEntityManager(connectionName);
        return new ManagerJPA(modelClass, entityManager);
    }

    public <E> Manager<E> getManager(Class<E> modelClass) {
        if (managers.containsKey(modelClass)) {
            return (Manager< E>) managers.get(modelClass);
        }
        Manager<E> manager = makeManager(modelClass);
        managers.put(modelClass, manager);
        return manager;
    }

    @Produces
    public <E> Manager<E> makeManager(InjectionPoint injectionPoint) {
        ParameterizedType type = (ParameterizedType) injectionPoint.getType();
        Class<E> modelClass = (Class<E>) type.getActualTypeArguments()[0];
        return getManager(modelClass);
    }

}
