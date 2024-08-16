package vn.com.gsoft.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.gsoft.system.entity.Wards;
import vn.com.gsoft.system.model.dto.WardsReq;

import java.util.List;

@Repository
public interface WardsRepository extends BaseRepository<Wards, WardsReq, Long> {
  @Query("SELECT c FROM Wards c " +
         "WHERE 1=1 "
          + " AND (:#{#param.id} IS NULL OR c.id = :#{#param.id}) "
          + " AND (:#{#param.cityId} IS NULL OR c.cityId = :#{#param.cityId}) "
          + " AND (:#{#param.name} IS NULL OR lower(c.name) LIKE lower(concat('%',CONCAT(:#{#param.name},'%'))))"
          + " ORDER BY c.id desc"
  )
  Page<Wards> searchPage(@Param("param") WardsReq param, Pageable pageable);
  
  
  @Query("SELECT c FROM Wards c " +
         "WHERE 1=1 "
          + " AND (:#{#param.id} IS NULL OR c.id = :#{#param.id}) "
          + " AND (:#{#param.cityId} IS NULL OR c.cityId = :#{#param.cityId}) "
          + " AND (:#{#param.name} IS NULL OR lower(c.name) LIKE lower(concat('%',CONCAT(:#{#param.name},'%'))))"
          + " ORDER BY c.id desc"
  )
  List<Wards> searchList(@Param("param") WardsReq param);

}
