package com.saveCar.SaveCar.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Builder
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String name;
    @Column(unique = true, nullable = false)
    @NotBlank
    private String email;
    @NotBlank
    private String userPassword;

    //Criando coluna de relação com a RoleEntity
    @ManyToMany(fetch = FetchType.EAGER) //Sempre que carrega o usuario vai trazer este campo no response
    @JoinTable(name = "usuarios_roles",
        joinColumns =  @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RolesEntity> roles = new HashSet<>();

    public UsuarioEntity() {

    }

    public UsuarioEntity(Long id, String name, String email, String userPassword, Set<RolesEntity> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userPassword = userPassword;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsePassword() {
        return userPassword;
    }

    public Set<RolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolesEntity> roles) {
        this.roles = roles;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    //Pega a autoridade do usuário pelo campo de roles
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    //Identificador Uníco
    //Pega email do usuário
    @Override
    public String getUsername() {
        return email;
    }

    //Identificador Uníco
    //Pega senha do usuário
    @Override
    public @Nullable String getPassword() {
        return userPassword;
    }

}
