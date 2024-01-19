package org.shlimtech.typesixm.tests.service;

import org.junit.jupiter.api.Test;
import org.shlimtech.typesixdatabasecommon.dto.UserDTO;
import org.shlimtech.typesixdatabasecommon.metadata.Metadata;
import org.shlimtech.typesixdatabasecommon.service.UserService;
import org.shlimtech.typesixm.service.MetadataManagerService;
import org.shlimtech.typesixm.service.MetadataUserService;
import org.shlimtech.typesixm.tests.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class MetadataUserServiceTests extends BaseTest {

    @Autowired
    private MetadataUserService metadataUserService;

    @Autowired
    private MetadataManagerService metadataManagerService;

    @Autowired
    private UserService userService;

    private int insertTestUser() {
        metadataUserService.createOrComplementUser(UserDTO.builder().email("ggg@gmail.com").firstName("hhh").build());
        Assert.isTrue(metadataUserService.loadUser("ggg@gmail.com") != null, "must contains user");
        int id = metadataUserService.loadUser("ggg@gmail.com").getId();
        return id;
    }

    @Test
    public void simpleMetadataCreationTest() {
        int id = insertTestUser();
        metadataUserService.setMetadata(id, Metadata.builder().version("v0").build());
        Assert.isTrue(metadataUserService.getMetadata(id).getVersion().equals("v0"), "correct metadata");
    }

    @Test
    public void metadataGenerationTest() {
        int id = insertTestUser();
        Assert.isTrue(metadataUserService.getMetadata(id).getVersion().equals(metadataManagerService.generateMetadata().getVersion()), "correct new metadata");
    }

    @Test
    public void testGetUserWithoutMetadata() {
        int id = userService.createOrComplementUser(UserDTO.builder().email("ddd@test.com").build()).getId();
        Assert.isTrue(metadataUserService.loadUser(id).getMetadata().equals(metadataManagerService.generateMetadata()), "metadata inserted");
    }

}
