package com.stc.app.assessment.Repository;

import com.stc.app.assessment.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item,Long> {
}
