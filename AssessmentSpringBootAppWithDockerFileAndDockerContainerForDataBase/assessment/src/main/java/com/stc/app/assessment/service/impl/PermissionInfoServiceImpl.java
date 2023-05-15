package com.stc.app.assessment.service.impl;


import com.stc.app.assessment.Repository.PermissionInfoRepo;
import com.stc.app.assessment.model.Permission;
import com.stc.app.assessment.model.PermissionGroup;
import com.stc.app.assessment.model.enums.PermissionLevel;
import com.stc.app.assessment.service.PermissionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.Optional;

@Service
public class PermissionInfoServiceImpl implements PermissionInfoService {

    @Autowired
    PermissionInfoRepo permissionInfoRepo;

        public Permission createUser(String email, PermissionGroup groupId, PermissionLevel permissionLevel){
            Permission permission = new Permission(email,groupId,permissionLevel);
            permissionInfoRepo.saveAndFlush(permission);
            return permission;
        }

        public Permission getUser(Long id){
            Optional<Permission> permissionById = permissionInfoRepo.findById(id);
            if(permissionById.isEmpty())
                throw new NotFoundException("no user with id" + id);
            return permissionById.get();
        }

        public Boolean checkIfUserHasPermissionToEdit(Long id){
            Permission permissionById = getUser(id);
            return permissionById.getPermissionLevel().equals(PermissionLevel.EDIT);
        }


        public Boolean checkIfUserHasPermissionToView(Long id){
            Permission permissionById = getUser(id);
            return permissionById.getPermissionLevel().equals(PermissionLevel.VIEW);
        }


        public Boolean checkIfUserHasPermissionBlocked(Long id){
            Permission permissionById = getUser(id);
            return permissionById.getPermissionLevel().equals(PermissionLevel.BLOCKED);
        }

        public Boolean blockTheUser(Long userEditAccessId , Long userToBeBlockedId){
            try {
                if (checkIfUserHasPermissionToEdit(userEditAccessId)){
                    if(checkIfUserHasPermissionToView(userToBeBlockedId)) {
                        Permission viewUserToBeBlocked = getUser(userToBeBlockedId);
                        viewUserToBeBlocked.setPermissionLevel(PermissionLevel.BLOCKED);
                        permissionInfoRepo.saveAndFlush(viewUserToBeBlocked);
                        return true;
                    }
                    else if (checkIfUserHasPermissionBlocked(userToBeBlockedId)){
                        return false;
                }
            }
                throw new BadRequestException("you don't have an edit access");
            }catch (Exception e){
                throw new BadRequestException("failed to block the user");
            }
        }
}
