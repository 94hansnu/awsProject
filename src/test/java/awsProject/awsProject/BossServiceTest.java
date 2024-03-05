package awsProject.awsProject;

import awsProject.awsProject.database.entity.Boss;
import awsProject.awsProject.database.repository.BossRepository;
import awsProject.awsProject.database.repository.SlaveRepository;
import awsProject.awsProject.service.BossService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BossServiceTest {

    @Mock
    private BossRepository bossRepository;

    @Mock
    private SlaveRepository slaveRepository;

    @InjectMocks
    private BossService bossService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    // Testa hämta alla bossar
    public void testGetAllBosses() {
        // Arrange
        // Mocka Boss-objekt
        Boss boss1 = new Boss();
        boss1.setTitle("CEO");
        Boss boss2 = new Boss();
        boss2.setTitle("CTO");
        List<Boss> bosses  = Arrays.asList(boss1, boss2);

        when(bossRepository.findAll()).thenReturn(bosses);

        // Act
        List<Boss> result = bossService.getAllBosses();

        // Assert
        Assertions.assertEquals(2, result.size());
    }

    @Test
    // Test för att hämta Boss genom angivet ID
    public void testGetBossById() {
        // Arrange
        Boss boss = new Boss();
        boss.setTitle("CEO");

        when(bossRepository.findById(1L)).thenReturn(Optional.of(boss));

        // Act
        Optional<Boss> result = bossService.getBossById(1L);

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("CEO", result.get().getTitle());
    }

    @Test
    // Test för att spara en ny boss
    public void testSaveBoss() {
        // Arrange
        Boss boss = new Boss();
        boss.setTitle("CEO");

        when(bossRepository.save(any(Boss.class))).thenReturn(boss);

        // Act
        Boss result = bossService.saveBoss(boss);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("CEO", result.getTitle());
    }

    @Test
    // Test för att uppdatera en boss
    public void testUpdateBoss() {
        // Arrange
        Boss boss = new Boss();
        boss.setTitle("CEO");

        when(bossRepository.findById(1L)).thenReturn(Optional.of(boss));
        when(bossRepository.save(any(Boss.class))).thenReturn(boss);

        Boss updatedBoss = new Boss();
        updatedBoss.setTitle("NyBoss");

        // Act
        Optional<Boss> result = bossService.updateBoss(1L, updatedBoss);

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("NyBoss", result.get().getTitle());
    }

    @Test
    // Test för att ta bort en boss
    public void testDeleteBoss() {
        // Arrange
        Boss boss = new Boss();
        // Skapa en tom lista för slavar för att undvika null-pointer exception
        boss.setSlaves(new ArrayList<>());

        when(bossRepository.findById(1L)).thenReturn(Optional.of(boss));

        // Act
        bossService.deleteBoss(1L);

        // Verify
        verify(slaveRepository, times(1)).saveAll(any());
        verify(bossRepository, times(1)).delete(boss);
    }
}
