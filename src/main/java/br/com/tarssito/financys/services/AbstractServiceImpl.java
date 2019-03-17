package br.com.tarssito.financys.services;

import br.com.tarssito.financys.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractServiceImpl <E, ID extends Serializable> implements AbstractService<E, ID> {

    private JpaRepository<E, ID> repository;

    @Autowired
    public AbstractServiceImpl(JpaRepository<E, ID> repository) {
        this.repository = repository;
    }

    @Override
    public E find(ID id) {
        Optional<E> optional = repository.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(ID id) {
        find(id);
        repository.deleteById(id);
    }

    @Override
    public Page<E> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        return null;
    }
}
