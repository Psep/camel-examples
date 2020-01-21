package cl.psep.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author psepulvedap
 *
 */
@Entity
@Table(name = "REGION")
@NamedQueries({ @NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r") })
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Region implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "CODISO")
	private String codIso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodIso() {
		return codIso;
	}

	public void setCodIso(String codIso) {
		this.codIso = codIso;
	}

}
