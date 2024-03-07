package awsProject.awsProject.controller;

import awsProject.awsProject.database.entity.Slave;
import awsProject.awsProject.service.SlaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/slaves")
public class SlaveController {

    @Autowired
    private SlaveService slaveService;

    @GetMapping
    public ResponseEntity<List<Slave>> getAllSlaves() {
        List<Slave> slaves = slaveService.getAllSlaves();
        if (!slaves.isEmpty()) {
            return ResponseEntity.ok(slaves);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Slave> getSlaveById(@PathVariable Long id) {
        Optional<Slave> slave = slaveService.getSlaveById(id);
        return slave.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/boss/{bossId}")
    public ResponseEntity<List<Slave>> getSlavesByBoss(@PathVariable Long bossId) {
        List<Slave> slaves = slaveService.getSlavesByBoss(bossId);
        if (!slaves.isEmpty()) {
            return ResponseEntity.ok(slaves);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Slave> createSlave(@RequestBody Slave slave) {
        Slave savedSlave = slaveService.saveSlave(slave);
        if (savedSlave != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSlave);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/boss/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Slave> updateSlaveBoss(@PathVariable Long id, @RequestBody Long bossId) {
        Slave updatedSlave = slaveService.updateSlaveBoss(id, bossId);

        if (updatedSlave !=  null){
            return ResponseEntity.ok().body(updatedSlave);
        } return ResponseEntity.badRequest().body(updatedSlave);
    }

    // Todo:
    // Testa att denna fungerar
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Slave> updateSlaveAttribut(
            @PathVariable Long id, @RequestBody Slave slave) {
        Slave updateSave = slaveService.getSlaveById(id).orElse(null);
        if (updateSave != null) {
            slave.setId(id);
            slaveService.saveSlave(slave);
            return ResponseEntity.status(201).body(slave);
        }
        return ResponseEntity.status(400).body(updateSave);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteSlave (@PathVariable Long id) {
        boolean deleted = slaveService.deleteSlave(id);
        if (deleted) {
            return ResponseEntity.ok("The slave with ID " + id + " has been deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The slave with ID " + id + " could not be found.");
        }
    }
}

