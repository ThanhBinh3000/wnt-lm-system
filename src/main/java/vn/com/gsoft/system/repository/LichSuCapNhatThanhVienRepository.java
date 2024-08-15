package vn.com.gsoft.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.com.gsoft.system.entity.LichSuCapNhatThanhVien;
import vn.com.gsoft.system.model.dto.LichSuCapNhatThanhVienReq;

import java.util.List;

public interface LichSuCapNhatThanhVienRepository extends BaseRepository<LichSuCapNhatThanhVien, LichSuCapNhatThanhVienReq, Integer> {
    @Query("SELECT c FROM LichSuCapNhatThanhVien c " +
            "WHERE 1=1 "
    )
    Page<LichSuCapNhatThanhVien> searchPage(@Param("param") LichSuCapNhatThanhVienReq param, Pageable pageable);


    @Query("SELECT c FROM LichSuCapNhatThanhVien c " +
            "WHERE 1=1 "
    )
    List<LichSuCapNhatThanhVien> searchList(@Param("param") LichSuCapNhatThanhVienReq param);

    List<LichSuCapNhatThanhVien> findLichSuCapNhatThanhVienByMaThanhVien(@Param("maThanhVien") String maThanhVien);
}
