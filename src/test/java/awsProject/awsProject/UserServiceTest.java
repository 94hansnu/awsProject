/*package awsProject.awsProject;

import awsProject.awsProject.database.entity.User;
import awsProject.awsProject.database.repository.UserRepository;
import awsProject.awsProject.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    // Test för att hämta en användare baserat på användarnamn från användarrepository
    public void testGetUserByUsername() {
        // Mocka User-objekt
        User user = new User();
        user.setUsername("UserTest");

        when(userRepository.findByUsername("UserTest")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByUsername("UserTest");

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("UserTest", result.get().getUsername());
    }

    @Test
    // Test för att ladda en användare baserat på användarnamn från användarrepositoryn
    public void testLoadUserByUsername() {
        User user = new User();
        user.setUsername("UserTest");

        when(userRepository.findByUsername("UserTest")).thenReturn(Optional.of(user));

        Assertions.assertDoesNotThrow(() -> userService.loadUserByUsername("UserTest"));
    }
}*/
