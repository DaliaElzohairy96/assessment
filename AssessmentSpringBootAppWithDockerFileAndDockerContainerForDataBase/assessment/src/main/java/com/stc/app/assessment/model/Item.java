package com.stc.app.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stc.app.assessment.model.enums.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="item" , uniqueConstraints = @UniqueConstraint(columnNames = {"name" , "type"}))
public class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @JsonProperty("type")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "permission_group_id")
    @JsonProperty("permissionGroupId")
    private PermissionGroup permissionGroupId;

    public Item() {
    }

    public Item(String name, Type type, PermissionGroup permissionGroupId) {
        this.name = name;
        this.type = type;
        this.permissionGroupId = permissionGroupId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public PermissionGroup getPermissionGroupId() {
        return permissionGroupId;
    }

    public void setPermissionGroupId(PermissionGroup permissionGroupId) {
        this.permissionGroupId = permissionGroupId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", permissionGroupId=" + permissionGroupId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(name, item.name) && type == item.type && Objects.equals(permissionGroupId, item.permissionGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, permissionGroupId);
    }
}
