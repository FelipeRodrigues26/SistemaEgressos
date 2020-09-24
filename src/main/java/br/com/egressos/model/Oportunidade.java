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
import javax.persistence.Lob;
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
@Table(name = "oportunidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oportunidade.findAll", query = "SELECT o FROM Oportunidade o")
    , @NamedQuery(name = "Oportunidade.findById", query = "SELECT o FROM Oportunidade o WHERE o.id = :id")
    , @NamedQuery(name = "Oportunidade.findByTipo", query = "SELECT o FROM Oportunidade o WHERE o.tipo = :tipo")
    , @NamedQuery(name = "Oportunidade.findByDescricao", query = "SELECT o FROM Oportunidade o WHERE o.descricao = :descricao")
    , @NamedQuery(name = "Oportunidade.findByStatus", query = "SELECT o FROM Oportunidade o WHERE o.status = :status")})
public class Oportunidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 45)
    @Column(name = "descricao")
    private String descricao;
    @Lob
    @Size(max = 65535)
    @Column(name = "requisitos")
    private String requisitos;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "Empresa_cnpj", referencedColumnName = "cnpj")
    @ManyToOne(optional = false)
    private Empresa empresacnpj;
    @JoinColumn(name = "Feedback_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Feedback feedbackid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oportunidade")
    private Collection<Participacao> participacaoCollection;

    public Oportunidade() {
    }

    public Oportunidade(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Empresa getEmpresacnpj() {
        return empresacnpj;
    }

    public void setEmpresacnpj(Empresa empresacnpj) {
        this.empresacnpj = empresacnpj;
    }

    public Feedback getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(Feedback feedbackid) {
        this.feedbackid = feedbackid;
    }

    @XmlTransient
    public Collection<Participacao> getParticipacaoCollection() {
        return participacaoCollection;
    }

    public void setParticipacaoCollection(Collection<Participacao> participacaoCollection) {
        this.participacaoCollection = participacaoCollection;
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
        if (!(object instanceof Oportunidade)) {
            return false;
        }
        Oportunidade other = (Oportunidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.Oportunidade[ id=" + id + " ]";
    }
    
}
