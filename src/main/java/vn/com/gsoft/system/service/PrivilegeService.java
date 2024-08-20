package vn.com.gsoft.system.service;

import vn.com.gsoft.system.entity.Entity;
import vn.com.gsoft.system.entity.Privilege;
import vn.com.gsoft.system.model.dto.EntityReq;
import vn.com.gsoft.system.model.dto.PrivilegeReq;

import java.util.List;

public interface PrivilegeService extends BaseService<Privilege, PrivilegeReq, Long> {

    List<Privilege> searchListRoleByMaThanhVien(PrivilegeReq req ) throws Exception;
}