package fzid.wbf.pojo;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MobileLogin {
	@Null(message="User.param.userName.isNull")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]*$",
		      message = "{User.param.userName.illegal}")
	@Size(max = 20,message = "{User.param.userName.outOfLength}")
	private String userName;
	private String pwd;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString(){
		return "userName="+userName+"\r\n"+
				"password="+pwd+"\r\n";
	}
}
