package vn.com.gsoft.system.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.com.gsoft.system.model.system.BaseRequest;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolePrivilegeReq extends BaseRequest {

    private Long roleId;

    private Long privilegeId;
}
