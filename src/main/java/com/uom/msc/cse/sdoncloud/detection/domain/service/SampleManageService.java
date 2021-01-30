
package com.uom.msc.cse.sdoncloud.detection.domain.service;

import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.SampleDomainRequestEntity;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.SampleDomainResponseEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class SampleManageService {
    @Autowired
    SampleDomainResponseEntity sampleDomainResponseEntity;
    /**
     * handle business logic
     * @param
     * @return
     */
    public SampleDomainResponseEntity process(SampleDomainRequestEntity sampleDomainRequestEntity) throws Exception{
        log.info("INFO|START use case...| request : " );
//        TODO: Execute business logic

        sampleDomainResponseEntity.setResCode("200");
        sampleDomainResponseEntity.setResDesc("Operation Success");

        return sampleDomainResponseEntity;
    }

}
