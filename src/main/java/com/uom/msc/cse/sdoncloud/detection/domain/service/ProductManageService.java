
package com.uom.msc.cse.sdoncloud.detection.domain.service;

import com.uom.msc.cse.sdoncloud.detection.domain.boundary.LabelDetectionInterface;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.ProductDetectDomainRequestEntity;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.SampleDomainResponseEntity;
import com.uom.msc.cse.sdoncloud.detection.external.OfferMatcherService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class ProductManageService {
    @Autowired
    SampleDomainResponseEntity sampleDomainResponseEntity;

    @Autowired
    LabelDetectionInterface labelDetectionInterface;

    @Autowired
    OfferMatcherService offerMatcherService;
    /**
     * handle business logic
     * @param
     * @return
     */
    public SampleDomainResponseEntity process(ProductDetectDomainRequestEntity productDetectDomainRequestEntity) throws Exception{
        log.info("INFO|START use case...| request : " );
//        TODO: Execute business logic


        labelDetectionInterface.detectLabel(productDetectDomainRequestEntity.getImagesBase64());
//        offerMatcherService.matchOffers();



        sampleDomainResponseEntity.setResCode("200");
        sampleDomainResponseEntity.setResDesc("Operation Success");

        return sampleDomainResponseEntity;
    }

}
