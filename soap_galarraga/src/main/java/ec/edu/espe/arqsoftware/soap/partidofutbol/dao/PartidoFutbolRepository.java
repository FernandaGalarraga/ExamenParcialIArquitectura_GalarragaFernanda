/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.soap.partidofutbol.dao;

import ec.edu.espe.arqsoftware.soap.partidofutbol.model.PartidoFutbol;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PartidoFutbolRepository extends JpaRepository<PartidoFutbol, String> {
    
}
