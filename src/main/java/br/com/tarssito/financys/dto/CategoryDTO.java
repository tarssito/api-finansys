package br.com.tarssito.financys.dto;

import br.com.tarssito.financys.domain.Category;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@SuppressWarnings("serial")
@Data
public class CategoryDTO {

    private Long id;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
    private String name;

    private String description;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }

    public CategoryDTO() {
    }
}
