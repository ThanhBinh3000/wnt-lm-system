package vn.com.gsoft.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.com.gsoft.system.entity.Entity;
import vn.com.gsoft.system.entity.TinhThanhs;
import vn.com.gsoft.system.model.dto.EntityReq;
import vn.com.gsoft.system.model.dto.TinhThanhsReq;

import java.util.List;

public interface EntityRepository extends BaseRepository<Entity, EntityReq, Integer> {
    @Query("SELECT c FROM Entity c " +
            "WHERE 1=1 "
    )
    Page<Entity> searchPage(@Param("param") EntityReq param, Pageable pageable);


    @Query("SELECT c FROM Entity c " +
            "WHERE 1=1 "
    )
    List<Entity> searchList(@Param("param") EntityReq param);

    Entity findByCode(String level);
}
