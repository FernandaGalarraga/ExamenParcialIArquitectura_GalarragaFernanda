/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.cliente.partidocliente.service;

import ec.espe.edu.arqsoftware.cliente.partidocliente.wsdl.LocalidadRS;
import ec.espe.edu.arqsoftware.cliente.partidocliente.wsdl.ObtenerLocalidadDisponibleRequest;
import ec.espe.edu.arqsoftware.cliente.partidocliente.wsdl.ObtenerLocalidadDisponibleResponse;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Service
public class LocalidadPartidoService extends WebServiceGatewaySupport {
    private String endpoint = "http://localhost:8090/ws/localidad.wsdl";
    
     public List<LocalidadRS> obtenerLocalidadesDisponibles(String codigo) throws DatatypeConfigurationException{
        ObtenerLocalidadDisponibleRequest request = new ObtenerLocalidadDisponibleRequest();
        request.setCodigo(codigo);
        ObtenerLocalidadDisponibleResponse response 
                = (ObtenerLocalidadDisponibleResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint, request);
        return response.getLocalidadS();
    }
}
