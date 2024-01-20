package org.shlimtech.typesevenm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shlimtech.typesixdatabasecommon.dto.UserDTO;
import org.shlimtech.typesevenm.metadata.MetadataEntrySet;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetadataDTO {

    private String version;
    private List<MetadataEntrySet> metadataEntrySets;
    private List<UserDTO> selectedUsers;

}