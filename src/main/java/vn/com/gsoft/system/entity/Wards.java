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
@Table(name = "Wards")
public class Wards extends BaseEntity{
    @Id
    @Column(name = "Id")
    private Long id;
    @Column(name = "CityId")
    private Long cityId;
    @Column(name = "Name")
    private String name;
}

