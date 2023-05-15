package com.stc.app.assessment.Repository;

import com.stc.app.assessment.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionInfoRepo extends JpaRepository<Permission,Long> {
}
