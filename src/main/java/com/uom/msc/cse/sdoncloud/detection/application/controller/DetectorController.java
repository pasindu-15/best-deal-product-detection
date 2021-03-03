
package com.uom.msc.cse.sdoncloud.detection.application.controller;

import com.uom.msc.cse.sdoncloud.detection.application.transformer.ResponseEntityTransformer;
import com.uom.msc.cse.sdoncloud.detection.application.transport.request.entities.ProductDetectRequestEntity;
import com.uom.msc.cse.sdoncloud.detection.application.transport.response.transformers.SampleResponseTransformer;
import com.uom.msc.cse.sdoncloud.detection.application.validator.RequestEntityValidator;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.ProdDetectionDomainRequestEntity;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.SampleDomainResponseEntity;
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
@RequestMapping("${base-url.context}")
@Log4j2
public class DetectorController extends BaseController {
    @Autowired
    ProductManageService productManageService;

    @Autowired
    ResponseEntityTransformer responseEntityTransformer;

    @Autowired
    SampleResponseTransformer sampleResponseTransformer;

    @Autowired
    private RequestEntityValidator validator;

    @PostMapping(value="/detect", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> detect(@Validated @RequestBody(required = true) ProductDetectRequestEntity productDetectRequestEntity, HttpServletRequest request)throws Exception{

//        TODO: set UUID
        setLogIdentifier(request);
//        TODO: validate the request
        validator.validate(productDetectRequestEntity);
//        logger.info("Request validation success");

//        TODO: request object map to domain entity object
        ProdDetectionDomainRequestEntity sampleDomainRequestEntity = new ModelMapper().map(productDetectRequestEntity, ProdDetectionDomainRequestEntity.class);

//        TODO: call domain business logic
        SampleDomainResponseEntity sampleDomainResponseEntity = productManageService.process(sampleDomainRequestEntity);

//        TODO: transform domain response
        Map trResponse = responseEntityTransformer.transform(sampleDomainResponseEntity,sampleResponseTransformer);
//        logger.info("Transformed response : "+trResponse.toString());

//        TODO: return response
         return ResponseEntity.status(HttpStatus.OK).body(trResponse);
    }
}
