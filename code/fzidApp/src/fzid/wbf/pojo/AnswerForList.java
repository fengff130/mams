package fzid.wbf.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
/**
 * 
 * @author Administrator
 *
 * @param <T>
 */
@JsonInclude(Include.NON_NULL)
public class AnswerForList<T> extends Answer {
	private List<T> beanList;
	public AnswerForList(List<T> beanlist){
		super(0,"请求成功");
		beanList=beanlist;
	}
	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
}
