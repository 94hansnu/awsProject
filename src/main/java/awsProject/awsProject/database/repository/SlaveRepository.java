package awsProject.awsProject.database.repository;

import awsProject.awsProject.database.entity.Slave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SlaveRepository extends JpaRepository<Slave, Long> {
    Optional<Slave> findByNationality(String nationality);
}
