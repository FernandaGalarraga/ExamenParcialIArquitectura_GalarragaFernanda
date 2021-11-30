/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.cliente.partidocliente.config;

import ec.edu.espe.arqsoftware.cliente.partidocliente.service.PartidoFutbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WebServiceConfig {
    
    @Autowired
    //private TransaccionService transaccion;
    private PartidoFutbolService partido;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ec.espe.edu.arqsoftware.cliente.partidocliente.wsdl");
        return marshaller;
    }

    @Bean
    @Primary
    public PartidoFutbolService partidoServ(Jaxb2Marshaller marshaller) {
        partido.setMarshaller(marshaller);
        partido.setUnmarshaller(marshaller);
        return partido;
    }
}
