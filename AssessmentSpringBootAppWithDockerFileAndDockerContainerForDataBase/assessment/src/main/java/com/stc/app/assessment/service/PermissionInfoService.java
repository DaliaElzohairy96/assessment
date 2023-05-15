package com.stc.app.assessment.service;

import com.stc.app.assessment.model.Permission;
import com.stc.app.assessment.model.PermissionGroup;
import com.stc.app.assessment.model.enums.PermissionLevel;

public interface PermissionInfoService {
    Permission createUser(String email, PermissionGroup groupId, PermissionLevel permissionLevel);
    Permission getUser(Long id);
    Boolean checkIfUserHasPermissionToEdit(Long id);
    Boolean checkIfUserHasPermissionToView(Long id);
    Boolean checkIfUserHasPermissionBlocked(Long id);
    Boolean blockTheUser(Long userEditAccessId , Long userToBeBlockedId);
}
