package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgUser;
import com.crossent.monitoring.portal.jpa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {

    public User findByUuid(String uuid);

    public Page<User> findByNameLike(Pageable pageable, String name);
    public Page<User> findByIdLike(Pageable pageable, String id);
    public Page<User> findByEmailLike(Pageable pageable, String email);

    public void deleteByIdIn(String[] ids);

    //namelike 조회 후 mgUser안에 들어가지 않은 목록 넣기
    public Collection<User> findAllByNameLikeAndMgUsersNotIn(String name, Collection<MgUser> mgUsers);

}
