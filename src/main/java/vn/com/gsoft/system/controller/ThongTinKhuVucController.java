package vn.com.gsoft.system.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.gsoft.system.constant.PathConstant;
import vn.com.gsoft.system.model.dto.CitiesReq;
import vn.com.gsoft.system.model.dto.LichSuCapNhatThanhVienReq;
import vn.com.gsoft.system.model.dto.RegionsReq;
import vn.com.gsoft.system.model.dto.WardsReq;
import vn.com.gsoft.system.response.BaseResponse;
import vn.com.gsoft.system.service.CitiesService;
import vn.com.gsoft.system.service.LichSuCapNhatThanhVienService;
import vn.com.gsoft.system.service.RegionsService;
import vn.com.gsoft.system.service.WardsService;
import vn.com.gsoft.system.util.system.ResponseUtils;

@Slf4j
@RestController
@RequestMapping("/thong-tin-khu-vuc")
public class ThongTinKhuVucController {
	
  @Autowired
  private CitiesService citiesServiceservice;

  @Autowired
  private RegionsService regionsService;

  @Autowired
  private WardsService wardsService;

  @PostMapping(value = PathConstant.URL_SEARCH_LIST + "-quan-huyen", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<BaseResponse> danhSachTinhThanh(@RequestBody CitiesReq req) throws Exception {
    return ResponseEntity.ok(ResponseUtils.ok(citiesServiceservice.searchList(req)));
  }

  @PostMapping(value = PathConstant.URL_SEARCH_LIST + "-tinh-thanh", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<BaseResponse> danhSachQuanHuyen(@RequestBody RegionsReq req) throws Exception {
    return ResponseEntity.ok(ResponseUtils.ok(regionsService.searchList(req)));
  }

  @PostMapping(value = PathConstant.URL_SEARCH_LIST + "-phuong-xa", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<BaseResponse> danhSachPhuongXa(@RequestBody WardsReq req) throws Exception {
    return ResponseEntity.ok(ResponseUtils.ok(wardsService.searchList(req)));
  }
}
