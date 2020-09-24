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
@Table(name = "egresso_curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EgressoCurso.findAll", query = "SELECT e FROM EgressoCurso e")
    , @NamedQuery(name = "EgressoCurso.findByEgressocpf", query = "SELECT e FROM EgressoCurso e WHERE e.egressoCursoPK.egressocpf = :egressocpf")
    , @NamedQuery(name = "EgressoCurso.findByCursoid", query = "SELECT e FROM EgressoCurso e WHERE e.egressoCursoPK.cursoid = :cursoid")
    , @NamedQuery(name = "EgressoCurso.findByAnoConclusao", query = "SELECT e FROM EgressoCurso e WHERE e.anoConclusao = :anoConclusao")})
public class EgressoCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EgressoCursoPK egressoCursoPK;
    @Column(name = "ano_conclusao")
    @Temporal(TemporalType.DATE)
    private Date anoConclusao;
    @JoinColumn(name = "Curso_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Curso curso;
    @JoinColumn(name = "Egresso_cpf", referencedColumnName = "cpf", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Egresso egresso;

    public EgressoCurso() {
    }

    public EgressoCurso(EgressoCursoPK egressoCursoPK) {
        this.egressoCursoPK = egressoCursoPK;
    }

    public EgressoCurso(String egressocpf, int cursoid) {
        this.egressoCursoPK = new EgressoCursoPK(egressocpf, cursoid);
    }

    public EgressoCursoPK getEgressoCursoPK() {
        return egressoCursoPK;
    }

    public void setEgressoCursoPK(EgressoCursoPK egressoCursoPK) {
        this.egressoCursoPK = egressoCursoPK;
    }

    public Date getAnoConclusao() {
        return anoConclusao;
    }

    public void setAnoConclusao(Date anoConclusao) {
        this.anoConclusao = anoConclusao;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Egresso getEgresso() {
        return egresso;
    }

    public void setEgresso(Egresso egresso) {
        this.egresso = egresso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egressoCursoPK != null ? egressoCursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EgressoCurso)) {
            return false;
        }
        EgressoCurso other = (EgressoCurso) object;
        if ((this.egressoCursoPK == null && other.egressoCursoPK != null) || (this.egressoCursoPK != null && !this.egressoCursoPK.equals(other.egressoCursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.EgressoCurso[ egressoCursoPK=" + egressoCursoPK + " ]";
    }
    
}
