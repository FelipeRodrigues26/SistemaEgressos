/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.egressos.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Porto
 */
@Entity
@Table(name = "qualificacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qualificacao.findAll", query = "SELECT q FROM Qualificacao q")
    , @NamedQuery(name = "Qualificacao.findById", query = "SELECT q FROM Qualificacao q WHERE q.id = :id")
    , @NamedQuery(name = "Qualificacao.findByDescricao", query = "SELECT q FROM Qualificacao q WHERE q.descricao = :descricao")
    , @NamedQuery(name = "Qualificacao.findByAnoConclusao", query = "SELECT q FROM Qualificacao q WHERE q.anoConclusao = :anoConclusao")})
public class Qualificacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 45)
    @Column(name = "ano_conclusao")
    private String anoConclusao;
    @JoinColumn(name = "Egresso_cpf", referencedColumnName = "cpf")
    @ManyToOne(optional = false)
    private Egresso egressocpf;
    @JoinColumn(name = "Qualificacao_Tipo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private QualificacaoTipo qualificacaoTipoid;

    public Qualificacao() {
    }

    public Qualificacao(Integer id) {
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

    public String getAnoConclusao() {
        return anoConclusao;
    }

    public void setAnoConclusao(String anoConclusao) {
        this.anoConclusao = anoConclusao;
    }

    public Egresso getEgressocpf() {
        return egressocpf;
    }

    public void setEgressocpf(Egresso egressocpf) {
        this.egressocpf = egressocpf;
    }

    public QualificacaoTipo getQualificacaoTipoid() {
        return qualificacaoTipoid;
    }

    public void setQualificacaoTipoid(QualificacaoTipo qualificacaoTipoid) {
        this.qualificacaoTipoid = qualificacaoTipoid;
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
        if (!(object instanceof Qualificacao)) {
            return false;
        }
        Qualificacao other = (Qualificacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.Qualificacao[ id=" + id + " ]";
    }
    
}
