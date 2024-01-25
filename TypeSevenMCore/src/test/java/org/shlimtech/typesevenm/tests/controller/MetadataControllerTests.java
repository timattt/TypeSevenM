package org.shlimtech.typesevenm.tests.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.shlimtech.typesevenm.tests.BaseTest;
import org.shlimtech.typesixdatabasecommon.dto.UserDTO;
import org.shlimtech.typesixdatabasecommon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MetadataControllerTests extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    @SneakyThrows
    public void jsonParsingErrorTest() {
        String json = "{\"version\":\"v1\",\"metadataEntrySets\":[{\"name\":\"Пол\",\"message\":\"Кто вы?\",\"minimumChoices\":1,\"maximumChoices\":1,\"entries\":[{\"name\":\"Парень\",\"message\":\"Парень\",\"flag\":false},{\"name\":\"Девушка\",\"message\":\"Девушка\",\"flag\":true}]},{\"name\":\"Цель\",\"message\":\"Кого ищем?\",\"minimumChoices\":1,\"maximumChoices\":1,\"entries\":[{\"name\":\"Парень\",\"message\":\"Парней\",\"flag\":true},{\"name\":\"Девушка\",\"message\":\"Девушек\",\"flag\":false}]},{\"name\":\"Увлечения\",\"message\":null,\"minimumChoices\":0,\"maximumChoices\":2147483647,\"entries\":[{\"name\":\"Бег\",\"message\":\"Бег\",\"flag\":true},{\"name\":\"Покер\",\"message\":\"Покер\",\"flag\":false}]},{\"name\":\"Любимая музыка\",\"message\":null,\"minimumChoices\":0,\"maximumChoices\":2147483647,\"entries\":[{\"name\":\"Классика\",\"message\":\"Классика\",\"flag\":false},{\"name\":\"Попса\",\"message\":\"Попса\",\"flag\":false}]}],\"selectedUsers\":[{\"id\":2,\"email\":\"test1@gmail.com\",\"login\":\"admin\",\"firstName\":\"TestName\",\"lastName\":\"TestLastName\",\"biography\":\"TestBio\",\"birthday\":\"\",\"phone\":\"\",\"vkLink\":\"\",\"githubLink\":\"\"}]}";

        int id = userService.createOrComplementUser(UserDTO.builder().email("ggg@mail.ru").build()).getId();

        mockMvc.perform(post("/metadata/set")
                        .with(SecurityMockMvcRequestPostProcessors.jwt().jwt(c -> c.claim("id", id)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

}
