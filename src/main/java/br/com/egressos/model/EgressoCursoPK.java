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
public class EgressoCursoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "Egresso_cpf")
    private String egressocpf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Curso_id")
    private int cursoid;

    public EgressoCursoPK() {
    }

    public EgressoCursoPK(String egressocpf, int cursoid) {
        this.egressocpf = egressocpf;
        this.cursoid = cursoid;
    }

    public String getEgressocpf() {
        return egressocpf;
    }

    public void setEgressocpf(String egressocpf) {
        this.egressocpf = egressocpf;
    }

    public int getCursoid() {
        return cursoid;
    }

    public void setCursoid(int cursoid) {
        this.cursoid = cursoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egressocpf != null ? egressocpf.hashCode() : 0);
        hash += (int) cursoid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EgressoCursoPK)) {
            return false;
        }
        EgressoCursoPK other = (EgressoCursoPK) object;
        if ((this.egressocpf == null && other.egressocpf != null) || (this.egressocpf != null && !this.egressocpf.equals(other.egressocpf))) {
            return false;
        }
        if (this.cursoid != other.cursoid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.EgressoCursoPK[ egressocpf=" + egressocpf + ", cursoid=" + cursoid + " ]";
    }
    
}
