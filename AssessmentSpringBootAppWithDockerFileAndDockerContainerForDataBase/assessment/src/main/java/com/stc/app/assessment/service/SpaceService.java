package com.stc.app.assessment.service;

import com.stc.app.assessment.model.Space;

public interface SpaceService {
    Space addSpace(Space space, String spaceName, String groupName);
}
