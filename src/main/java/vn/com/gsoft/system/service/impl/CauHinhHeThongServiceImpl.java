package vn.com.gsoft.system.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.com.gsoft.system.entity.CauHinhHeThong;
import vn.com.gsoft.system.entity.Entity;
import vn.com.gsoft.system.entity.NhaThuocs;
import vn.com.gsoft.system.model.dto.CauHinhHeThongReq;
import vn.com.gsoft.system.model.dto.EntityReq;
import vn.com.gsoft.system.model.system.Profile;
import vn.com.gsoft.system.repository.CauHinhHeThongRepository;
import vn.com.gsoft.system.repository.EntityRepository;
import vn.com.gsoft.system.service.CauHinhHeThongService;
import vn.com.gsoft.system.service.EntityService;

import java.util.List;
import java.util.Optional;


@Service
@Log4j2
public class CauHinhHeThongServiceImpl extends BaseServiceImpl<CauHinhHeThong, CauHinhHeThongReq, Long> implements CauHinhHeThongService {

    @Autowired
    private CauHinhHeThongRepository hdrRepo;

    @Autowired
    public CauHinhHeThongServiceImpl(CauHinhHeThongRepository hdrRepo) {
        super(hdrRepo);
        this.hdrRepo = hdrRepo;
    }
    @Override
    public List<CauHinhHeThong> searchList(CauHinhHeThongReq req) throws Exception{
        Profile userInfo = this.getLoggedUser();
        if (userInfo == null)
            throw new Exception("Bad request.");
        if (!userInfo.getIsAdmin())
            throw new Exception("Bad request.");
        return hdrRepo.searchList(req);
    }

    @Override
    public Page<CauHinhHeThong> searchPage(CauHinhHeThongReq req) throws Exception{
        Profile userInfo = this.getLoggedUser();
        if (userInfo == null)
            throw new Exception("Bad request.");
        if (!userInfo.getIsAdmin())
            throw new Exception("Bad request.");
        Pageable pageable = PageRequest.of(req.getPaggingReq().getPage(), req.getPaggingReq().getLimit());
        return hdrRepo.searchPage(req, pageable);
    }

    @Override
    public CauHinhHeThong update(CauHinhHeThongReq req) throws Exception{
        Profile userInfo = this.getLoggedUser();
        if (userInfo == null)
            throw new Exception("Bad request.");
        if (!userInfo.getIsAdmin())
            throw new Exception("Bad request.");
        Optional<CauHinhHeThong> item = hdrRepo.findById(req.getId());
        if(item.isPresent()){
            item.get().setValue(req.getValue());
            item.get().setGhiChu(req.getGhiChu());
            hdrRepo.save(item.get());
        }
        return item.get();
    }
}
