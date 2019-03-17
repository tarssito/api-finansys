package br.com.tarssito.financys.services;

import org.springframework.data.domain.Page;

import java.util.List;

public interface AbstractService<E, ID> {

    E find(ID id);

    List<E> findAll();

    E save(E entity);

    void delete(ID id);

    Page<E> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);

}
