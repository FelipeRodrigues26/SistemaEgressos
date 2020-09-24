/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.egressos.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Porto
 */
@Entity
@Table(name = "participacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Participacao.findAll", query = "SELECT p FROM Participacao p")
    , @NamedQuery(name = "Participacao.findByEgressocpf", query = "SELECT p FROM Participacao p WHERE p.participacaoPK.egressocpf = :egressocpf")
    , @NamedQuery(name = "Participacao.findByOportunidadeid", query = "SELECT p FROM Participacao p WHERE p.participacaoPK.oportunidadeid = :oportunidadeid")
    , @NamedQuery(name = "Participacao.findByDate", query = "SELECT p FROM Participacao p WHERE p.date = :date")})
public class Participacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParticipacaoPK participacaoPK;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "Egresso_cpf", referencedColumnName = "cpf", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Egresso egresso;
    @JoinColumn(name = "Oportunidade_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Oportunidade oportunidade;

    public Participacao() {
    }

    public Participacao(ParticipacaoPK participacaoPK) {
        this.participacaoPK = participacaoPK;
    }

    public Participacao(String egressocpf, int oportunidadeid) {
        this.participacaoPK = new ParticipacaoPK(egressocpf, oportunidadeid);
    }

    public ParticipacaoPK getParticipacaoPK() {
        return participacaoPK;
    }

    public void setParticipacaoPK(ParticipacaoPK participacaoPK) {
        this.participacaoPK = participacaoPK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Egresso getEgresso() {
        return egresso;
    }

    public void setEgresso(Egresso egresso) {
        this.egresso = egresso;
    }

    public Oportunidade getOportunidade() {
        return oportunidade;
    }

    public void setOportunidade(Oportunidade oportunidade) {
        this.oportunidade = oportunidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (participacaoPK != null ? participacaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participacao)) {
            return false;
        }
        Participacao other = (Participacao) object;
        if ((this.participacaoPK == null && other.participacaoPK != null) || (this.participacaoPK != null && !this.participacaoPK.equals(other.participacaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.Participacao[ participacaoPK=" + participacaoPK + " ]";
    }
    
}
