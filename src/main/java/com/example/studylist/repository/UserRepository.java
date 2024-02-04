package com.example.studylist.repository;

import com.example.studylist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRoleId(Long roleId);
    User findUserByLogin(String username);

    @Query("SELECT u FROM User u WHERE u.group.id = :groupId AND u.role.id = 1")
    List<User> findStudentsByGroupId(@Param("groupId") Long groupId);

    @Query("SELECT u FROM User u WHERE u.group.id = :groupId AND u.subject.id = :subjectId AND u.role.id = 2")
    Optional<User> findTeacherByGroupAndSubjectId(@Param("groupId") Long groupId, @Param("subjectId") Long subjectId);


    @Query("SELECT u FROM User u WHERE u.group.id = :groupId AND u.subject.id = :subjectId AND u.role.id = 1")
    List<User> findStudentsByGroupAndSubjectId(@Param("groupId") Long groupId, @Param("subjectId") Long subjectId);

}
