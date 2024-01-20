package org.shlimtech.typesevenm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.shlimtech.typesevenm.metadata.Metadata;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "type7metadata")
public class Type7Metadata {

    @Id
    @Column(name = "user_id")
    private int userId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "metadata")
    private Metadata metadata;

    public Type7Metadata(int userId, Metadata metadata) {
        this.userId = userId;
        this.metadata = metadata;
    }

}
