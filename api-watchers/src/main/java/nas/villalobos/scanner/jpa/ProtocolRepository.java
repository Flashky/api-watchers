package nas.villalobos.scanner.jpa;

import org.springframework.data.repository.CrudRepository;

import nas.villalobos.scanner.jpa.entities.ProtocolEntity;

public interface ProtocolRepository extends CrudRepository<ProtocolEntity, Integer> {

}
