package vn.com.gsoft.system.repository;

import jakarta.persistence.Tuple;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.com.gsoft.system.constant.*;
import vn.com.gsoft.system.entity.NhaThuocs;
import vn.com.gsoft.system.model.dto.NhaThuocsReq;
import vn.com.gsoft.system.model.dto.NhaThuocsRes;

import java.util.List;
import java.util.Optional;

@Repository
public interface NhaThuocsRepository extends BaseRepository<NhaThuocs, NhaThuocsReq, Long> {
    @Query(value = "SELECT * FROM NhaThuocs c " +
            "WHERE 1=1 "
            + " AND (:#{#param.recordStatusId} IS NULL OR c.RecordStatusId = :#{#param.recordStatusId})"
            + " AND (:#{#param.maNhaThuoc} IS NULL OR lower(c.MaNhaThuoc) LIKE lower(concat('%',CONCAT(:#{#param.maNhaThuoc},'%'))))"
            + " AND (:#{#param.tenNhaThuoc} IS NULL OR lower(c.TenNhaThuoc) LIKE lower(concat('%',CONCAT(:#{#param.tenNhaThuoc},'%'))))"
            + " AND (:#{#param.fromDate} IS NULL OR c.Created >= :#{#param.fromDate}) "
            + " AND ((:#{#param.toDate} IS NULL OR c.Created <= :#{#param.toDate} OR (c.Modified IS NOT NULL AND c.Modified <= :#{#param.toDate}))"
            + " OR (:#{#param.toDate} IS NULL OR c.Created <= :#{#param.toDate}) OR (c.Modified IS NOT NULL AND c.Modified <= :#{#param.toDate}))"
            + " AND ((:#{#param.hoatDong} IS NULL) OR (c.RecordStatusId = :#{#param.recordStatusId})) "
            + " AND ((:#{#param.createdByUserId} IS NULL) OR (c.CreatedBy_UserId = :#{#param.createdByUserId}))"
            + " AND ((:#{#param.entityId} IS NULL) OR (c.entityId = :#{#param.entityId}))"
            + " AND ((:#{#param.tinhThanhId} IS NULL) OR (c.TinhThanhId = :#{#param.tinhThanhId}))"
            + " AND ((:#{#param.textSearch} IS NULL OR lower(c.TenNhaThuoc) LIKE lower(concat('%',CONCAT(:#{#param.textSearch},'%'))))"
            + " OR (:#{#param.textSearch} IS NULL OR lower(c.DiaChi) LIKE lower(concat('%',CONCAT(:#{#param.textSearch},'%')))))"
            + " ORDER BY c.Created desc"
            , nativeQuery = true
    )
    Page<NhaThuocs> searchPage(@Param("param") NhaThuocsReq param, Pageable pageable);


    @Query("SELECT c FROM NhaThuocs c " +
            "WHERE 1=1 "
            + " AND (:#{#param.maNhaThuoc} IS NULL OR lower(c.maNhaThuoc) LIKE lower(concat('%',CONCAT(:#{#param.maNhaThuoc},'%'))))"
            + " AND (:#{#param.tenNhaThuoc} IS NULL OR lower(c.tenNhaThuoc) LIKE lower(concat('%',CONCAT(:#{#param.tenNhaThuoc},'%'))))"
            + " ORDER BY c.id desc"
    )
    List<NhaThuocs> searchList(@Param("param") NhaThuocsReq param);

    NhaThuocs findByMaNhaThuoc(String maNhaThuoc);

    @Query("SELECT c FROM NhaThuocs c " +
            " WHERE 1=1 " +
            " ORDER BY c.created DESC" +
            " LIMIT 1")
    Optional<NhaThuocs> findLatestRecord();
    List<NhaThuocs> findByMaNhaChaAndRecordStatusId(String maNhaCha, Long recordStatusId);
}
