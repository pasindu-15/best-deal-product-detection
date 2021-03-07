
package com.uom.msc.cse.sdoncloud.detection.domain.service;

import com.uom.msc.cse.sdoncloud.detection.domain.boundary.ImageHandlerInterface;
import com.uom.msc.cse.sdoncloud.detection.domain.boundary.LabelDetectionInterface;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.ProductDetectDomainRequestEntity;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.ProductDetectDomainResponseEntity;
import com.uom.msc.cse.sdoncloud.detection.external.OfferMatcherService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
public class ProductManageService {
    @Autowired
    ProductDetectDomainResponseEntity productDetectDomainResponseEntity;

    @Autowired
    LabelDetectionInterface labelDetectionInterface;

    @Autowired
    ImageHandlerInterface imageHandlerInter;

    @Autowired
    OfferMatcherService offerMatcherService;
    /**
     * handle business logic
     * @param
     * @return
     */
    public ProductDetectDomainResponseEntity process(ProductDetectDomainRequestEntity productDetectDomainRequestEntity) throws Exception{
        log.info("INFO|START use case...| request : " );
//        TODO: Execute business logic

        List<String> featuresist = productDetectDomainRequestEntity.getImagesBase64().parallelStream().map(img ->{
            String features = null;
            Boolean isDeleted = null;
            try {
                String imgPath = imageHandlerInter.decodeImage(img);

//                    features = labelDetectionInterface.detectLabel(imgPath);
                    isDeleted  = imageHandlerInter.deleteImg(imgPath);

                log.info("image: {} | features: {} | imageDeleted {}",imgPath,features,isDeleted);

            } catch (IOException e) {
                log.error("Product Detection IOException");
            }
            return features;
        }).collect(Collectors.toList());



//        offerMatcherService.matchOffers();



        if(featuresist.contains(null)){
            productDetectDomainResponseEntity.setResCode("01");
            productDetectDomainResponseEntity.setResDesc("Something went wrong!");
        }else {
            productDetectDomainResponseEntity.setResCode("00");
            productDetectDomainResponseEntity.setResDesc("Operation Success");
        }

        productDetectDomainResponseEntity.setFeatures(featuresist);

        return productDetectDomainResponseEntity;
    }

}
