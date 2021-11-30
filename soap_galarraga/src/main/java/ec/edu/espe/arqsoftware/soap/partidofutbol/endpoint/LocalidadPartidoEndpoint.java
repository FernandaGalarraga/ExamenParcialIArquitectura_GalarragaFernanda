/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.soap.partidofutbol.endpoint;

import ec.edu.espe.arqsoftware.soap.partidofutbol.exception.FoundException;
import ec.edu.espe.arqsoftware.soap.partidofutbol.model.LocalidadPartido;
import ec.edu.espe.arqsoftware.soap.partidofutbol.service.LocalidadPartidoService;
import static ec.edu.espe.arqsoftware.soap.partidofutbol.transform.LocalidadRSTransform.buildLocalidadTransformRS;
import ec.edu.espe.arqsoftware.soap.partidofutbol.ws.LocalidadRS;
import ec.edu.espe.arqsoftware.soap.partidofutbol.ws.ObtenerLocalidadDisponibleRequest;
import ec.edu.espe.arqsoftware.soap.partidofutbol.ws.ObtenerLocalidadDisponibleResponse;
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
public class LocalidadPartidoEndpoint {
    
    private static final String NAMESPACE_URI = "http://espe.edu.ec/arqsoftware/activotarjeta/ws";
    private final LocalidadPartidoService service;
    
    @Autowired
    public LocalidadPartidoEndpoint(LocalidadPartidoService service) {
        this.service = service;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "obtenerLocalidadDisponibleRequest")
    @ResponsePayload
    ObtenerLocalidadDisponibleResponse obtenerLocalidad(@RequestPayload ObtenerLocalidadDisponibleRequest request){
        log.info("Se va a buscar localidades disponibles con la siguiente informacion: {}", request.getCodigo());
        List<LocalidadRS> localidadRS = new ArrayList<>();
        try {
            List<LocalidadPartido> localidad = this.service.obtenerLocalidadDisponible(request.getCodigo());
            if (localidad.size() <= 0) {
                log.error("No hay ninguna localidad");
                throw new FoundException("No se ha encontrado ninguna localidad");
            } else {
                log.info("Localidades obtenidos {}",
                        localidad.size());
                localidad.forEach(t -> {
                    try {
                        localidadRS.add(buildLocalidadTransformRS(t));
                    } catch (DatatypeConfigurationException ex) {
                        Logger.getLogger(PartidoFutbolEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            ObtenerLocalidadDisponibleResponse response = new ObtenerLocalidadDisponibleResponse();
            response.setLocalidadS(localidadRS);
            return response;
        } catch (Exception e) {
            String exceptionMessage = e.getMessage();
            log.error("Ocurrio un error al obtener {} - retorna badrequest - causado por: {}", e.getMessage(), exceptionMessage);
            throw new FoundException("Error: " + exceptionMessage);
        }
    }
}
