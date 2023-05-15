package com.stc.app.assessment.service.impl;


import com.stc.app.assessment.Repository.PermissionGroupRepo;
import com.stc.app.assessment.Repository.SpaceRepo;
import com.stc.app.assessment.model.Item;
import com.stc.app.assessment.model.PermissionGroup;
import com.stc.app.assessment.model.Space;
import com.stc.app.assessment.model.enums.Type;
import com.stc.app.assessment.service.PermissionGroupService;
import com.stc.app.assessment.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;


@Service
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    SpaceRepo spaceRepo;
    @Autowired
    PermissionGroupService permissionGroupService;
    @Override
    public Space addSpace(Space space, String spaceName, String groupName) {
        try{
            PermissionGroup permissionGroup = permissionGroupService.addPermissionGroup(groupName);

            Item item = new Item();
            if(!StringUtils.isEmpty(spaceName)) {
                item.setName(spaceName);
            }
            if(!ObjectUtils.isEmpty(permissionGroup)){
                    item.setPermissionGroupId(permissionGroup);
            }
            item.setType(Type.SPACE);
            space.setItemId(item);
            spaceRepo.saveAndFlush(space);

        }catch (Exception e){
            throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity(e).build());
        }
        return space;
    }
}
