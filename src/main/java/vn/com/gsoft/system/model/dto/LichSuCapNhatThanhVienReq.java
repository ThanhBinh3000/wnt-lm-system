package vn.com.gsoft.system.model.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.com.gsoft.system.model.system.BaseRequest;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class LichSuCapNhatThanhVienReq extends BaseRequest {
    private Long id;
    private Date ngayCapNhat;
    private String ghiChu;
    private String maThanhVien;
    private Integer statusId;
}
