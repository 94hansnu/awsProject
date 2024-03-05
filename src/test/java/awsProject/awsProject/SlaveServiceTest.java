package awsProject.awsProject;

import awsProject.awsProject.database.entity.Boss;
import awsProject.awsProject.database.entity.Slave;
import awsProject.awsProject.database.repository.BossRepository;
import awsProject.awsProject.database.repository.SlaveRepository;
import awsProject.awsProject.service.SlaveService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SlaveServiceTest {

    @Mock
    private SlaveRepository slaveRepository;

    @Mock
    private BossRepository bossRepository;

    @InjectMocks
    private SlaveService slaveService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    // Test för att hämta alla slavar
    public void testGetAllSlaves() {
        // Mocka Slav-objekt
        Slave slave1 = new Slave();
        Slave slave2 = new Slave();
        List<Slave> slaves = Arrays.asList(slave1, slave2);

        when(slaveRepository.findAll()).thenReturn(slaves);

        List<Slave> result = slaveService.getAllSlaves();

        Assertions.assertEquals(2, result.size());
    }

    @Test
    // Test för att hämta en slav baserat på ID
    public void testGetSlaveById() {
        Slave slave =  new Slave();

        when(slaveRepository.findById(1L)).thenReturn(Optional.of(slave));

        Optional<Slave> result = slaveService.getSlaveById(1L);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    // Test för att spara en ny slav
    public void testSaveSlave() {
        Slave slave = new Slave();

        when(slaveRepository.save(any(Slave.class))).thenReturn(slave);

        Slave result = slaveService.saveSlave(slave);

        Assertions.assertNotNull(result);
    }

    @Test
    // Test för att uppdatera en slav
    public void testUpdateSlaveBoss() {
        Slave slave = new Slave();
        slave.setId(1L);

        when(bossRepository.findById(1L)).thenReturn(Optional.of(new Boss()));
        when(slaveRepository.findById(1L)).thenReturn(Optional.of(slave));
        when(slaveRepository.save(any(Slave.class))).thenReturn(slave);

        Slave result = slaveService.updateSlaveBoss(1L, 1L);

        Assertions.assertNotNull(result);
    }

    @Test
    // Test för att ta bort en slav
    public void testDeleteSlave() {
        Slave slave = new Slave();
        slave.setId(1L);

        when(slaveRepository.findById(1L)).thenReturn(Optional.of(slave));

        slaveService.deleteSlave(1L);

        verify(slaveRepository, times(1)).delete(slave);
    }
}
