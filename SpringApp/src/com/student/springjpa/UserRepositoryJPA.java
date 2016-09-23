package com.student.springjpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.student.entity.UserEntity;

@Repository
public interface UserRepositoryJPA extends JpaRepository<UserEntity, Long>{
	
	public UserEntity findById(Long id);
	
	
	public List<UserEntity> findByStudentNameOrStudentHobby(String name,String hobby);
	
	@Query("select p from UserEntity p where p.studentHobby = :hobby")
	public List<UserEntity> findByHobby(@Param("hobby") String hobby);
}
