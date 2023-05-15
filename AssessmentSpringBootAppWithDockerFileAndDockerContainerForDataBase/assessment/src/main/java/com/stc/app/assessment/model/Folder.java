package com.stc.app.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="folder")
public class Folder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL)
    @JsonProperty("files")
    @Column(name = "files")
    private List<File> files;

    @ManyToOne
    @JoinColumn(name = "space_id")
    @JsonProperty("space")
    private Space space;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    @JsonProperty("itemId")
    private Item itemId;

    public Folder(Item itemId) {
        this.itemId = itemId;
    }

    public Folder(List<File> files, Item itemId) {
        this.files = files;
        this.itemId = itemId;
    }

    public Folder() {}

    public Long getId() {
        return id;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", files=" + files +
                ", space=" + space +
                ", itemId=" + itemId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder = (Folder) o;
        return Objects.equals(id, folder.id) && Objects.equals(files, folder.files) && Objects.equals(space, folder.space) && Objects.equals(itemId, folder.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, files, space, itemId);
    }
}
