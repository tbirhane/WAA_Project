package edu.mum.cs.onlinehabeshaclothing.repository;


import edu.mum.cs.onlinehabeshaclothing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
