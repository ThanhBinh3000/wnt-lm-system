package vn.com.gsoft.system.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.com.gsoft.system.model.system.BaseRequest;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntityReq extends BaseRequest {
    private Long id;
    private String code;
    private String name;
    private Integer type;
}
