/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.egressos.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Porto
 */
@Embeddable
public class ParticipacaoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "Egresso_cpf")
    private String egressocpf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Oportunidade_id")
    private int oportunidadeid;

    public ParticipacaoPK() {
    }

    public ParticipacaoPK(String egressocpf, int oportunidadeid) {
        this.egressocpf = egressocpf;
        this.oportunidadeid = oportunidadeid;
    }

    public String getEgressocpf() {
        return egressocpf;
    }

    public void setEgressocpf(String egressocpf) {
        this.egressocpf = egressocpf;
    }

    public int getOportunidadeid() {
        return oportunidadeid;
    }

    public void setOportunidadeid(int oportunidadeid) {
        this.oportunidadeid = oportunidadeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egressocpf != null ? egressocpf.hashCode() : 0);
        hash += (int) oportunidadeid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParticipacaoPK)) {
            return false;
        }
        ParticipacaoPK other = (ParticipacaoPK) object;
        if ((this.egressocpf == null && other.egressocpf != null) || (this.egressocpf != null && !this.egressocpf.equals(other.egressocpf))) {
            return false;
        }
        if (this.oportunidadeid != other.oportunidadeid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.ParticipacaoPK[ egressocpf=" + egressocpf + ", oportunidadeid=" + oportunidadeid + " ]";
    }
    
}
