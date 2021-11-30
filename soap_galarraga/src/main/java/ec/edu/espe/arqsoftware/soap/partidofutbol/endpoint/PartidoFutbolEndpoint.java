/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.soap.partidofutbol.endpoint;

import ec.edu.espe.arqsoftware.soap.partidofutbol.exception.FoundException;
import ec.edu.espe.arqsoftware.soap.partidofutbol.model.PartidoFutbol;
import ec.edu.espe.arqsoftware.soap.partidofutbol.service.PartidoFutbolService;
import static ec.edu.espe.arqsoftware.soap.partidofutbol.transform.PartidoFutbolTransform.buildFutbolTransformRS;
import ec.edu.espe.arqsoftware.soap.partidofutbol.ws.ObtenerPartidosDisponiblesRequest;
import ec.edu.espe.arqsoftware.soap.partidofutbol.ws.ObtenerPartidosDisponiblesResponse;
import ec.edu.espe.arqsoftware.soap.partidofutbol.ws.PartidoRS;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Slf4j
public class PartidoFutbolEndpoint {

    private static final String NAMESPACE_URI = "http://espe.edu.ec/arqsoftware/activotarjeta/ws";
    private final PartidoFutbolService service;

    @Autowired
    public PartidoFutbolEndpoint(PartidoFutbolService service) {
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "obtenerPartidosDisponiblesRequest")
    @ResponsePayload
    public ObtenerPartidosDisponiblesResponse obtenerPartidosDisponibles(@RequestPayload ObtenerPartidosDisponiblesRequest request) {
        log.info("Se va a buscar partidos disponibles con la siguiente informacion: {}", request);
        List<PartidoRS> partidosRS = new ArrayList<>();
        try {
            List<PartidoFutbol> partidos = this.service.obtenerPartidosDisponibles();
            if (partidos.size() <= 0) {
                log.error("No hay ninguna partido");
                throw new FoundException("No se ha encontrado ningún partido");
            } else {
                log.info("Partidos obtenidos {}",
                        partidos.size());
                partidos.forEach(t -> {
                    try {
                        partidosRS.add(buildFutbolTransformRS(t));
                    } catch (DatatypeConfigurationException ex) {
                        Logger.getLogger(PartidoFutbolEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            ObtenerPartidosDisponiblesResponse response = new ObtenerPartidosDisponiblesResponse();
            response.setPartidosRS(partidosRS);
            return response;
        } catch (Exception e) {
            String exceptionMessage = e.getMessage();
            log.error("Ocurrio un error al obtener {} - retorna badrequest - causado por: {}", e.getMessage(), exceptionMessage);
            throw new FoundException("Error: " + exceptionMessage);
        }
    }

}
