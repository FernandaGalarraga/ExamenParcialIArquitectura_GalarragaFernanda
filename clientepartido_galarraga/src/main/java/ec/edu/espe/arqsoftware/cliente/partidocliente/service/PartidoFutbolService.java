/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.cliente.partidocliente.service;

import ec.espe.edu.arqsoftware.cliente.partidocliente.wsdl.ObtenerPartidosDisponiblesRequest;
import ec.espe.edu.arqsoftware.cliente.partidocliente.wsdl.ObtenerPartidosDisponiblesResponse;
import ec.espe.edu.arqsoftware.cliente.partidocliente.wsdl.PartidoRS;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Service
public class PartidoFutbolService extends WebServiceGatewaySupport {
    private String endpoint = "http://localhost:8090/ws/partido.wsdl";
    
    public List<PartidoRS> obtenerPartidosDisponibles() throws DatatypeConfigurationException{
        ObtenerPartidosDisponiblesRequest request = new ObtenerPartidosDisponiblesRequest();
        ObtenerPartidosDisponiblesResponse response 
                = (ObtenerPartidosDisponiblesResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint, request);
        return response.getPartidosRS();
    }
}
