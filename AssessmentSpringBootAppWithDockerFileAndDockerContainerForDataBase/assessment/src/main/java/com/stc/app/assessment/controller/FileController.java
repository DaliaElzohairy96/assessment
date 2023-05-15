package com.stc.app.assessment.controller;

import com.stc.app.assessment.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/stc/system/file")
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/uploadFile",
            produces = {"application/json;charset=utf-8"})
    public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file ,
        @RequestParam(name = "spaceName") String spaceName,
        @RequestParam(name = "folderName") String folderName,
        @RequestParam(name = "permissionGroupName", required = false) String groupName,
        @RequestParam(name = "editUserId") Long editUserId,
        @RequestParam(name = "blockUserId", required = false) Long blockedId)
    {
        fileService.uploadFile(file,spaceName,folderName, groupName, editUserId, blockedId);
        return ResponseEntity.ok().body(null);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/downloadFile")
    public ResponseEntity<Void> downloadFile(@RequestParam("id") Long id, HttpServletResponse httpServletResponse) {

        fileService.downloadFile(id , httpServletResponse);
        return ResponseEntity.ok().body(null);
    }
}
