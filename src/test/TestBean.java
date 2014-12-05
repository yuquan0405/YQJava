package test;

import com.framework.annotation.Column;
import com.framework.annotation.GenerationType;
import com.framework.annotation.Id;
import com.framework.annotation.Table;
 
@Table(name="TestBean")
public class TestBean extends TestParentBean{
	
	/**
	 * 
	 */
	@Id(type=GenerationType.UUID,sequence="seq_test")
	@Column
	private String uid="";	
	
	@Column(name="uname")
	private String uname;
	
	
	private String pwd;
	
	@Column
	private Integer age;
	
	private Integer age2;
	
	private boolean enable;
	
	private String testDemo;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getAge2() {
		return age2;
	}
	public void setAge2(Integer age2) {
		this.age2 = age2;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getTestDemo() {
		return testDemo;
	}
	public void setTestDemo(String testDemo) {
		this.testDemo = testDemo;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	

}
