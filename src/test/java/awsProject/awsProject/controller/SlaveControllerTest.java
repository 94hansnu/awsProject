package awsProject.awsProject.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import awsProject.awsProject.database.entity.Slave;
import awsProject.awsProject.service.SlaveService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

public class SlaveControllerTest {

   /* @Mock
    private SlaveService slaveService;

    @InjectMocks
    private SlaveController slaveController;

    @Test
    public void testUpdateSlaveAttribut_Success() {
        // Arrange
        long id = 1L;
        Slave existingSlave = new Slave();
        existingSlave.setId(id);
        Slave updatedSlave = new Slave();
        updatedSlave.setId(id);

        when(slaveService.getSlaveById(id)).thenReturn(Optional.of(existingSlave));
        when(slaveService.saveSlave(updatedSlave)).thenReturn(updatedSlave);

        // Act
        ResponseEntity<Slave> response = slaveController.updateSlaveAttribut(id, updatedSlave);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(updatedSlave, response.getBody());
    }

    @Test
    public void testUpdateSlaveAttribut_NotFound() {
        // Arrange
        long id = 1L;
        Slave updatedSlave = new Slave();

        when(slaveService.getSlaveById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Slave> response = slaveController.updateSlaveAttribut(id, updatedSlave);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody());
    }*/
}
