package com.dayao.prep18.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Distritos.
 */
@Entity
@Table(name = "distritos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Distritos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "num_distrito", nullable = false)
    private Integer num_distrito;

    @NotNull
    @Column(name = "nom_distrito", nullable = false)
    private String nom_distrito;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNum_distrito() {
        return num_distrito;
    }

    public Distritos num_distrito(Integer num_distrito) {
        this.num_distrito = num_distrito;
        return this;
    }

    public void setNum_distrito(Integer num_distrito) {
        this.num_distrito = num_distrito;
    }

    public String getNom_distrito() {
        return nom_distrito;
    }

    public Distritos nom_distrito(String nom_distrito) {
        this.nom_distrito = nom_distrito;
        return this;
    }

    public void setNom_distrito(String nom_distrito) {
        this.nom_distrito = nom_distrito;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Distritos distritos = (Distritos) o;
        if (distritos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), distritos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Distritos{" +
            "id=" + getId() +
            ", num_distrito='" + getNum_distrito() + "'" +
            ", nom_distrito='" + getNom_distrito() + "'" +
            "}";
    }
}
