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
public class SlaveService {

    @Autowired
    private SlaveRepository slaveRepository;

    @Autowired
    private BossRepository bossRepository;

    public List<Slave> getAllSlaves() {//hej
        try {
            List<Slave> slaves = slaveRepository.findAll();
            System.out.println("All slaves retrieved: " + slaves.size());
            return slaves;
        } catch (Exception e) {
            System.out.println("Failed to retrieve all slaves: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Optional<Slave> getSlaveById(Long id) {
        try {
            Optional<Slave> slave = slaveRepository.findById(id);
            System.out.println("Retrieving slave by ID: " + id);
            slave.ifPresent(s -> System.out.println("Slave found: " + s));
            return slave;
        } catch (Exception e) {
            System.out.println("Failed to retrieve slave by ID: " + e.getMessage());
            return Optional.empty();
        }
    }

    public List<Slave> getSlavesByBoss(Long bossId) {
        try {
            Optional<Boss> boss = bossRepository.findById(bossId);
            List<Slave> slaves = boss.map(Boss::getSlaves).orElse(Collections.emptyList());
            System.out.println("Retrieving slaves for boss with ID: " + bossId);
            System.out.println("Number of slaves retrieved: " + slaves.size());
            return slaves;
        } catch (Exception e) {
            System.out.println("Failed to retrieve slaves by boss ID: " + e.getMessage());
            return Collections.emptyList();
        }
    }


    public Slave saveSlave(Slave slave) {
        try {
            Slave savedSlave = slaveRepository.save(slave);
            System.out.println("Slave saved: " + savedSlave);
            return savedSlave;
        } catch (Exception e) {
            System.out.println("Failed to save slave: " + e.getMessage());
            return null;
        }
    }


   public Slave updateSlaveBoss(Long slaveId, Long boosId) {
       try {
           Optional<Slave> slaveOptional = slaveRepository.findById(slaveId);

           if (slaveOptional.isPresent()) {
               Optional<Boss> bossOptional = bossRepository.findById(boosId);
               Slave updateSlave = slaveOptional.get();
               updateSlave.setBoss(bossOptional.get());
               return slaveRepository.save(updateSlave);
           }
       }
       catch (Exception e) {
           System.out.println("Failed to update slave: " + e.getMessage());

       }return null;
   }



    public void deleteSlave(Long id) {
        Optional<Slave> slaveOptional = slaveRepository.findById(id);
        slaveOptional.ifPresent(slave -> {
            // Hämta referensen till bossen
            Boss boss = slave.getBoss();
            if (boss != null) {
                // Ta bort slaven från bossens lista över slavar
                boss.getSlaves().remove(slave);
                // Spara den uppdaterade bossen
                bossRepository.save(boss);
            }
            // Radera slaven
            slaveRepository.delete(slave);
        });
    }
}






