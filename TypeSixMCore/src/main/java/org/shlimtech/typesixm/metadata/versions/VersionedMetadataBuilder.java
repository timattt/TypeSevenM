package org.shlimtech.typesixm.metadata.versions;

import org.shlimtech.typesixm.metadata.Metadata;

public interface VersionedMetadataBuilder {

    Metadata createNewMetadata();
    String getVersion();
    String getParentVersion();
    Metadata upgradeFromParentVersion(Metadata metadata);

}
