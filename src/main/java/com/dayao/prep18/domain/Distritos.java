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

    @NotNull
    @Column(name = "total_actas_distrito", nullable = false)
    private Integer total_actas_distrito;

    @NotNull
    @Column(name = "lista_nominal_distrito", nullable = false)
    private Integer lista_nominal_distrito;

    @NotNull
    @Column(name = "actas_procesadas", nullable = false)
    private Integer actas_procesadas;

    @NotNull
    @Column(name = "porc_actas_procesadas", nullable = false)
    private Float porc_actas_procesadas;

    @NotNull
    @Column(name = "total_votos", nullable = false)
    private Integer total_votos;

    @NotNull
    @Column(name = "porc_participacion", nullable = false)
    private Float porc_participacion;

    @NotNull
    @Column(name = "actas_correctas", nullable = false)
    private Integer actas_correctas;

    @NotNull
    @Column(name = "actas_digitalizadas", nullable = false)
    private Integer actas_digitalizadas;

    @NotNull
    @Column(name = "actas_acopiadas", nullable = false)
    private Integer actas_acopiadas;

    @NotNull
    @Column(name = "total_votantes", nullable = false)
    private Integer total_votantes;

    @NotNull
    @Column(name = "total_sobrantes", nullable = false)
    private Integer total_sobrantes;

    @NotNull
    @Column(name = "votos_sacados_urna", nullable = false)
    private Integer votos_sacados_urna;

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

    public Integer getTotal_actas_distrito() {
        return total_actas_distrito;
    }

    public Distritos total_actas_distrito(Integer total_actas_distrito) {
        this.total_actas_distrito = total_actas_distrito;
        return this;
    }

    public void setTotal_actas_distrito(Integer total_actas_distrito) {
        this.total_actas_distrito = total_actas_distrito;
    }

    public Integer getLista_nominal_distrito() {
        return lista_nominal_distrito;
    }

    public Distritos lista_nominal_distrito(Integer lista_nominal_distrito) {
        this.lista_nominal_distrito = lista_nominal_distrito;
        return this;
    }

    public void setLista_nominal_distrito(Integer lista_nominal_distrito) {
        this.lista_nominal_distrito = lista_nominal_distrito;
    }

    public Integer getActas_procesadas() {
        return actas_procesadas;
    }

    public Distritos actas_procesadas(Integer actas_procesadas) {
        this.actas_procesadas = actas_procesadas;
        return this;
    }

    public void setActas_procesadas(Integer actas_procesadas) {
        this.actas_procesadas = actas_procesadas;
    }

    public Float getPorc_actas_procesadas() {
        return porc_actas_procesadas;
    }

    public Distritos porc_actas_procesadas(Float porc_actas_procesadas) {
        this.porc_actas_procesadas = porc_actas_procesadas;
        return this;
    }

    public void setPorc_actas_procesadas(Float porc_actas_procesadas) {
        this.porc_actas_procesadas = porc_actas_procesadas;
    }

    public Integer getTotal_votos() {
        return total_votos;
    }

    public Distritos total_votos(Integer total_votos) {
        this.total_votos = total_votos;
        return this;
    }

    public void setTotal_votos(Integer total_votos) {
        this.total_votos = total_votos;
    }

    public Float getPorc_participacion() {
        return porc_participacion;
    }

    public Distritos porc_participacion(Float porc_participacion) {
        this.porc_participacion = porc_participacion;
        return this;
    }

    public void setPorc_participacion(Float porc_participacion) {
        this.porc_participacion = porc_participacion;
    }

    public Integer getActas_correctas() {
        return actas_correctas;
    }

    public Distritos actas_correctas(Integer actas_correctas) {
        this.actas_correctas = actas_correctas;
        return this;
    }

    public void setActas_correctas(Integer actas_correctas) {
        this.actas_correctas = actas_correctas;
    }

    public Integer getActas_digitalizadas() {
        return actas_digitalizadas;
    }

    public Distritos actas_digitalizadas(Integer actas_digitalizadas) {
        this.actas_digitalizadas = actas_digitalizadas;
        return this;
    }

    public void setActas_digitalizadas(Integer actas_digitalizadas) {
        this.actas_digitalizadas = actas_digitalizadas;
    }

    public Integer getActas_acopiadas() {
        return actas_acopiadas;
    }

    public Distritos actas_acopiadas(Integer actas_acopiadas) {
        this.actas_acopiadas = actas_acopiadas;
        return this;
    }

    public void setActas_acopiadas(Integer actas_acopiadas) {
        this.actas_acopiadas = actas_acopiadas;
    }

    public Integer getTotal_votantes() {
        return total_votantes;
    }

    public Distritos total_votantes(Integer total_votantes) {
        this.total_votantes = total_votantes;
        return this;
    }

    public void setTotal_votantes(Integer total_votantes) {
        this.total_votantes = total_votantes;
    }

    public Integer getTotal_sobrantes() {
        return total_sobrantes;
    }

    public Distritos total_sobrantes(Integer total_sobrantes) {
        this.total_sobrantes = total_sobrantes;
        return this;
    }

    public void setTotal_sobrantes(Integer total_sobrantes) {
        this.total_sobrantes = total_sobrantes;
    }

    public Integer getVotos_sacados_urna() {
        return votos_sacados_urna;
    }

    public Distritos votos_sacados_urna(Integer votos_sacados_urna) {
        this.votos_sacados_urna = votos_sacados_urna;
        return this;
    }

    public void setVotos_sacados_urna(Integer votos_sacados_urna) {
        this.votos_sacados_urna = votos_sacados_urna;
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
            ", total_actas_distrito='" + getTotal_actas_distrito() + "'" +
            ", lista_nominal_distrito='" + getLista_nominal_distrito() + "'" +
            ", actas_procesadas='" + getActas_procesadas() + "'" +
            ", porc_actas_procesadas='" + getPorc_actas_procesadas() + "'" +
            ", total_votos='" + getTotal_votos() + "'" +
            ", porc_participacion='" + getPorc_participacion() + "'" +
            ", actas_correctas='" + getActas_correctas() + "'" +
            ", actas_digitalizadas='" + getActas_digitalizadas() + "'" +
            ", actas_acopiadas='" + getActas_acopiadas() + "'" +
            ", total_votantes='" + getTotal_votantes() + "'" +
            ", total_sobrantes='" + getTotal_sobrantes() + "'" +
            ", votos_sacados_urna='" + getVotos_sacados_urna() + "'" +
            "}";
    }
}
