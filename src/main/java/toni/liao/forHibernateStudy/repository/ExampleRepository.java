package toni.liao.forHibernateStudy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import toni.liao.forHibernateStudy.pojo.Example;
import toni.liao.forHibernateStudy.pojo.User;
@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
	Example findByName(String name);
	Example findByAdress(String adress);
	
	//自定义原生SQL语句执行的方法的例子
	@Query(nativeQuery = true, value = "SELECT * FROM Example WHERE name = :name1")
    List<Example> findSQL(@Param("name1") String name1);
	
	default void doNothing() {
		System.out.println("用default声明的方法，有实体执行能力");
	}
}