package vn.com.gsoft.system.service.impl;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.util.EncodingUtils;
import org.springframework.stereotype.Service;
import vn.com.gsoft.system.constant.RecordStatusContains;
import vn.com.gsoft.system.constant.StatusConstant;
import vn.com.gsoft.system.entity.*;
import vn.com.gsoft.system.model.dto.*;
import vn.com.gsoft.system.model.system.Profile;
import vn.com.gsoft.system.repository.*;
import vn.com.gsoft.system.service.NhaThuocsService;
import vn.com.gsoft.system.util.system.StoreHelper;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class NhaThuocsServiceImpl extends BaseServiceImpl<NhaThuocs, NhaThuocsReq, Long> implements NhaThuocsService {


    private NhaThuocsRepository hdrRepo;

    @Autowired
    private TinhThanhsRepository tinhThanhsRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private EntityRepository entityRepository;
    @Autowired
    private LichSuCapNhatThanhVienRepository lichSuCapNhatThanhVienRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public NhaThuocsServiceImpl(NhaThuocsRepository hdrRepo, UserProfileRepository userProfileRepository,
                                EntityRepository entityRepository, TinhThanhsRepository tinhThanhsRepository,
                                LichSuCapNhatThanhVienRepository lichSuCapNhatThanhVienRepository
                                ) {
        super(hdrRepo);
        this.hdrRepo = hdrRepo;
        this.userProfileRepository = userProfileRepository;
        this.entityRepository = entityRepository;
        this.tinhThanhsRepository = tinhThanhsRepository;
        this.lichSuCapNhatThanhVienRepository = lichSuCapNhatThanhVienRepository;
    }

    @Override
    public Page<NhaThuocs> searchPage(NhaThuocsReq req) throws Exception {
        Profile userInfo = this.getLoggedUser();
        if (userInfo == null)
            throw new Exception("Bad request.");
        Pageable pageable = PageRequest.of(req.getPaggingReq().getPage(), req.getPaggingReq().getLimit());
        Page<NhaThuocs> nhaThuocs = hdrRepo.searchPage(req, pageable);
        nhaThuocs.getContent().forEach(item->{
          Optional<Entity> entity = entityRepository.findById(item.getEntityId());
            entity.ifPresent(value -> item.setLevel(value.getName()));
        });
        return nhaThuocs;
    }

    @Override
    public NhaThuocs create(NhaThuocsReq req) throws Exception{
        Profile userInfo = this.getLoggedUser();
        if (userInfo == null)
            throw new Exception("Bad request.");
        if(req.getTenNhaThuoc() == null || req.getTenNhaThuoc().isEmpty()){
            throw new Exception("Tên thành viên không được null.");
        }
        if(req.getDiaChi() == null || req.getDiaChi().isEmpty()){
            throw new Exception("Địa chỉ thành viên không được null.");
        }
        if(req.getEntityId() == null){
            throw new Exception("Cấp thành viên không được null.");
        }
        if(req.getUserName() == null || req.getUserName().isEmpty()){
            throw new Exception("Tài khoản thành viên không được null.");
        }
        if(req.getPassword() == null || req.getPassword().isEmpty()){
            throw new Exception("Mật khẩu thành viên không được null.");
        }
        if(req.getDienThoai() == null || req.getDienThoai().isEmpty()){
            throw new Exception("Số điện thoại thành viên không được null.");
        }
        //kiểm tra xem tên tài khoản tv đã tồn tại chưa
        var user= userProfileRepository.findByUserNameAndHoatDong(req.getUserName(), true);
        if(user.isPresent()){
            throw new Exception("Tên tài khoản dùng để đăng nhập đã tồn tại.");
        }
        NhaThuocs nhaThuoc = new NhaThuocs();
        nhaThuoc.setMaNhaThuoc(getNewStoreCode());
        nhaThuoc.setCreated(new Date());
        nhaThuoc.setCreatedByUserId(userInfo.getId());
        nhaThuoc.setRecordStatusId(RecordStatusContains.ACTIVE);
        BeanUtils.copyProperties(req, nhaThuoc, "maNhaThuoc","created", "createdByUserId", "recordStatusId");
        hdrRepo.save(nhaThuoc);
        //lưu tài khoản
        UserProfile userProfile = new UserProfile();
        userProfile.setMaNhaThuoc(req.getMaNhaThuoc());
        userProfile.setUserName(req.getUserName());
        userProfile.setPassword(passwordEncoder.encode(req.getPassword()));
        userProfile.setTenDayDu(req.getTenNhaThuoc());
        userProfile.setCreated(new Date());
        userProfile.setCreatedByUserId(userInfo.getId());
        userProfile.setHoatDong(true);
        userProfile.setUserId(0L);
        userProfile.setCityId(req.getCityId() > 0 ? req.getCityId() : 0L);
        userProfile.setRegionId(req.getRegionId() > 0 ? req.getRegionId() : 0L);
        userProfile.setWardId(req.getRegionId() > 0 ? req.getRegionId() : 0L);
        userProfileRepository.save(userProfile);
        //lưu lịch sử
        LichSuCapNhatThanhVien lichSuCapNhatThanhVien = new LichSuCapNhatThanhVien();
        lichSuCapNhatThanhVien.setStatusId(StatusConstant.ADD);
        lichSuCapNhatThanhVien.setMaThanhVien(userProfile.getMaNhaThuoc());
        lichSuCapNhatThanhVien.setGhiChu(req.getDescription());
        lichSuCapNhatThanhVien.setNgayCapNhat(new Date());
        lichSuCapNhatThanhVienRepository.save(lichSuCapNhatThanhVien);
        return nhaThuoc;
    }
    @Override
    public NhaThuocs update(NhaThuocsReq req) throws Exception{
        Profile userInfo = this.getLoggedUser();
        if (userInfo == null)
            throw new Exception("Bad request.");
        if(req.getTenNhaThuoc() == null || req.getTenNhaThuoc().isEmpty()){
            throw new Exception("Tên thành viên không được null.");
        }
        if(req.getMaNhaThuoc() == null || req.getMaNhaThuoc().isEmpty()){
            throw new Exception("Mã thành viên không được null.");
        }
        if(req.getEntityId() == null){
            throw new Exception("Cấp thành viên không được null.");
        }

        NhaThuocs nhaThuoc = hdrRepo.findByMaNhaThuoc(req.getMaNhaThuoc());

        nhaThuoc.setTenNhaThuoc(req.getTenNhaThuoc());
        nhaThuoc.setEntityId(req.getEntityId());
        nhaThuoc.setEmail(req.getEmail());
        nhaThuoc.setDiaChi(req.getDiaChi());
        nhaThuoc.setCityId(req.getCityId() > 0 ? req.getCityId() : 0L);
        nhaThuoc.setRegionId(req.getRegionId() > 0 ? req.getRegionId() : 0L);
        nhaThuoc.setWardId(req.getRegionId() > 0 ? req.getRegionId() : 0L);
        nhaThuoc.setModified(new Date());
        nhaThuoc.setModifiedByUserId(userInfo.getId());
        hdrRepo.save(nhaThuoc);
        //lưu lịch sử
        LichSuCapNhatThanhVien lichSuCapNhatThanhVien = new LichSuCapNhatThanhVien();
        lichSuCapNhatThanhVien.setStatusId(StatusConstant.UPDATE);
        lichSuCapNhatThanhVien.setMaThanhVien(req.getMaNhaThuoc());
        lichSuCapNhatThanhVien.setGhiChu(req.getDescription());
        lichSuCapNhatThanhVien.setNgayCapNhat(new Date());
        lichSuCapNhatThanhVienRepository.save(lichSuCapNhatThanhVien);
        return nhaThuoc;
    }

    @Override
    public Boolean deleteByMaNhaThuoc(NhaThuocsReq req) throws Exception{
        Profile userInfo = this.getLoggedUser();
        if (userInfo == null)
            throw new Exception("Bad request.");
        NhaThuocs nhaThuoc = hdrRepo.findByMaNhaThuoc(req.getMaNhaThuoc());
        nhaThuoc.setRecordStatusId(RecordStatusContains.DELETED);
        nhaThuoc.setModified(new Date());
        nhaThuoc.setModifiedByUserId(userInfo.getId());
        hdrRepo.save(nhaThuoc);
        //lưu lịch sử
        LichSuCapNhatThanhVien lichSuCapNhatThanhVien = new LichSuCapNhatThanhVien();
        lichSuCapNhatThanhVien.setStatusId(StatusConstant.DELETE);
        lichSuCapNhatThanhVien.setMaThanhVien(req.getMaNhaThuoc());
        lichSuCapNhatThanhVien.setGhiChu(req.getDescription());
        lichSuCapNhatThanhVien.setNgayCapNhat(new Date());
        lichSuCapNhatThanhVienRepository.save(lichSuCapNhatThanhVien);
        return true;
    }
    //region PRIVATE
    private String getNewStoreCode() throws Exception {
        String code = "0000";
        Optional<NhaThuocs> optional = hdrRepo.findLatestRecord();
        if (optional.isEmpty()) return code;
        NhaThuocs lastDs = optional.get();
        Long lastDsIdNumber;
        try {
            lastDsIdNumber = Long.parseLong(lastDs.getMaNhaThuoc());
        } catch (NumberFormatException e) {
            lastDsIdNumber = 0L;
        }
        if (lastDsIdNumber == 0) lastDsIdNumber = lastDs.getId();
        lastDsIdNumber++;
        code = StoreHelper.getCodeBasedOnNumber(lastDsIdNumber);
        while (true) {
            NhaThuocs byMaNT = hdrRepo.findByMaNhaThuoc(code);
            if (byMaNT == null) break;
            lastDsIdNumber++;
            code = StoreHelper.getCodeBasedOnNumber(lastDsIdNumber);
        }
        return code;
    }
    //endregion
}
