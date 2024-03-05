package awsProject.awsProject.database.repository;

import awsProject.awsProject.database.entity.Boss;
import awsProject.awsProject.database.entity.Slave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BossRepository extends JpaRepository<Boss, Long> {
    Optional<Boss> findByTitle(String title);

    List<Slave> findAllSlavesByTitle(String title);
}
