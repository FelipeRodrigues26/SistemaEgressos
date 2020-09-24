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
@Table(name = "historico_profissional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoProfissional.findAll", query = "SELECT h FROM HistoricoProfissional h")
    , @NamedQuery(name = "HistoricoProfissional.findById", query = "SELECT h FROM HistoricoProfissional h WHERE h.id = :id")
    , @NamedQuery(name = "HistoricoProfissional.findByEmpresa", query = "SELECT h FROM HistoricoProfissional h WHERE h.empresa = :empresa")
    , @NamedQuery(name = "HistoricoProfissional.findByProfissao", query = "SELECT h FROM HistoricoProfissional h WHERE h.profissao = :profissao")
    , @NamedQuery(name = "HistoricoProfissional.findByDataInicio", query = "SELECT h FROM HistoricoProfissional h WHERE h.dataInicio = :dataInicio")
    , @NamedQuery(name = "HistoricoProfissional.findByDataTermino", query = "SELECT h FROM HistoricoProfissional h WHERE h.dataTermino = :dataTermino")})
public class HistoricoProfissional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "empresa")
    private String empresa;
    @Size(max = 45)
    @Column(name = "profissao")
    private String profissao;
    @Size(max = 45)
    @Column(name = "data_inicio")
    private String dataInicio;
    @Size(max = 45)
    @Column(name = "data_termino")
    private String dataTermino;
    @JoinColumn(name = "Egresso_cpf", referencedColumnName = "cpf")
    @ManyToOne(optional = false)
    private Egresso egressocpf;

    public HistoricoProfissional() {
    }

    public HistoricoProfissional(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Egresso getEgressocpf() {
        return egressocpf;
    }

    public void setEgressocpf(Egresso egressocpf) {
        this.egressocpf = egressocpf;
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
        if (!(object instanceof HistoricoProfissional)) {
            return false;
        }
        HistoricoProfissional other = (HistoricoProfissional) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.HistoricoProfissional[ id=" + id + " ]";
    }
    
}
