package vn.com.gsoft.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.com.gsoft.system.entity.CauHinhHeThong;
import vn.com.gsoft.system.model.dto.CauHinhHeThongReq;

import java.util.List;

public interface CauHinhHeThongRepository extends BaseRepository<CauHinhHeThong, CauHinhHeThongReq, Long> {
    @Query("SELECT c FROM CauHinhHeThong c " +
            "WHERE 1=1 "
    )
    Page<CauHinhHeThong> searchPage(@Param("param") CauHinhHeThongReq param, Pageable pageable);


    @Query("SELECT c FROM CauHinhHeThong c " +
            "WHERE 1=1 "
    )
    List<CauHinhHeThong> searchList(@Param("param") CauHinhHeThongReq param);
}
