package vn.com.gsoft.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CauHinhHeThong")
public class CauHinhHeThong extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "Code")
    private String code;
    @Column(name = "Display")
    private String display;
    @Column(name = "Value")
    private Integer Value;
    @Column(name = "GhiChu")
    private String ghiChu;
}

