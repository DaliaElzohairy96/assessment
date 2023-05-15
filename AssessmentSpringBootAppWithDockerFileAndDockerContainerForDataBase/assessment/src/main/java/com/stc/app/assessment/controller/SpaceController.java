package com.stc.app.assessment.controller;

import com.stc.app.assessment.model.Space;
import com.stc.app.assessment.service.SpaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/stc/system/space")
public class SpaceController {

    @Autowired
    SpaceService spaceService;

    @RequestMapping(
            method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    public Space addSpace(
            @RequestBody Space space,
            @RequestParam(name = "spaceName", required = false) String spaceName,
            @RequestParam(name = "permissionGroupName", required = false) String groupName){

        return this.spaceService.addSpace(space, spaceName,groupName);
    }



}
