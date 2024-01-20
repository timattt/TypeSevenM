package org.shlimtech.typesixm.tests.service;

import org.junit.jupiter.api.Test;
import org.shlimtech.typesixdatabasecommon.dto.UserDTO;
import org.shlimtech.typesixdatabasecommon.service.UserService;
import org.shlimtech.typesixm.dto.MetadataDTO;
import org.shlimtech.typesixm.metadata.Metadata;
import org.shlimtech.typesixm.service.MetadataService;
import org.shlimtech.typesixm.tests.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

public class MetadataUserServiceTests extends BaseTest {

    @Autowired
    private MetadataService metadataService;

    @Autowired
    private UserService userService;

    private int insertTestUser() {
        userService.createOrComplementUser(UserDTO.builder().email("ggg@gmail.com").firstName("hhh").build());
        Assert.isTrue(userService.loadUser("ggg@gmail.com") != null, "must contains user");
        int id = userService.loadUser("ggg@gmail.com").getId();
        return id;
    }

    @Test
    public void simpleMetadataCreationTest() {
        int id = insertTestUser();
        metadataService.saveUserMetadata(id, Metadata.builder().version("v0").build());
        Assert.isTrue(metadataService.loadUserMetadata(id).getVersion().equals("v0"), "incorrect metadata");
    }

    @Test
    public void metadataGenerationTest() {
        int id = insertTestUser();
        Assert.isTrue(metadataService.loadUserMetadata(id).getVersion().equals(metadataService.generateMetadata().getVersion()), "incorrect new metadata");
    }

    @Test
    public void testGetUserWithoutMetadata() {
        int id = userService.createOrComplementUser(UserDTO.builder().email("ddd@test.com").build()).getId();
        Assert.isTrue(metadataService.loadUserMetadata(id).equals(metadataService.generateMetadata()), "metadata not inserted");
    }

    @Test
    public void metadataDTOTest() {
        int id = insertTestUser();
        metadataService.saveUserMetadata(id, Metadata.builder().version("v0").selectedUsers(List.of(1)).build());
        MetadataDTO dto = metadataService.loadUserMetadataDTO(id);
        Assert.isTrue(dto.getSelectedUsers().size() == 1, "not mapped correctly");
    }

}
