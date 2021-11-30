/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.cliente.partidocliente.controller;

import ec.edu.espe.arqsoftware.cliente.partidocliente.service.PartidoFutbolService;
import ec.espe.edu.arqsoftware.cliente.partidocliente.wsdl.PartidoRS;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partido")
@Slf4j
public class PartidoFutbolController {
    
    private final PartidoFutbolService service;

    public PartidoFutbolController(PartidoFutbolService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity listarPartidosDisponibles() {
        try {
            List<PartidoRS> partidos = this.service.obtenerPartidosDisponibles();
            if (partidos.size() <= 0) {
               log.info("No se encontro ningun partido");
               return ResponseEntity.badRequest().build();
            } else {
                log.info("Partidos obtenidos {}",
                        partidos.size());
                return ResponseEntity.ok(partidos);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("{}", e.getStackTrace());
            return ResponseEntity.badRequest().build();
        }  
        
    }
}
