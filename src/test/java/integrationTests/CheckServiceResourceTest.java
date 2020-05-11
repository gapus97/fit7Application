package integrationTests;


import fun7App.entity.Users;
import fun7App.repository.UsersRepository;
import fun7App.resource.CheckServiceResource;
import fun7App.utilities.GlobalProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CheckServiceResourceTest {
    @Mock
    private UsersRepository usersRepository;

    @Mock
    private GlobalProperties globalProperties;

    @InjectMocks
    private CheckServiceResource checkServiceResource;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(checkServiceResource)
                .build();
    }

    @Test
    public void registrationWorksThroughAllLayers() throws Exception {
        setUserForMock("Europe/Ljubljana", "John", "US");
        setGlobalPropertiesForMock("https://us-central1-o7tools.cloudfunctions.net/fun7-ad-partner/", "fun7user", "fun7pass");
        mockMvc.perform(post("/checkServices")
                .contentType("application/json")
                .param("timezone", "Europe/Ljubljana")
                .param("userId", "John")
                .param("cc", "US"))
                .andExpect(status().isOk());
    }

    public void setGlobalPropertiesForMock(String url, String username, String password) {
        GlobalProperties properties = new GlobalProperties();
        properties.setUrl(url);
        properties.setPassword(password);
        properties.setUsername(username);
        when(globalProperties.getUrl()).thenReturn(properties.getUrl());
        when(globalProperties.getUsername()).thenReturn(properties.getUsername());
        when(globalProperties.getPassword()).thenReturn(properties.getPassword());
    }

    public void setUserForMock(String timezone, String UserId, String cc) {
        Users user = new Users(timezone, UserId, cc);
        user.setTimesLogged(6);
        when(usersRepository.save(any())).thenReturn(user);
        when(usersRepository.findByUserId(any())).thenReturn(user);
    }
}

