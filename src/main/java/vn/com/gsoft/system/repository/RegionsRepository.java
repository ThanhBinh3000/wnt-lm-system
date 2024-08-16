package vn.com.gsoft.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.gsoft.system.entity.Regions;
import vn.com.gsoft.system.model.dto.RegionsReq;

import java.util.List;

@Repository
public interface RegionsRepository extends BaseRepository<Regions, RegionsReq, Long> {
  @Query("SELECT c FROM Regions c " +
         "WHERE 1=1 "
          + " AND (:#{#param.id} IS NULL OR c.id = :#{#param.id}) "
          + " AND (:#{#param.name} IS NULL OR lower(c.name) LIKE lower(concat('%',CONCAT(:#{#param.name},'%'))))"
          + " AND (:#{#param.countryId} IS NULL OR c.countryId = :#{#param.countryId}) "
          + " AND (:#{#param.levelId} IS NULL OR c.levelId = :#{#param.levelId}) "
          + " ORDER BY c.id desc"
  )
  Page<Regions> searchPage(@Param("param") RegionsReq param, Pageable pageable);
  
  
  @Query("SELECT c FROM Regions c " +
         "WHERE 1=1 "
          + " AND (:#{#param.id} IS NULL OR c.id = :#{#param.id}) "
          + " AND (:#{#param.name} IS NULL OR lower(c.name) LIKE lower(concat('%',CONCAT(:#{#param.name},'%'))))"
          + " AND (:#{#param.countryId} IS NULL OR c.countryId = :#{#param.countryId}) "
          + " AND (:#{#param.levelId} IS NULL OR c.levelId = :#{#param.levelId}) "
          + " ORDER BY c.id desc"
  )
  List<Regions> searchList(@Param("param") RegionsReq param);

}
