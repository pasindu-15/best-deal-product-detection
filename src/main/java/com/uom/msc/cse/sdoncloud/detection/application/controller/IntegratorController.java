
package com.uom.msc.cse.sdoncloud.detection.application.controller;

import com.uom.msc.cse.sdoncloud.detection.application.transformer.ResponseEntityTransformer;
import com.uom.msc.cse.sdoncloud.detection.application.transport.request.entities.SampleRequestEntity;
import com.uom.msc.cse.sdoncloud.detection.application.transport.response.transformers.SampleResponseTransformer;
import com.uom.msc.cse.sdoncloud.detection.application.validator.RequestEntityValidator;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.SampleDomainRequestEntity;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.SampleDomainResponseEntity;
import com.uom.msc.cse.sdoncloud.detection.domain.service.SampleManageService;
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
public class IntegratorController extends BaseController {
    SampleManageService sampleManageService;

    @Autowired
    ResponseEntityTransformer responseEntityTransformer;

    @Autowired
    SampleResponseTransformer sampleResponseTransformer;

    @Autowired
    private RequestEntityValidator validator;

    @PostMapping(value="/action", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> integration(@Validated @RequestBody(required = true) SampleRequestEntity sampleRequestEntity, HttpServletRequest request)throws Exception{
        Integer a = sampleRequestEntity.getId();
        String image = sampleRequestEntity.getImage();

//        TODO: set UUID
        /*setLogIdentifier(request);
//        TODO: validate the request
        validator.validate(sampleRequestEntity);
//        logger.info("Request validation success");

//        TODO: request object map to domain entity object
        SampleDomainRequestEntity sampleDomainRequestEntity = new ModelMapper().map(sampleRequestEntity, SampleDomainRequestEntity.class);

//        TODO: call domain business logic
        SampleDomainResponseEntity sampleDomainResponseEntity = sampleManageService.process(sampleDomainRequestEntity);

//        TODO: transform domain response
        Map trResponse = responseEntityTransformer.transform(sampleDomainResponseEntity,sampleResponseTransformer);
//        logger.info("Transformed response : "+trResponse.toString());
*/
//        TODO: return response
        return ResponseEntity.status(HttpStatus.OK).body(a);
         //return ResponseEntity.status(HttpStatus.OK).body(trResponse);
    }

    @GetMapping(value="/moda-pasiya")
    public String moda_pasiya(){
        return "<marquee><h1>Moda Pasiya<h1></marquee>";
    }

}
