package com.stc.app.assessment.service;

import com.stc.app.assessment.model.Folder;

import java.util.List;

public interface FolderService {
    Folder addFolder(Folder folder, String folderName, String spaceName, String groupName);
    List<Folder> getFolderByName(String name);
}
