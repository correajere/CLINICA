package com.microservice.profesionales.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.microservice.profesionales.entities.Profesionales;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProfesionalesRepository extends CrudRepository<Profesionales, Long> {

    // De las dos formas da lo mismo
    //@Query("SELECT p FROM Pacientes p Wwhere p.turnoId = :idTurno")
    //List<Pacientes> findAllPacientes(Long idTurno);

    // Este método lo toma automaticamente JPA por su anotación
    List<Profesionales> findAllByTurnoId(Long idTurno);
}

