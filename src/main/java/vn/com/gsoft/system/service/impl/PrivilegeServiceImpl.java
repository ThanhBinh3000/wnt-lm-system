package vn.com.gsoft.system.service.impl;

import vn.com.gsoft.system.entity.*;
import vn.com.gsoft.system.model.dto.EntityReq;
import vn.com.gsoft.system.model.dto.PrivilegeReq;
import vn.com.gsoft.system.model.system.Profile;
import vn.com.gsoft.system.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.gsoft.system.service.PrivilegeService;

import java.util.List;
import java.util.Objects;


@Service
@Log4j2
public class PrivilegeServiceImpl extends BaseServiceImpl<Privilege, PrivilegeReq, Long> implements PrivilegeService {

    @Autowired
    private PrivilegeRepository hdrRepo;
    @Autowired
    private PrivilegeEntityRepository privilegeEntityRepository;
    @Autowired
    private NhaThuocsRepository nhaThuocsRepo;

    @Autowired
    public PrivilegeServiceImpl(PrivilegeRepository hdrRepo,
                                PrivilegeEntityRepository privilegeEntityRepository,
                                NhaThuocsRepository nhaThuocsRepo) {
        super(hdrRepo);
        this.hdrRepo = hdrRepo;
        this.privilegeEntityRepository = privilegeEntityRepository;
        this.nhaThuocsRepo = nhaThuocsRepo;

    }

    @Override
    public List<Privilege> searchListRoleByMaThanhVien(PrivilegeReq req ) throws Exception {
        Profile userInfo = this.getLoggedUser();
        if (userInfo == null)
            throw new Exception("Bad request.");
        var lst = hdrRepo.searchList( req );
        var thanhVien = nhaThuocsRepo.findByMaNhaThuoc(req.getMaCoSo());
        var entityId = thanhVien.getEntityId();
        lst.forEach(x->{
            var role = privilegeEntityRepository.findByEntityIdAndPrivilegeId(Long.valueOf(entityId), x.getId());
            x.setActive(role.isPresent());
        });
        return lst;
    }

}
