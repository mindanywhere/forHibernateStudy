package toni.liao.forHibernateStudy.test;

import java.util.Date;
import java.util.List;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import toni.liao.forHibernateStudy.pojo.Department;
import toni.liao.forHibernateStudy.pojo.Example;
import toni.liao.forHibernateStudy.pojo.Role;
import toni.liao.forHibernateStudy.pojo.User;
import toni.liao.forHibernateStudy.repository.DepartmentRepository;
import toni.liao.forHibernateStudy.repository.ExampleRepository;
import toni.liao.forHibernateStudy.repository.RoleRepository;
import toni.liao.forHibernateStudy.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlTest {
	private static Logger logger = LoggerFactory.getLogger(MysqlTest.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ExampleRepository eRepository;
	
	@Before
	public void initData() {
		System.out.println("测试函数执行前触发");
		userRepository.deleteAll();
		roleRepository.deleteAll();
		departmentRepository.deleteAll();
		Department department = new Department();
		department.setName("开发部");
		departmentRepository.save(department);
		Assert.assertNotNull(department.getId());
		Role role = new Role();
		role.setName("管理员");
		roleRepository.save(role);
		Assert.assertNotNull(role.getId());
		User user = new User();
		user.setName("用户A");
		user.setCreateDate(new Date());
		user.setDepartment(department);
		List<Role> roles = roleRepository.findAll();
		Assert.assertNotNull(roles);
		user.setRoles(roles);
		userRepository.save(user);
		Assert.assertNotNull(user.getId());
		
		eRepository.deleteAll();
		Example ex=new Example();
		ex.setAdress("贵州贵阳");
		ex.setName("我的家乡");
		eRepository.save(ex);
		ex=eRepository.findByAdress("贵州贵阳");
		System.out.println("实验自己建立的实体类，和JPA函数是否有效："+ex.getName());
		List<Example> exl=eRepository.findSQL("我的家乡");
		for(Example ex_unit:exl)
			System.out.println("实验自己建立的实体类，和原生SQL语句执行的函数是否有效："+ex_unit.getAdress());
		eRepository.doNothing();
	}

	@Test
	public void mytest1() {
		System.out.println("测试根据名字寻找：开始");
		String name = "用户A";
		User user = userRepository.findByName(name);
		Assert.assertNotNull(user);
		logger.info(user.getName());
		System.out.println("lala");
		
	}

	@Test
	public void findPage() {
		System.out.println("kaka");
		// Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC);
		Pageable pageable = PageRequest.of(0, 10);
		Page<User> page = userRepository.findAll(pageable);
		
		Assert.assertNotNull(page);
		for (User user : page.getContent()) {
			logger.info("====user==== user name:{}, department name:{}, role name:{}", user.getName(),
					user.getDepartment().getName(), user.getRoles().get(0).getName());
		}
		System.out.println("yaya");
	}
}