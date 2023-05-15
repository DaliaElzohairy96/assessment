package com.stc.app.assessment.service.impl;

import com.stc.app.assessment.Repository.*;
import com.stc.app.assessment.model.File;
import com.stc.app.assessment.model.Folder;
import com.stc.app.assessment.model.Item;
import com.stc.app.assessment.model.PermissionGroup;
import com.stc.app.assessment.model.enums.Type;
import com.stc.app.assessment.service.FileService;
import com.stc.app.assessment.service.FolderService;
import com.stc.app.assessment.service.PermissionGroupService;
import com.stc.app.assessment.service.PermissionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepo fileRepo;
    @Autowired
    FolderRepo folderRepo;
    @Autowired
    FolderService folderService;
    @Autowired
    PermissionGroupService permissionGroupService;
    @Autowired
    PermissionInfoService permissionInfoService;


    @Override
    public void uploadFile(MultipartFile multipartFile, String spaceName, String folderName, String groupName, Long editUserId, Long blockedId) {
        try {
            if(editUserId!=null && permissionInfoService.checkIfUserHasPermissionToView(editUserId)){
                PermissionGroup permissionGroup = permissionGroupService.addPermissionGroup(groupName);
                Item fileItem = new Item(StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename())), Type.FILE, permissionGroup);
                File file = new File(multipartFile.getBytes(),fileItem);
                fileRepo.saveAndFlush(file);

                List<Folder> folderByName = folderService.getFolderByName(folderName);
                Optional<Folder> existedFolder = folderByName.stream().filter(folder -> folder.getSpace().getItemId().getName().equals(spaceName)).findFirst();
                if(existedFolder.isEmpty()){
                    throw new BadRequestException("no folder with that name "+ folderName + " under space name "+ spaceName);
                }
                Folder folder = existedFolder.get();
                List<File> files = folder.getFiles();
                files.add(file);
                folderRepo.saveAndFlush(folder);
                if(!ObjectUtils.isEmpty(blockedId)){
                    Boolean blockTheUser = permissionInfoService.blockTheUser(editUserId, blockedId);
                    if(!blockTheUser)
                        throw new BadRequestException("cannot block user with id "+ blockedId +" after uploading the file");
                }
            }
            throw new BadRequestException("your id don't have access to upload file"+ editUserId);
        } catch (Exception e) {
            throw new BadRequestException("Cannot save file in the folder successfully" + e);
        }
    }

    @Override
    public void downloadFile(Long id , HttpServletResponse httpServletResponse){
        Optional<File> fileById = fileRepo.findById(id);
        if(!fileById.isPresent()) {
            throw new NotFoundException("Couldn't find document with Id" + id);
        }try{
        File file = fileById.get();

        httpServletResponse.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + file.getItemId().getName();
        httpServletResponse.setHeader(headerKey,headerValue);

        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write(file.getBinary());
        outputStream.close();

        } catch (IOException e) {
            throw new RuntimeException("failed while downloading the file" + e);
        }
    }
}
