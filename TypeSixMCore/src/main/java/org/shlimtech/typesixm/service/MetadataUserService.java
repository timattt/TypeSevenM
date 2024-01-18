package org.shlimtech.typesixm.service;

import org.modelmapper.ModelMapper;
import org.shlimtech.typesixdatabasecommon.dto.UserDTO;
import org.shlimtech.typesixdatabasecommon.metadata.Metadata;
import org.shlimtech.typesixdatabasecommon.repository.UserRepository;
import org.shlimtech.typesixdatabasecommon.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MetadataUserService extends UserService {

    private final MetadataManagerService metadataManagerService;

    public MetadataUserService(UserRepository userRepository, ModelMapper modelMapper, MetadataManagerService metadataManagerService) {
        super(userRepository, modelMapper);
        this.metadataManagerService = metadataManagerService;
    }

    @Transactional
    @Override
    public Metadata getMetadata(int userID) {
        UserDTO user = loadUser(userID);

        Metadata metadata = user.getMetadata();
        if (metadata == null) {
            metadata = metadataManagerService.generateMetadata();
            super.setMetadata(userID, metadata);
        }

        return metadata;
    }

    @Transactional
    @Override
    public void setMetadata(int userId, Metadata metadata) {
        Metadata toSet = metadata;
        if (!metadataManagerService.hasLatestVersion(toSet)) {
            toSet = metadataManagerService.update(toSet);
        }
        super.setMetadata(userId, toSet);
    }

}
