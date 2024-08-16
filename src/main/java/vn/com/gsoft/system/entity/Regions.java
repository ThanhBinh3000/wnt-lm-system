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
@Table(name = "Regions")
public class Regions extends BaseEntity{
    @Id
    @Column(name = "Id")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "CountryId")
    private Long countryId;
    @Column(name = "LevelId")
    private Integer levelId;
}

