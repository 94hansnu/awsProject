package awsProject.awsProject.controller;

import awsProject.awsProject.database.entity.Boss;
import awsProject.awsProject.service.BossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bosses")
public class BossController {

    @Autowired
    private BossService bossService;

    @GetMapping
    public ResponseEntity<List<Boss>> getAllBosses() {
        List<Boss> bosses = bossService.getAllBosses();
        if (!bosses.isEmpty()) {
            return ResponseEntity.ok(bosses);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boss> getBossById(@PathVariable Long id) {
        Optional<Boss> boss = bossService.getBossById(id);
        return boss.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Boss> createBoss(@RequestBody Boss boss) {
        Boss savedBoss = bossService.saveBoss(boss);
        if (savedBoss != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBoss);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boss> updateBoss(@PathVariable Long id, @RequestBody Boss bossDetails) {
        Optional<Boss> updatedBoss = bossService.updateBoss(id, bossDetails);
        return updatedBoss.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoss(@PathVariable Long id) {
        bossService.deleteBoss(id);
        return ResponseEntity.noContent().build();
    }
}