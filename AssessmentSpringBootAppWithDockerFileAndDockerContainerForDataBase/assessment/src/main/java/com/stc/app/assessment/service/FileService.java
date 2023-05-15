package com.stc.app.assessment.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    void uploadFile(MultipartFile file, String spaceName, String folderName, String groupName, Long editUserId, Long blockUserId);
    void downloadFile(Long id, HttpServletResponse httpServletResponse);
}
