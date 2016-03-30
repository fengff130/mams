package fzid.wbf.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class Answer {
	private int status;
	private String message;
	
	public Answer(){
		
	}
	public Answer(int istatus,String msg){
		status=istatus;
		message=msg;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString(){
		return "Answer [status="+status+",message="+message+"]";
	}
}
