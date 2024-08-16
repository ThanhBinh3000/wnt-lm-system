package vn.com.gsoft.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Entity
@Table(name = "LichSuCapNhatThanhVien")
public class LichSuCapNhatThanhVien extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NgayCapNhat")
    private Date ngayCapNhat;
    @Column(name = "GhiChu")
    private String ghiChu;
    @Column(name = "MaThanhVien")
    private String maThanhVien;
    @Column(name = "StatusId")
    private Integer statusId;
    @Transient
    private  String statusName;
}
