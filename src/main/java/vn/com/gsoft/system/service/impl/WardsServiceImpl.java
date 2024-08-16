package vn.com.gsoft.system.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.gsoft.system.entity.Wards;
import vn.com.gsoft.system.model.dto.WardsReq;
import vn.com.gsoft.system.repository.WardsRepository;
import vn.com.gsoft.system.service.WardsService;


@Service
@Log4j2
public class WardsServiceImpl extends BaseServiceImpl<Wards, WardsReq,Long> implements WardsService {

	private WardsRepository hdrRepo;
	@Autowired
	public WardsServiceImpl(WardsRepository hdrRepo) {
		super(hdrRepo);
		this.hdrRepo = hdrRepo;
	}

}
