package com.stc.app.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stc.app.assessment.model.enums.PermissionLevel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Objects;


@Entity
@Table(name="permission")
public class Permission {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "user_email")
    @JsonProperty("email")
    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonProperty("groupId")
    private PermissionGroup groupId;

    @Enumerated(EnumType.STRING)
    @Column(name = "permission_level")
    @JsonProperty("permissionLevel")
    private PermissionLevel permissionLevel;


    public Permission(String email, PermissionGroup groupId, PermissionLevel permissionLevel) {
        this.email = email;
        this.groupId = groupId;
        this.permissionLevel = permissionLevel;
    }

    public Long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PermissionGroup getGroupId() {
        return groupId;
    }

    public void setGroupId(PermissionGroup groupId) {
        this.groupId = groupId;
    }

    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(PermissionLevel permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", groupId=" + groupId +
                ", permissionLevel=" + permissionLevel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(groupId, that.groupId) && permissionLevel == that.permissionLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, groupId, permissionLevel);
    }
}
