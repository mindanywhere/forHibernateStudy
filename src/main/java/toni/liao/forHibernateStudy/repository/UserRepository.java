package toni.liao.forHibernateStudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import toni.liao.forHibernateStudy.pojo.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
User findByName(String name);
}