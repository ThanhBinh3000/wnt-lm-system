package vn.com.gsoft.system.service;

import org.springframework.data.domain.Page;
import vn.com.gsoft.system.entity.UserProfile;
import vn.com.gsoft.system.model.dto.ChangePasswordReq;
import vn.com.gsoft.system.model.dto.UserProfileReq;

import java.util.List;

public interface UserProfileService extends BaseService<UserProfile, UserProfileReq, Long> {

    Boolean changePassword(ChangePasswordReq objReq) throws Exception;

    Boolean resetPassword(ChangePasswordReq objReq) throws Exception;

    UserProfile insertUser(UserProfileReq objReq) throws Exception;

    UserProfile updateUser(UserProfileReq objReq) throws Exception;

    UserProfile insertStaff(UserProfileReq objReq) throws Exception;

    UserProfile updateStaff(UserProfileReq objReq) throws Exception;
}