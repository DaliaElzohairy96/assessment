package com.stc.app.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="permissionGroup")
public class PermissionGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name", unique = true)
    @JsonProperty("groupName")
    @NotNull
    private String groupName;

    public PermissionGroup() {}

    public PermissionGroup(String groupName) {
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "PermissionGroup{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionGroup that = (PermissionGroup) o;
        return Objects.equals(id, that.id) && Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName);
    }
}
