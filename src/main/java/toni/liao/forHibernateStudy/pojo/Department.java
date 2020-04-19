package toni.liao.forHibernateStudy.pojo;
import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "department")
public class Department implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -3165868708465981108L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
public Long getId() {
return id;
}
public void setId(Long id) {
this.id = id;
}
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
}