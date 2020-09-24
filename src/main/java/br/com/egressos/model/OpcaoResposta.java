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
@Table(name = "opcao_resposta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpcaoResposta.findAll", query = "SELECT o FROM OpcaoResposta o")
    , @NamedQuery(name = "OpcaoResposta.findById", query = "SELECT o FROM OpcaoResposta o WHERE o.id = :id")
    , @NamedQuery(name = "OpcaoResposta.findByDescricao", query = "SELECT o FROM OpcaoResposta o WHERE o.descricao = :descricao")})
public class OpcaoResposta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "Pergunta_Questionario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PerguntaQuestionario perguntaQuestionarioid;

    public OpcaoResposta() {
    }

    public OpcaoResposta(Integer id) {
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

    public PerguntaQuestionario getPerguntaQuestionarioid() {
        return perguntaQuestionarioid;
    }

    public void setPerguntaQuestionarioid(PerguntaQuestionario perguntaQuestionarioid) {
        this.perguntaQuestionarioid = perguntaQuestionarioid;
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
        if (!(object instanceof OpcaoResposta)) {
            return false;
        }
        OpcaoResposta other = (OpcaoResposta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.OpcaoResposta[ id=" + id + " ]";
    }
    
}
