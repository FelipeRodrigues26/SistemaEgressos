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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Porto
 */
@Entity
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
    , @NamedQuery(name = "Empresa.findByCnpj", query = "SELECT e FROM Empresa e WHERE e.cnpj = :cnpj")
    , @NamedQuery(name = "Empresa.findByRazaoSocial", query = "SELECT e FROM Empresa e WHERE e.razaoSocial = :razaoSocial")
    , @NamedQuery(name = "Empresa.findByNomeFantasia", query = "SELECT e FROM Empresa e WHERE e.nomeFantasia = :nomeFantasia")
    , @NamedQuery(name = "Empresa.findByAreaAtuacao", query = "SELECT e FROM Empresa e WHERE e.areaAtuacao = :areaAtuacao")
    , @NamedQuery(name = "Empresa.findBySituacaoCadastral", query = "SELECT e FROM Empresa e WHERE e.situacaoCadastral = :situacaoCadastral")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "cnpj")
    private String cnpj;
    @Size(max = 45)
    @Column(name = "razao_social")
    private String razaoSocial;
    @Size(max = 45)
    @Column(name = "nome_fantasia")
    private String nomeFantasia;
    @Size(max = 45)
    @Column(name = "area_atuacao")
    private String areaAtuacao;
    @Size(max = 45)
    @Column(name = "situacao_cadastral")
    private String situacaoCadastral;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresacnpj")
    private Collection<Oportunidade> oportunidadeCollection;
    @JoinColumn(name = "Endereco_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Endereco endereco;

    public Empresa() {
    }

    public Empresa(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getSituacaoCadastral() {
        return situacaoCadastral;
    }

    public void setSituacaoCadastral(String situacaoCadastral) {
        this.situacaoCadastral = situacaoCadastral;
    }

    @XmlTransient
    public Collection<Oportunidade> getOportunidadeCollection() {
        return oportunidadeCollection;
    }

    public void setOportunidadeCollection(Collection<Oportunidade> oportunidadeCollection) {
        this.oportunidadeCollection = oportunidadeCollection;
    }

    public Endereco getEnderecoid() {
        return endereco;
    }

    public void setEnderecoid(Endereco enderecoid) {
        this.endereco = enderecoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cnpj != null ? cnpj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.cnpj == null && other.cnpj != null) || (this.cnpj != null && !this.cnpj.equals(other.cnpj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.Empresa[ cnpj=" + cnpj + " ]";
    }
    
}
