
package com.uom.msc.cse.sdoncloud.detection.application.transport.response.transformers;

import com.uom.msc.cse.sdoncloud.detection.application.transformer.ResponseEntityInterface;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.ProductDetectDomainResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class ProductDetectionResponseTransformer implements ResponseEntityInterface {
    @Override
    public Map transform(Object entity) {
        ProductDetectDomainResponseEntity responseEntity = (ProductDetectDomainResponseEntity)entity;
        Map<String,Object> mapping = new HashMap<>();
        mapping.put("resCode",responseEntity.getResCode());
        mapping.put("resDesc",responseEntity.getResDesc());
        mapping.put("features",responseEntity.getFeatures());

        return mapping;
    }
}
