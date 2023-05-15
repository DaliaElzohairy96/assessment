package com.stc.app.assessment.controller;

import com.stc.app.assessment.model.Folder;
import com.stc.app.assessment.model.Space;
import com.stc.app.assessment.service.FolderService;
import com.stc.app.assessment.service.SpaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/stc/system/folder")
public class FolderController {

    @Autowired
    FolderService folderService;

    @RequestMapping(
            method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    public Folder addFolder(
            @RequestBody Folder folder,
            @RequestParam(name = "folderName") String folderName,
            @RequestParam(name = "spaceName") String spaceName,
            @RequestParam(name = "permissionGroupName", required = false) String groupName){

        return this.folderService.addFolder(folder, folderName,spaceName, groupName);
    }
}
