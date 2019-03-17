package br.com.tarssito.financys.services;

import br.com.tarssito.financys.domain.Category;
import br.com.tarssito.financys.dto.CategoryDTO;
import br.com.tarssito.financys.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends AbstractServiceImpl<Category, Long> {

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

    public Category fromDTO(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getDescription());
    }
}
