package vn.com.gsoft.system.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.gsoft.system.entity.Entity;
import vn.com.gsoft.system.entity.NhaThuocs;
import vn.com.gsoft.system.model.dto.EntityReq;
import vn.com.gsoft.system.model.system.Profile;
import vn.com.gsoft.system.repository.EntityRepository;
import vn.com.gsoft.system.repository.NhaThuocsRepository;
import vn.com.gsoft.system.repository.PrivilegeEntityRepository;
import vn.com.gsoft.system.service.EntityService;

import java.util.List;


@Service
@Log4j2
public class EntityServiceImpl extends BaseServiceImpl<Entity, EntityReq, Long> implements EntityService {

    @Autowired
    private EntityRepository hdrRepo;

    @Autowired
    public EntityServiceImpl(EntityRepository hdrRepo) {
        super(hdrRepo);
        this.hdrRepo = hdrRepo;
    }
}
