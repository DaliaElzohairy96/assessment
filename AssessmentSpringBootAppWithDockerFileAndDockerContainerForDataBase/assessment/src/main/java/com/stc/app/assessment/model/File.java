package com.stc.app.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;


@Entity
@Table(name="file")
public class File {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "binary")
    @Basic(fetch = FetchType.LAZY)
    @JsonProperty("binary")
    @NotNull
    private byte[] binary;

    @ManyToOne
    @JoinColumn(name = "space_id")
    @JsonProperty("space")
    private Space space;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    @JsonProperty("folder")
    private Folder folder;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    @JsonProperty("itemId")
    private Item itemId;


    public File(@NotNull byte[] binary, Item itemId) {
        this.binary = binary;
        this.itemId = itemId;
    }

    public Long getId() {
        return id;
    }


    public byte[] getBinary() {
        return binary;
    }

    public void setBinary(byte[] binary) {
        this.binary = binary;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", binary=" + Arrays.toString(binary) +
                ", space=" + space +
                ", folder=" + folder +
                ", itemId=" + itemId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(id, file.id) && Arrays.equals(binary, file.binary) && Objects.equals(space, file.space) && Objects.equals(folder, file.folder) && Objects.equals(itemId, file.itemId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, space, folder, itemId);
        result = 31 * result + Arrays.hashCode(binary);
        return result;
    }
}
