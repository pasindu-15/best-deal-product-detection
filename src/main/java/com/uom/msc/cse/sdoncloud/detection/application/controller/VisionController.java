
package com.uom.msc.cse.sdoncloud.detection.application.controller;

import com.uom.msc.cse.sdoncloud.detection.application.transformer.ResponseEntityTransformer;
import com.uom.msc.cse.sdoncloud.detection.application.transport.request.entities.ProductDetectRequestEntity;
import com.uom.msc.cse.sdoncloud.detection.application.transport.response.transformers.ProductDetectionResponseTransformer;
import com.uom.msc.cse.sdoncloud.detection.application.validator.RequestEntityValidator;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.ProductDetectDomainRequestEntity;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.ProductDetectDomainResponseEntity;
import com.uom.msc.cse.sdoncloud.detection.domain.service.ProductManageService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("${base-url.context}/vision")
@Log4j2
public class VisionController extends BaseController {
    @Autowired
    ProductManageService productManageService;

    @Autowired
    ResponseEntityTransformer responseEntityTransformer;

    @Autowired
    ProductDetectionResponseTransformer productDetectionResponseTransformer;

    @Autowired
    private RequestEntityValidator validator;

    @PostMapping(value="/product-detect", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> detect(@Validated @RequestBody(required = true) ProductDetectRequestEntity productDetectRequestEntity, HttpServletRequest request)throws Exception{

//        TODO: set UUID
        setLogIdentifier(request);
//        TODO: validate the request
        validator.validate(productDetectRequestEntity);
//        logger.info("Request validation success");

//        TODO: request object map to domain entity object
        ProductDetectDomainRequestEntity productDetectDomainRequestEntity = new ModelMapper().map(productDetectRequestEntity, ProductDetectDomainRequestEntity.class);

//        TODO: call domain business logic
        ProductDetectDomainResponseEntity productDetectDomainResponseEntity = productManageService.process(productDetectDomainRequestEntity);

//        TODO: transform domain response
        Map trResponse = responseEntityTransformer.transform(productDetectDomainResponseEntity, productDetectionResponseTransformer);
//        logger.info("Transformed response : "+trResponse.toString());

//        TODO: return response
         return ResponseEntity.status(HttpStatus.OK).body(trResponse);
    }
}
