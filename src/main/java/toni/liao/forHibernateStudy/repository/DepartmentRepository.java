package toni.liao.forHibernateStudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import toni.liao.forHibernateStudy.pojo.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}