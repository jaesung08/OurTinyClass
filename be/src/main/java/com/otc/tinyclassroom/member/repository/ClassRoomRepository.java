package com.otc.tinyclassroom.member.repository;

import com.otc.tinyclassroom.member.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 교실 repository.
 */
@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {

}