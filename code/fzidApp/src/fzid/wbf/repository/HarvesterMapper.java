package fzid.wbf.repository;

import java.util.List;

import fzid.wbf.pojo.HarvesterInfo;

public interface HarvesterMapper {
	/**
	 * 查询采集器 根据组织id
	 * @param orgid
	 * @return
	 */
	public List<HarvesterInfo> find();
}
