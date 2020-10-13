/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.egressos.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 *
 * @author Porto
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u WHERE u.login = :login")
    , @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha")
    , @NamedQuery(name = "Usuario.findByRole", query = "SELECT u FROM Usuario u WHERE u.role = :role")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "login")
    private String login;
    @Size(max = 80)
    @Column(name = "senha")
    private String senha;
    @Size(max = 20)
    @Column(name = "nome_usuario")
    private String nomeUsuario;
    @Size(max = 45)
    @Column(name = "role")
    private String role;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "usuario" )
    private Administrador administrador;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,cascade =CascadeType.ALL, mappedBy = "usuario")
    private  Egresso egresso;
    @JsonIgnore
    @OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
    private Coordenador coordenador;
    @JsonIgnore
    @OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
    private Empresa empresa;

    public Usuario() {
    }

    public Usuario(String login) {
        this.login = login;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }
    
    public void setEgresso(Egresso egresso) {
        this.egresso = egresso;
    }

    public Egresso getEgresso() {
        return this.egresso;
    }
    public void setAdministrador(Administrador admin) {
        this.administrador = admin;
    }

    public Administrador getAdministrador() {
        return this.administrador;
    }
    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public Coordenador getCoordenador() {
        return this.coordenador;
    }
    
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.egressos.model.Usuario[ login=" + login + " ]";
    }
    
}
