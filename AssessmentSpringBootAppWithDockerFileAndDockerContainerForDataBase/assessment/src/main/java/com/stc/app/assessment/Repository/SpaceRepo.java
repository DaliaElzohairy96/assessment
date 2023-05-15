package com.stc.app.assessment.Repository;

import com.stc.app.assessment.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRepo extends JpaRepository<Space,Long> {

}
