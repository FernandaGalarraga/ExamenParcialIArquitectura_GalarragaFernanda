/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.cliente.partidocliente.controller;

import ec.edu.espe.arqsoftware.cliente.partidocliente.service.LocalidadPartidoService;
import ec.espe.edu.arqsoftware.cliente.partidocliente.wsdl.LocalidadRS;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/localidad")
@Slf4j
public class LocalidadPartidoController {
    private final LocalidadPartidoService service;

    public LocalidadPartidoController(LocalidadPartidoService service) {
        this.service = service;
    }
    
    @GetMapping(value = "/codPartido/{codigoPartido}")
    public ResponseEntity listarLocalidadesDisponibles(@PathVariable("codigoPartido") String codigo) {
        try {
            List<LocalidadRS> localidades = this.service.obtenerLocalidadesDisponibles(codigo);
            if (localidades.size() <= 0) {
               log.info("No se encontro ninguna localidad");
               return ResponseEntity.badRequest().build();
            } else {
                log.info("Localidades obtenidas {}",
                        localidades.size());
                return ResponseEntity.ok(localidades);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("{}", e.getStackTrace());
            return ResponseEntity.badRequest().build();
        }  
        
    }
}
