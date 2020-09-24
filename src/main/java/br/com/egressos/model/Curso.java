/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.egressos.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Porto
 */
@Entity
@Table(name = "curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c")
    , @NamedQuery(name = "Curso.findById", query = "SELECT c FROM Curso c WHERE c.id = :id")
    , @NamedQuery(name = "Curso.findByNome", query = "SELECT c FROM Curso c WHERE c.nome = :nome")
    , @NamedQuery(name = "Curso.findByCargaHoraria", query = "SELECT c FROM Curso c WHERE c.cargaHoraria = :cargaHoraria")
    , @NamedQuery(name = "Curso.findByNumeroSemestres", query = "SELECT c FROM Curso c WHERE c.numeroSemestres = :numeroSemestres")})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Size(max = 45)
    @Column(name = "carga_horaria")
    private String cargaHoraria;
    @Column(name = "numero_semestres")
    private Integer numeroSemestres;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private Collection<EgressoCurso> egressoCursoCollection;
    @JoinColumn(name = "Campus_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Campus campusid;
    @JoinColumn(name = "Coordenador_siape", referencedColumnName = "siape")
    @ManyToOne(optional = false)
    private Coordenador coordenadorsiape;

    public Curso() {
    }

    public Curso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Integer getNumeroSemestres() {
        return numeroSemestres;
    }

    public void setNumeroSemestres(Integer numeroSemestres) {
        this.numeroSemestres = numeroSemestres;
    }

    @XmlTransient
    public Collection<EgressoCurso> getEgressoCursoCollection() {
        return egressoCursoCollection;
    }

    public void setEgressoCursoCollection(Collection<EgressoCurso> egressoCursoCollection) {
        this.egressoCursoCollection = egressoCursoCollection;
    }

    public Campus getCampusid() {
        return campusid;
    }

    public void setCampusid(Campus campusid) {
        this.campusid = campusid;
    }

    public Coordenador getCoordenadorsiape() {
        return coordenadorsiape;
    }

    public void setCoordenadorsiape(Coordenador coordenadorsiape) {
        this.coordenadorsiape = coordenadorsiape;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.Curso[ id=" + id + " ]";
    }
    
}
