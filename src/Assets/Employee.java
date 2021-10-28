package Assets;

public class Employee {
	private String id, pwd;
	
	public Employee(String id, String pwd) {
		this.setId(id);
		this.setPwd(pwd);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
