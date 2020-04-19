package toni.liao.forHibernateStudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import toni.liao.forHibernateStudy.pojo.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}