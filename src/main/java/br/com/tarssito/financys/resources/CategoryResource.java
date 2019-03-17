package br.com.tarssito.financys.resources;

import br.com.tarssito.financys.domain.Category;
import br.com.tarssito.financys.dto.CategoryDTO;
import br.com.tarssito.financys.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = service.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> find(@PathVariable Long id) {
        Category category = service.find(id);
        return ResponseEntity.ok().body(category);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = service.fromDTO(categoryDTO);
        category = service.save(category);
        /* Retorna na url o id inserido (categorias/{id}) */
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        Category category = service.fromDTO(categoryDTO);
        category.setId(id);
        service.save(category);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
