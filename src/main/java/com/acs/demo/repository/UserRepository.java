package com.acs.demo.repository;

import com.acs.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByGmail(String gmail);

    @Query("select u from User u where u.strFirstName LIKE %:query% OR u.strLastName LIKE %:query% OR u.gmail LIKE %:query%")
    public List<User> searchUser(@Param("query") String query);
}
