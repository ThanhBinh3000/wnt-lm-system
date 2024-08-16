package vn.com.gsoft.system.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.gsoft.system.entity.Cities;
import vn.com.gsoft.system.model.dto.CitiesReq;
import vn.com.gsoft.system.repository.CitiesRepository;
import vn.com.gsoft.system.service.CitiesService;


@Service
@Log4j2
public class CitiesServiceImpl extends BaseServiceImpl<Cities, CitiesReq,Long> implements CitiesService {

	private CitiesRepository hdrRepo;
	@Autowired
	public CitiesServiceImpl(CitiesRepository hdrRepo) {
		super(hdrRepo);
		this.hdrRepo = hdrRepo;
	}

}
