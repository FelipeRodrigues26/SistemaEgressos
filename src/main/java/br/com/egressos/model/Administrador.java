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
import javax.persistence.FetchType;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Porto
 */
@Entity
@Table(name = "administrador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a")
    , @NamedQuery(name = "Administrador.findByIdAdm", query = "SELECT a FROM Administrador a WHERE a.idAdm = :idAdm")
    , @NamedQuery(name = "Administrador.findByNome", query = "SELECT a FROM Administrador a WHERE a.nome = :nome")})
public class Administrador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_adm")
    private Integer idAdm;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @JoinColumn(name = "Campus_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Campus campusid;
    @JsonIgnore
    @JoinColumn(name = "Usuario_login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Administrador() {
    }

    public Administrador(Integer idAdm) {
        this.idAdm = idAdm;
    }

    public Integer getIdAdm() {
        return idAdm;
    }

    public void setIdAdm(Integer idAdm) {
        this.idAdm = idAdm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Campus getCampusid() {
        return campusid;
    }

    public void setCampusid(Campus campusid) {
        this.campusid = campusid;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdm != null ? idAdm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.idAdm == null && other.idAdm != null) || (this.idAdm != null && !this.idAdm.equals(other.idAdm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.Administrador[ idAdm=" + idAdm + " ]";
    }
    
}
