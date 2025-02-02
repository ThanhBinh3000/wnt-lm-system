package vn.com.gsoft.system.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.gsoft.system.model.dto.*;
import vn.com.gsoft.system.response.BaseResponse;
import vn.com.gsoft.system.service.NhaThuocsService;
import vn.com.gsoft.system.constant.PathConstant;
import vn.com.gsoft.system.util.system.ResponseUtils;

@Slf4j
@RestController
@RequestMapping("/thanh-vien")
public class NhaThuocsController {

    @Autowired
    private NhaThuocsService service;


    @PostMapping(value = PathConstant.URL_SEARCH_PAGE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse> colection(@RequestBody NhaThuocsReq objReq) throws Exception {
        return ResponseEntity.ok(ResponseUtils.ok(service.searchPage(objReq)));
    }

    @PostMapping(value = PathConstant.URL_SEARCH_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse> colectionList(@RequestBody NhaThuocsReq objReq) throws Exception {
        return ResponseEntity.ok(ResponseUtils.ok(service.searchList(objReq)));
    }

    @PostMapping(value = PathConstant.URL_CREATE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BaseResponse> insert(@Valid @RequestBody NhaThuocsReq objReq) throws Exception {
        return ResponseEntity.ok(ResponseUtils.ok(service.create(objReq)));
    }

    @PostMapping(value = PathConstant.URL_UPDATE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BaseResponse> update(@Valid @RequestBody NhaThuocsReq objReq) throws Exception {
        return ResponseEntity.ok(ResponseUtils.ok(service.update(objReq)));
    }


    @GetMapping(value = PathConstant.URL_DETAIL, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse> detail(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(ResponseUtils.ok(service.detail(id)));
    }

    @PostMapping(value = PathConstant.URL_DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse> delete(@Valid @RequestBody NhaThuocsReq idSearchReq) throws Exception {
        return ResponseEntity.ok(ResponseUtils.ok(service.delete(idSearchReq.getId())));
    }

    @PostMapping(value = PathConstant.URL_DELETE + "-by-ma-nha-thuoc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse> deleteByMaNhaThuoc(@Valid @RequestBody NhaThuocsReq objReq) throws Exception {
        return ResponseEntity.ok(ResponseUtils.ok(service.deleteByMaNhaThuoc(objReq)));
    }

    @PostMapping(value = "detail-by-ma-nha-thuoc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse> detailByMaNhaThuoc(@Valid @RequestBody NhaThuocsReq objReq) throws Exception {
        return ResponseEntity.ok(ResponseUtils.ok(service.detailByMa(objReq.getMaNhaThuoc())));
    }
    @PostMapping(value = "ds-by-ma-nha-thuoc-cha", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse> dsByMaNhaThuocCha(@Valid @RequestBody NhaThuocsReq objReq) throws Exception {
        return ResponseEntity.ok(ResponseUtils.ok(service.dsByMaNhaCha(objReq.getMaNhaCha())));
    }
}
