
package com.uom.msc.cse.sdoncloud.detection.domain.service;

import com.google.protobuf.ByteString;
import com.uom.msc.cse.sdoncloud.detection.domain.boundary.ImageHandlerInterface;
import com.uom.msc.cse.sdoncloud.detection.domain.boundary.LabelDetectionInterface;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.FeatureDto;
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
    ImageHandlerInterface imageHandlerInterface;

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

        List<FeatureDto> featuresDtoList = productDetectDomainRequestEntity.getImagesBase64().parallelStream().map(img ->{
            FeatureDto featureDto = null;
            Boolean isDeleted = null;
            try {
//                String imgPath = imageHandlerInterface.decodeImage(img);
////
//                featureDto = labelDetectionInterface.detectLabel(imgPath);
//                isDeleted  = imageHandlerInterface.deleteImg(imgPath);
//
                ByteString byteStrImg = imageHandlerInterface.decodeImageToByteString(img);
                featureDto = labelDetectionInterface.detectLabelFromByteStringImage(byteStrImg);

//                log.info("image: {} | features: {} | imageDeleted {}",imgPath,featureDto,isDeleted);

            } catch (Exception e) {
                log.error("Product Detection IOException");
            }
            return featureDto;
        }).collect(Collectors.toList());



//        offerMatcherService.matchOffers();



        if(featuresDtoList.contains(null)){
            productDetectDomainResponseEntity.setResCode("01");
            productDetectDomainResponseEntity.setResDesc("Something went wrong!");
        }else {
            productDetectDomainResponseEntity.setResCode("00");
            productDetectDomainResponseEntity.setResDesc("Operation Success");
        }

        productDetectDomainResponseEntity.setData(featuresDtoList);
        log.info("Domain Response : {}",productDetectDomainResponseEntity.toString());

        return productDetectDomainResponseEntity;
    }

}
