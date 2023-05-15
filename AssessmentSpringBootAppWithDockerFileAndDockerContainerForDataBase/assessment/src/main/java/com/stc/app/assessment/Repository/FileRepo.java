package com.stc.app.assessment.Repository;

import com.stc.app.assessment.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends JpaRepository<File,Long> {
}
