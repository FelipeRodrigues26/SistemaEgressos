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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Porto
 */
@Entity
@Table(name = "egresso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Egresso.findAll", query = "SELECT e FROM Egresso e")
    , @NamedQuery(name = "Egresso.findByCpf", query = "SELECT e FROM Egresso e WHERE e.cpf = :cpf")
    , @NamedQuery(name = "Egresso.findByNome", query = "SELECT e FROM Egresso e WHERE e.nome = :nome")
    , @NamedQuery(name = "Egresso.findByDataNascimento", query = "SELECT e FROM Egresso e WHERE e.dataNascimento = :dataNascimento")
    , @NamedQuery(name = "Egresso.findByNomeMae", query = "SELECT e FROM Egresso e WHERE e.nomeMae = :nomeMae")
    , @NamedQuery(name = "Egresso.findByEmail", query = "SELECT e FROM Egresso e WHERE e.email = :email")
    , @NamedQuery(name = "Egresso.findByFone", query = "SELECT e FROM Egresso e WHERE e.fone = :fone")})
public class Egresso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "cpf")
    private String cpf;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Size(max = 45)
    @Column(name = "nome_mae")
    private String nomeMae;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 15)
    @Column(name = "fone")
    private String fone;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresso")
    private Collection<EgressoCurso> egressoCursoCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egressocpf")
    private Collection<Qualificacao> qualificacaoCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egressocpf")
    private Collection<HistoricoProfissional> historicoProfissionalCollection;
    @JoinColumn(name = "Endereco_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Endereco endereco;
    
    @JoinColumn( name = "Usuario_login", referencedColumnName = "login")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Usuario usuario;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egressocpf")
    private Collection<RepostaEgresso> repostaEgressoCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresso")
    private Collection<Participacao> participacaoCollection;

    public Egresso() {
    }

    public Egresso(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    @XmlTransient
    public Collection<EgressoCurso> getEgressoCursoCollection() {
        return egressoCursoCollection;
    }

    public void setEgressoCursoCollection(Collection<EgressoCurso> egressoCursoCollection) {
        this.egressoCursoCollection = egressoCursoCollection;
    }

    @XmlTransient
    public Collection<Qualificacao> getQualificacaoCollection() {
        return qualificacaoCollection;
    }

    public void setQualificacaoCollection(Collection<Qualificacao> qualificacaoCollection) {
        this.qualificacaoCollection = qualificacaoCollection;
    }

    @XmlTransient
    public Collection<HistoricoProfissional> getHistoricoProfissionalCollection() {
        return historicoProfissionalCollection;
    }

    public void setHistoricoProfissionalCollection(Collection<HistoricoProfissional> historicoProfissionalCollection) {
        this.historicoProfissionalCollection = historicoProfissionalCollection;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<RepostaEgresso> getRepostaEgressoCollection() {
        return repostaEgressoCollection;
    }

    public void setRepostaEgressoCollection(Collection<RepostaEgresso> repostaEgressoCollection) {
        this.repostaEgressoCollection = repostaEgressoCollection;
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
        hash += (cpf != null ? cpf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Egresso)) {
            return false;
        }
        Egresso other = (Egresso) object;
        if ((this.cpf == null && other.cpf != null) || (this.cpf != null && !this.cpf.equals(other.cpf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.Egresso[ cpf=" + cpf + " ]";
    }
    
}
