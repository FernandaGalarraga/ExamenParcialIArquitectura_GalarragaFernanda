/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.soap.partidofutbol.service;

import ec.edu.espe.arqsoftware.soap.partidofutbol.dao.LocalidadPartidoRepository;
import ec.edu.espe.arqsoftware.soap.partidofutbol.model.LocalidadPartido;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LocalidadPartidoService {
    private final LocalidadPartidoRepository repo;

    public LocalidadPartidoService(LocalidadPartidoRepository repo) {
        this.repo = repo;
    }
    
    public List<LocalidadPartido> obtenerLocalidadDisponible(String codigo){
        return this.repo.findByCodigoPartidoAndDisponibilidadGreaterThan(codigo, 0);
    }
    
    public void comprarBoleto(String codigoLocalidad, String codigoPartido){
        LocalidadPartido localidad = new LocalidadPartido();
        localidad.setDisponibilidad(localidad.getDisponibilidad()-1);
        this.repo.save(localidad);
    }
}
