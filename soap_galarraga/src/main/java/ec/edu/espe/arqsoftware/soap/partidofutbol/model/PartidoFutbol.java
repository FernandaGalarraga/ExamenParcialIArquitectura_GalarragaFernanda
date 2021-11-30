/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.soap.partidofutbol.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@Entity
@Table(name = "partido_futbol")
@XmlRootElement

public class PartidoFutbol implements Serializable {

    @Id
    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;
    
    @Column(name = "equipo_local", nullable = false, length = 50)
    private String equipoLocal;
    
    @Column(name = "equipo_visita", nullable = false, length = 50)
    private String equipoVisita;
    
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;
    
    @Column(name = "lugar", nullable = false, length = 100)
    private String lugar;
    @OneToMany(mappedBy = "codigo")
    private List<LocalidadPartido> localidadPartido;

}
