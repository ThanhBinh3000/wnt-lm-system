package vn.com.gsoft.system.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.gsoft.system.constant.StatusConstant;
import vn.com.gsoft.system.entity.LichSuCapNhatThanhVien;
import vn.com.gsoft.system.model.dto.LichSuCapNhatThanhVienReq;
import vn.com.gsoft.system.repository.LichSuCapNhatThanhVienRepository;
import vn.com.gsoft.system.service.LichSuCapNhatThanhVienService;

import java.util.List;


@Service
@Log4j2
public class LichSuCapNhatThanhVienServiceImpl extends BaseServiceImpl<LichSuCapNhatThanhVien, LichSuCapNhatThanhVienReq, Long> implements LichSuCapNhatThanhVienService {

    private LichSuCapNhatThanhVienRepository hdrRepo;

    @Autowired
    public LichSuCapNhatThanhVienServiceImpl(LichSuCapNhatThanhVienRepository hdrRepo) {
        super(hdrRepo);
        this.hdrRepo = hdrRepo;
    }

    @Override
    public List<LichSuCapNhatThanhVien> searchList(LichSuCapNhatThanhVienReq req) throws Exception {
        var list = hdrRepo.findLichSuCapNhatThanhVienByMaThanhVien(req.getMaThanhVien());
        if(!list.stream().isParallel()){
            list.forEach(item->{
                switch (item.getStatusId()){
                    case StatusConstant.ADD -> {
                        item.setStatusName("Tạo mới");
                        break;
                    }
                    case StatusConstant.UPDATE -> {
                        item.setStatusName("Cập nhật");
                        break;
                    }
                    case StatusConstant.DELETE -> {
                        item.setStatusName("Xóa khỏi thành viên");
                        break;
                    }
                }
            });
        }
        return list;
    }
}
