package com.uom.msc.cse.sdoncloud.detection.application.function;

//import com.uom.msc.cse.sdoncloud.detection.application.controller.BaseController;
import com.uom.msc.cse.sdoncloud.detection.application.transformer.ResponseEntityTransformer;
import com.uom.msc.cse.sdoncloud.detection.application.transport.request.entities.ProductDetectRequestEntity;
import com.uom.msc.cse.sdoncloud.detection.application.transport.response.transformers.SampleResponseTransformer;
import com.uom.msc.cse.sdoncloud.detection.application.validator.RequestEntityValidator;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.ProdDetectionDomainRequestEntity;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.SampleDomainResponseEntity;
import com.uom.msc.cse.sdoncloud.detection.domain.service.ProductManageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.function.Function;

public class ProductDetection implements Function<ProductDetectRequestEntity,ResponseEntity> {

    @Autowired
    ProductManageService productManageService;

    @Autowired
    ResponseEntityTransformer responseEntityTransformer;

    @Autowired
    SampleResponseTransformer sampleResponseTransformer;

    @Autowired
    private RequestEntityValidator validator;



    @Override
    public ResponseEntity apply(ProductDetectRequestEntity productDetectRequestEntity){
//        return "Hello";


////        TODO: set UUID
//        setLogIdentifier(request);
//        TODO: validate the request
        validator.validate(productDetectRequestEntity);
//        logger.info("Request validation success");

//        TODO: request object map to domain entity object
        ProdDetectionDomainRequestEntity prodDetectionDomainRequestEntity = new ModelMapper().map(productDetectRequestEntity, ProdDetectionDomainRequestEntity.class);

//        TODO: call domain business logic
        SampleDomainResponseEntity sampleDomainResponseEntity = productManageService.process(prodDetectionDomainRequestEntity);

//        TODO: transform domain response
        Map trResponse = responseEntityTransformer.transform(sampleDomainResponseEntity,sampleResponseTransformer);
//        logger.info("Transformed response : "+trResponse.toString());

//        TODO: return response
        return ResponseEntity.status(HttpStatus.OK).body(trResponse);


    }


}
