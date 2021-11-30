/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.soap.partidofutbol.transform;

import ec.edu.espe.arqsoftware.soap.partidofutbol.model.PartidoFutbol;
import ec.edu.espe.arqsoftware.soap.partidofutbol.ws.PartidoRS;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


public class PartidoFutbolTransform {
    public static PartidoRS buildFutbolTransformRS(PartidoFutbol partido) throws DatatypeConfigurationException {
        LocalDateTime fecha = partido.getFecha();
        XMLGregorianCalendar fechaD = DatatypeFactory.newInstance().newXMLGregorianCalendar(fecha.toString());
        return PartidoRS.builder()
                .codigo(partido.getCodigo())
                .equipoLocal(partido.getEquipoLocal())
                .equipoVisita(partido.getEquipoVisita())
                .fecha(fechaD)
                .lugar(partido.getLugar())
                .build();
    }
}
