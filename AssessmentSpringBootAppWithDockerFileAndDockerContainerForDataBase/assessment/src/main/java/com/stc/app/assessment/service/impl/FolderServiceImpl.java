package com.stc.app.assessment.service.impl;

import com.stc.app.assessment.Repository.FolderRepo;
import com.stc.app.assessment.Repository.ItemRepo;
import com.stc.app.assessment.Repository.SpaceRepo;
import com.stc.app.assessment.model.*;
import com.stc.app.assessment.model.enums.Type;
import com.stc.app.assessment.service.FolderService;
import com.stc.app.assessment.service.PermissionGroupService;
import com.stc.app.assessment.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    ItemRepo itemRepo;
    @Autowired
    SpaceRepo spaceRepo;
    @Autowired
    SpaceService spaceService;
    @Autowired
    FolderRepo folderRepo;
    @Autowired
    PermissionGroupService permissionGroupService;


    @Override
    public Folder addFolder(Folder folder, String folderName,String spaceName, String groupName) {
            PermissionGroup permissionGroup = permissionGroupService.addPermissionGroup(groupName);
            if(ObjectUtils.isEmpty(folder.getItemId())) {
                if(StringUtils.isEmpty(folderName))
                    throw new BadRequestException("No value for the folder name");
                Item folderItem = new Item(folderName, Type.FOLDER, permissionGroup);
                folder.setItemId(folderItem);
                folderRepo.saveAndFlush(folder);
            }

            if(!StringUtils.isEmpty(spaceName)) {
                try {
                    List<Item> itemList = itemRepo.findAll();
                    Optional<Item> item = itemList.stream().filter(item1 -> item1.getName().equals(spaceName) && item1.getType().equals(Type.SPACE)).findFirst();
                    if (item.isPresent()) {
                        Item existedItem = item.get();
                        Long existedItemId = existedItem.getId();
                        Space existedSpace = spaceRepo.findAll().stream().filter(space1 -> space1.getItemId().getId().equals(existedItemId)).findFirst().get();
                        List<Folder> folders = existedSpace.getFolders();
                        if(folders.stream().anyMatch(folder1 -> folder1.getItemId().getName().equals(folder.getItemId().getName())))
                            throw new BadRequestException("folder with that name already under the same space change the name of folder");
                        folders.add(folder);
                        existedSpace.setFolders(folders);
                        spaceRepo.saveAndFlush(existedSpace);
                        return folder;
                    }
                    Space addedSpace = spaceService.addSpace(new Space(), spaceName, groupName);
                    addedSpace.setFolders(List.of(folder));
                    spaceRepo.saveAndFlush(addedSpace);
                    return folder;
                } catch (Exception e){
                    throw new BadRequestException("The folder couldn't be saved successfully"+ e);
                }
            }throw new BadRequestException("No value for the space name");
    }

    @Override
    public List<Folder> getFolderByName(String name) {
       List<Folder> byName = new ArrayList<>();
        try {
             byName = folderRepo.findAllByName(name);
        }catch (Exception e){
            throw new NotFoundException("couldn't find folder with that name"+ e);
        }
        return byName;
    }
}
