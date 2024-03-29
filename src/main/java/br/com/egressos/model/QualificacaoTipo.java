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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Porto
 */
@Entity
@Table(name = "qualificacao_tipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QualificacaoTipo.findAll", query = "SELECT q FROM QualificacaoTipo q")
    , @NamedQuery(name = "QualificacaoTipo.findById", query = "SELECT q FROM QualificacaoTipo q WHERE q.id = :id")
    , @NamedQuery(name = "QualificacaoTipo.findByDescricao", query = "SELECT q FROM QualificacaoTipo q WHERE q.descricao = :descricao")})
public class QualificacaoTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "descricao")
    private String descricao;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qualificacaoTipo")
    private Collection<Qualificacao> qualificacaoCollection;

    public QualificacaoTipo() {
    }

    public QualificacaoTipo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<Qualificacao> getQualificacaoCollection() {
        return qualificacaoCollection;
    }

    public void setQualificacaoCollection(Collection<Qualificacao> qualificacaoCollection) {
        this.qualificacaoCollection = qualificacaoCollection;
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
        if (!(object instanceof QualificacaoTipo)) {
            return false;
        }
        QualificacaoTipo other = (QualificacaoTipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.QualificacaoTipo[ id=" + id + " ]";
    }
    
}
