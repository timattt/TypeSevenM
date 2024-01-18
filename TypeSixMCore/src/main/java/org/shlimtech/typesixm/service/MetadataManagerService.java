package org.shlimtech.typesixm.service;

import lombok.AllArgsConstructor;
import org.shlimtech.typesixdatabasecommon.metadata.Metadata;
import org.shlimtech.typesixm.metadata.versions.V1Metadata;
import org.shlimtech.typesixm.metadata.versions.VersionedMetadataBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MetadataManagerService {

    private List<VersionedMetadataBuilder> allVersions;
    private final V1Metadata latest;

    public Metadata generateMetadata() {
        return latest.createNewMetadata();
    }

    public boolean hasLatestVersion(Metadata metadata) {
        return metadata.getVersion().equals(latest.getVersion());
    }

    public VersionedMetadataBuilder findBuilder(String version) {
        return allVersions.stream().filter(builder -> builder.getVersion().equals(version)).findAny().get();
    }

    public Metadata update(Metadata metadata) {
        // TODO create version upgrade mechanics
        return metadata;
    }

}
