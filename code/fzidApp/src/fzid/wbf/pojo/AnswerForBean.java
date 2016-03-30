package fzid.wbf.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Administrator
 *
 * @param <T>
 */
@JsonInclude(Include.NON_NULL)
public class AnswerForBean<T> extends Answer{
	
	public AnswerForBean(T bean){
		super(0, "请求成功");
		this.bean=bean;
	}
	public AnswerForBean(int istatus, String msg,T bean) {
		super(istatus, msg);
		// TODO Auto-generated constructor stub
		this.bean=bean;
	}

	private T bean;

	public T getBean() {
		return bean;
	}

	public void setBean(T bean) {
		this.bean = bean;
	}
}
