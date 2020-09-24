/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.egressos.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Porto
 */
@Entity
@Table(name = "reposta_egresso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepostaEgresso.findAll", query = "SELECT r FROM RepostaEgresso r")
    , @NamedQuery(name = "RepostaEgresso.findByPerguntaQuestionarioid", query = "SELECT r FROM RepostaEgresso r WHERE r.perguntaQuestionarioid = :perguntaQuestionarioid")
    , @NamedQuery(name = "RepostaEgresso.findByDataResposta", query = "SELECT r FROM RepostaEgresso r WHERE r.dataResposta = :dataResposta")})
public class RepostaEgresso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Pergunta_Questionario_id")
    private Integer perguntaQuestionarioid;
    @Column(name = "data_resposta")
    @Temporal(TemporalType.DATE)
    private Date dataResposta;
    @JoinColumn(name = "Egresso_cpf", referencedColumnName = "cpf")
    @ManyToOne(optional = false)
    private Egresso egressocpf;
    @JoinColumn(name = "Pergunta_Questionario_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private PerguntaQuestionario perguntaQuestionario;

    public RepostaEgresso() {
    }

    public RepostaEgresso(Integer perguntaQuestionarioid) {
        this.perguntaQuestionarioid = perguntaQuestionarioid;
    }

    public Integer getPerguntaQuestionarioid() {
        return perguntaQuestionarioid;
    }

    public void setPerguntaQuestionarioid(Integer perguntaQuestionarioid) {
        this.perguntaQuestionarioid = perguntaQuestionarioid;
    }

    public Date getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(Date dataResposta) {
        this.dataResposta = dataResposta;
    }

    public Egresso getEgressocpf() {
        return egressocpf;
    }

    public void setEgressocpf(Egresso egressocpf) {
        this.egressocpf = egressocpf;
    }

    public PerguntaQuestionario getPerguntaQuestionario() {
        return perguntaQuestionario;
    }

    public void setPerguntaQuestionario(PerguntaQuestionario perguntaQuestionario) {
        this.perguntaQuestionario = perguntaQuestionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perguntaQuestionarioid != null ? perguntaQuestionarioid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepostaEgresso)) {
            return false;
        }
        RepostaEgresso other = (RepostaEgresso) object;
        if ((this.perguntaQuestionarioid == null && other.perguntaQuestionarioid != null) || (this.perguntaQuestionarioid != null && !this.perguntaQuestionarioid.equals(other.perguntaQuestionarioid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.RepostaEgresso[ perguntaQuestionarioid=" + perguntaQuestionarioid + " ]";
    }
    
}
