package fzid.wbf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fzid.wbf.pojo.HarvesterInfo;
import fzid.wbf.repository.HarvesterMapper;
import fzid.wbf.service.HarvesterService;
@Service
public class HarvesterServiceImpl implements HarvesterService {

	@Override
	public List<HarvesterInfo> find() {
		// TODO Auto-generated method stub
		return hvrMpr.find();
	}
	@Autowired
	private HarvesterMapper hvrMpr;
}
