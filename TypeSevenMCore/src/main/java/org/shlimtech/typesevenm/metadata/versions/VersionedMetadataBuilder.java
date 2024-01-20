package org.shlimtech.typesevenm.metadata.versions;

import org.shlimtech.typesevenm.metadata.Metadata;

public interface VersionedMetadataBuilder {

    Metadata createNewMetadata();
    String getVersion();
    String getParentVersion();
    Metadata upgradeFromParentVersion(Metadata metadata);

}
