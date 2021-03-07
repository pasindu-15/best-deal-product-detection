
package com.uom.msc.cse.sdoncloud.detection.domain.service;

import com.uom.msc.cse.sdoncloud.detection.domain.boundary.ImageHandlerInterface;
import com.uom.msc.cse.sdoncloud.detection.domain.boundary.LabelDetectionInterface;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.ProdDetectionDomainRequestEntity;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.SampleDomainResponseEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@Log4j2
public class ProductManageService {
    @Autowired
    SampleDomainResponseEntity sampleDomainResponseEntity;

    @Autowired
    LabelDetectionInterface labelDetectionInterface;



    @Autowired
    ImageHandlerInterface imageHandlerInterface;

    /**
     * handle business logic
     * @param
     * @return
     */
    public SampleDomainResponseEntity process(ProdDetectionDomainRequestEntity prodDetectionDomainRequestEntity){
        log.info("INFO|START use case...| request : " );
//        TODO: Execute business logic

        try{
            String imageName = imageHandlerInterface.decodeImage(prodDetectionDomainRequestEntity.getImageBase64());


//          label detection from google vision
//            labelDetectionInterface.detectLabel();
            log.info("Successfully detected image");

            //delete the temp-image
            imageHandlerInterface.deleteImg(imageName);



        }catch (IOException ex){
            ex.printStackTrace();
        }




//        offerMatcherService.matchOffers();




        sampleDomainResponseEntity.setResCode("200");
        sampleDomainResponseEntity.setResDesc("Operation Success");

        return sampleDomainResponseEntity;
    }

}
