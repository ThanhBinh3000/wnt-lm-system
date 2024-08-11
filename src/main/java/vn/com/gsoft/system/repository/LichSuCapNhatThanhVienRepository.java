package vn.com.gsoft.system.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.com.gsoft.system.entity.LichSuCapNhatThanhVien;

import java.util.List;

public interface LichSuCapNhatThanhVienRepository extends CrudRepository<LichSuCapNhatThanhVien, Integer> {
    List<LichSuCapNhatThanhVien> findLichSuCapNhatThanhVienByMaThanhVien(@Param("maThanhVien") String maThanhVien);
}
