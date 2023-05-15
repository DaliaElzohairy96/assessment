package com.stc.app.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "space")
public class Space {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL)
    @Column(name="files", unique = true)
    @JsonProperty("files")
    private List<File> files;

    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL)
    @Column(name="folders", unique = true)
    @JsonProperty("folders")
    private List<Folder> folders;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    @JsonProperty("itemId")
    private Item itemId;

    public Space() {}

    public Space(List<File> files, List<Folder> folders, Item itemId) {
        this.files = files;
        this.folders = folders;
        this.itemId = itemId;
    }

    public Space(List<File> files, Item itemId) {
        this.files = files;
        this.itemId = itemId;
    }

    public Space(Item itemId, List<Folder> folders) {
        this.folders = folders;
        this.itemId = itemId;
    }

    public Space(Item itemId) {
        this.itemId = itemId;
    }

    public Long getId() {
        return id;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "Space{" +
                "id=" + id +
                ", files=" + files +
                ", folders=" + folders +
                ", itemId=" + itemId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Space space = (Space) o;
        return Objects.equals(id, space.id) && Objects.equals(files, space.files) && Objects.equals(folders, space.folders) && Objects.equals(itemId, space.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, files, folders, itemId);
    }
}
