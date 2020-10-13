/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.egressos.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Porto
 */
@Entity
@Table(name = "feedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")
    , @NamedQuery(name = "Feedback.findById", query = "SELECT f FROM Feedback f WHERE f.id = :id")
    , @NamedQuery(name = "Feedback.findByEgressoContratado", query = "SELECT f FROM Feedback f WHERE f.egressoContratado = :egressoContratado")
    , @NamedQuery(name = "Feedback.findByComentario", query = "SELECT f FROM Feedback f WHERE f.comentario = :comentario")
    , @NamedQuery(name = "Feedback.findByData", query = "SELECT f FROM Feedback f WHERE f.data = :data")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "egresso_contratado")
    private Short egressoContratado;
    @Size(max = 45)
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feedback")
    private Collection<Oportunidade> oportunidadeCollection;
    @JoinColumn(name = "Motivo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Motivo motivoid;

    public Feedback() {
    }

    public Feedback(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getEgressoContratado() {
        return egressoContratado;
    }

    public void setEgressoContratado(Short egressoContratado) {
        this.egressoContratado = egressoContratado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @XmlTransient
    public Collection<Oportunidade> getOportunidadeCollection() {
        return oportunidadeCollection;
    }

    public void setOportunidadeCollection(Collection<Oportunidade> oportunidadeCollection) {
        this.oportunidadeCollection = oportunidadeCollection;
    }

    public Motivo getMotivoid() {
        return motivoid;
    }

    public void setMotivoid(Motivo motivoid) {
        this.motivoid = motivoid;
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
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.Feedback[ id=" + id + " ]";
    }
    
}
