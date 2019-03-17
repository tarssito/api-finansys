package br.com.tarssito.financys.domain;

import br.com.tarssito.financys.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
@Entity
@Data
public class User extends AbstractEntity<Long>{

    @Column(unique = true)
    private String login;

    private String nome;

    private String email;

    @JsonIgnore
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Profiles")
    private Set<Integer> profiles = new HashSet<>();

    public User(Long id, String nome, String email, String login, String password, Profile profile) {
        super(id);
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.password = password;
        addProfile(profile);
    }

    public User() {
        super();
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile perfil) {
        profiles.add(perfil.getCode());
    }
}
