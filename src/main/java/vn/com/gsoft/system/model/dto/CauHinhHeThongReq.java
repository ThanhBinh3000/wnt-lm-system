package vn.com.gsoft.system.model.dto;

import lombok.Data;
import vn.com.gsoft.system.model.system.BaseRequest;

@Data
public class CauHinhHeThongReq extends BaseRequest {
    private Long id;
    private String code;
    private String display;
    private Integer value;
    private String ghiChu;
}
