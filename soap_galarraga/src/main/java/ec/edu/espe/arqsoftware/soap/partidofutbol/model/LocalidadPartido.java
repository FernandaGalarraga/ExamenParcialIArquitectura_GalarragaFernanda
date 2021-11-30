/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.soap.partidofutbol.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@Entity
@Table(name = "localidad_partido")
@XmlRootElement
public class LocalidadPartido implements Serializable {

    @Id
    @Column(name = "codigo_localidad", nullable = false, length = 3)
    private String codigoLocalidad;
    
    @Column(name = "codigo", nullable = false, length = 3)
    private String codigoPartido;
    
    @Column(name = "disponibilidad", nullable = false)
    private int disponibilidad;
    
    @Column(name = "precio", nullable = false, precision = 8, scale = 2)
    private BigDecimal precio;
    
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    @ManyToOne
    private PartidoFutbol codigo;

}
