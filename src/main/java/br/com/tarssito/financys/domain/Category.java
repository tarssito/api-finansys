package br.com.tarssito.financys.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@Data
public class Category extends AbstractEntity<Long> {

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    public Category(Long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public Category(Long id, String name) {
        super(id);
        this.name = name;
    }

    public Category() {
    }
}

