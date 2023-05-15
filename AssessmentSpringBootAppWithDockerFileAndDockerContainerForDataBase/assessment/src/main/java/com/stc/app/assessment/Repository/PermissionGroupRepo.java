package com.stc.app.assessment.Repository;

import com.stc.app.assessment.model.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionGroupRepo extends JpaRepository<PermissionGroup,Long> {
    PermissionGroup findByName(String name);
}
