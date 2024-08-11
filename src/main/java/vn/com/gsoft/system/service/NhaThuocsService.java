package vn.com.gsoft.system.service;

import org.springframework.data.domain.Page;
import vn.com.gsoft.system.entity.Entity;
import vn.com.gsoft.system.entity.LichSuCapNhatThanhVien;
import vn.com.gsoft.system.entity.NhaThuocs;
import vn.com.gsoft.system.model.dto.*;

import java.util.List;
import java.util.Optional;

public interface NhaThuocsService extends BaseService<NhaThuocs, NhaThuocsReq, Long> {

    List<Entity> searchListEntity(EntityReq rq) throws Exception;

    List<LichSuCapNhatThanhVien> searchLichSuCapNhatThanhVien(String maThanhVien) throws Exception;

    Boolean deleteByMaNhaThuoc(NhaThuocsReq req) throws Exception;
}