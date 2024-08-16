package vn.com.gsoft.system.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.gsoft.system.entity.Regions;
import vn.com.gsoft.system.model.dto.RegionsReq;
import vn.com.gsoft.system.repository.RegionsRepository;
import vn.com.gsoft.system.service.RegionsService;


@Service
@Log4j2
public class RegionsServiceImpl extends BaseServiceImpl<Regions, RegionsReq,Long> implements RegionsService {

	private RegionsRepository hdrRepo;
	@Autowired
	public RegionsServiceImpl(RegionsRepository hdrRepo) {
		super(hdrRepo);
		this.hdrRepo = hdrRepo;
	}

}
