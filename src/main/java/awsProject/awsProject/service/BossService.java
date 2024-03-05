package awsProject.awsProject.service;

import awsProject.awsProject.database.entity.Boss;
import awsProject.awsProject.database.entity.Slave;
import awsProject.awsProject.database.repository.BossRepository;
import awsProject.awsProject.database.repository.SlaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class BossService {

    @Autowired
    private BossRepository bossRepository;

    @Autowired
    private SlaveRepository slaveRepository;

    public List<Boss> getAllBosses() {
        try {
            List<Boss> bosses = bossRepository.findAll();
            System.out.println("All bosses retrieved: " + bosses.size());
            return bosses;
        } catch (Exception e) {
            System.out.println("Failed to retrieve all bosses: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Optional<Boss> getBossById(Long id) {
        try {
            Optional<Boss> boss = bossRepository.findById(id);
            System.out.println("Retrieving boss by ID: " + id);
            boss.ifPresent(b -> System.out.println("Boss found: " + b));
            return boss;
        } catch (Exception e) {
            System.out.println("Failed to retrieve boss by ID: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Boss saveBoss(Boss boss) {
        try {
            Boss savedBoss = bossRepository.save(boss);
            System.out.println("Boss saved: " + savedBoss);
            return savedBoss;
        } catch (Exception e) {
            System.out.println("Failed to save boss: " + e.getMessage());
            return null;
        }
    }

    public Optional<Boss> updateBoss(Long id, Boss bossDetails) {
        try {
            return getBossById(id).map(boss -> {
                boss.setTitle(bossDetails.getTitle());
                Boss updatedBoss = bossRepository.save(boss);
                System.out.println("Boss updated: " + updatedBoss);
                return updatedBoss;
            });
        } catch (Exception e) {
            System.out.println("Failed to update boss: " + e.getMessage());
            return Optional.empty();
        }
    }

    public void deleteBoss(Long id) {
        Optional<Boss> bossOptional = bossRepository.findById(id);
        bossOptional.ifPresent(boss -> {
            // Hämta alla slavar som är kopplade till denna boss
            List<Slave> slaves = boss.getSlaves();
            // Ta bort bossens referenser från slavarna
            for (Slave slave : slaves) {
                slave.setBoss(null);
            }
            // Spara de ändrade slavarna
            slaveRepository.saveAll(slaves);
            // Radera bossen
            bossRepository.delete(boss);
        });
    }

}