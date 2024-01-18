package org.shlimtech.typesixm.tests;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=sa",
        "spring.profiles.active=debug",
        "type-6.client-cors-allowed-origin=https://google.com",
        "spring.security.oauth2.resourceserver.jwt.jwk-set-uri=https://google.com"
})
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class BaseTest {

    @Autowired
    protected ModelMapper modelMapper;
    @Autowired
    protected MockMvc mockMvc;

}