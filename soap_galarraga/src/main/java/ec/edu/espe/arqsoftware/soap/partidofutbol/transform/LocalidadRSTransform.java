/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.soap.partidofutbol.transform;

import ec.edu.espe.arqsoftware.soap.partidofutbol.model.LocalidadPartido;
import ec.edu.espe.arqsoftware.soap.partidofutbol.ws.LocalidadRS;
import javax.xml.datatype.DatatypeConfigurationException;

public class LocalidadRSTransform {
    public static LocalidadRS buildLocalidadTransformRS(LocalidadPartido partido) throws DatatypeConfigurationException {
        return LocalidadRS.builder()
                .codigo(partido.getCodigoLocalidad())
                .disponibilidad(partido.getDisponibilidad())
                .build();
    }
}
