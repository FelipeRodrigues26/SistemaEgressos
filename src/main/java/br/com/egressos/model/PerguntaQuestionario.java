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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Porto
 */
@Entity
@Table(name = "pergunta_questionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerguntaQuestionario.findAll", query = "SELECT p FROM PerguntaQuestionario p")
    , @NamedQuery(name = "PerguntaQuestionario.findById", query = "SELECT p FROM PerguntaQuestionario p WHERE p.id = :id")
    , @NamedQuery(name = "PerguntaQuestionario.findByDescricao", query = "SELECT p FROM PerguntaQuestionario p WHERE p.descricao = :descricao")})
public class PerguntaQuestionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "Questionario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Questionario questionarioid;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "perguntaQuestionario")
    private RepostaEgresso repostaEgresso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perguntaQuestionarioid")
    private Collection<OpcaoResposta> opcaoRespostaCollection;

    public PerguntaQuestionario() {
    }

    public PerguntaQuestionario(Integer id) {
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

    public Questionario getQuestionarioid() {
        return questionarioid;
    }

    public void setQuestionarioid(Questionario questionarioid) {
        this.questionarioid = questionarioid;
    }

    public RepostaEgresso getRepostaEgresso() {
        return repostaEgresso;
    }

    public void setRepostaEgresso(RepostaEgresso repostaEgresso) {
        this.repostaEgresso = repostaEgresso;
    }

    @XmlTransient
    public Collection<OpcaoResposta> getOpcaoRespostaCollection() {
        return opcaoRespostaCollection;
    }

    public void setOpcaoRespostaCollection(Collection<OpcaoResposta> opcaoRespostaCollection) {
        this.opcaoRespostaCollection = opcaoRespostaCollection;
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
        if (!(object instanceof PerguntaQuestionario)) {
            return false;
        }
        PerguntaQuestionario other = (PerguntaQuestionario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.PerguntaQuestionario[ id=" + id + " ]";
    }
    
}
