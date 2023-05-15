package com.stc.app.assessment.service.impl;

import com.stc.app.assessment.Repository.PermissionGroupRepo;
import com.stc.app.assessment.model.PermissionGroup;
import com.stc.app.assessment.service.PermissionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.ws.rs.BadRequestException;

@Service
public class PermissionGroupServiceImpl implements PermissionGroupService {
    @Autowired
    PermissionGroupRepo permissionGroupRepo;

   public PermissionGroup addPermissionGroup(String groupName){
       PermissionGroup permissionGroup = new PermissionGroup();
       try{
            if (!StringUtils.isEmpty(groupName)) {
                PermissionGroup permissionGroupByName = permissionGroupRepo.findByName(groupName);
                if (!ObjectUtils.isEmpty(permissionGroupByName)) {
                    permissionGroup = permissionGroupByName;
                } else {
                    permissionGroup.setGroupName(groupName);
                    permissionGroupRepo.saveAndFlush(permissionGroup);
                }
            }
       }catch (Exception e){
           throw new BadRequestException("Cannot find/save Space with that name" + e);
       }
        return permissionGroup;
    }
}
