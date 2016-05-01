/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciag.infra;

import java.util.List;

/**
 *
 * @author ettore
 * @param <E>
 */
public interface Manager<E> {

    public List<E> list() throws Exception;

    public void delete(E e) throws Exception;

    public void delete(Long id) throws Exception;

    public void save(E e) throws Exception;
    
    public void update(E e) throws Exception;

    public E load(Long id) throws Exception;

    public E load(E e) throws Exception;

    public void begin() throws Exception;

    public void rollback() throws Exception;

    public void commit() throws Exception;

}
