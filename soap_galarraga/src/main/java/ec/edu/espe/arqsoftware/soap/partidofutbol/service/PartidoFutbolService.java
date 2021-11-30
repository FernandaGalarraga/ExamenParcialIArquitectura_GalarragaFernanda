/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.soap.partidofutbol.service;

import ec.edu.espe.arqsoftware.soap.partidofutbol.dao.PartidoFutbolRepository;
import ec.edu.espe.arqsoftware.soap.partidofutbol.exception.FoundException;
import ec.edu.espe.arqsoftware.soap.partidofutbol.model.PartidoFutbol;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PartidoFutbolService {
    private final PartidoFutbolRepository partidoRepo;

    public PartidoFutbolService(PartidoFutbolRepository partidoRepo) {
        this.partidoRepo = partidoRepo;
    }
    
    public List<PartidoFutbol> obtenerPartidosDisponibles(){
        PartidoFutbol partido = new PartidoFutbol();
        LocalDateTime fechaActual = LocalDateTime.now(ZoneId.of("America/New_York")).withNano(0);
        if(partido.getFecha().isAfter(fechaActual)){
            return this.partidoRepo.findAll();
        }else{
            throw new FoundException("No se ha encontrado disponibilidad de partidos");
        }
    }
    
}
