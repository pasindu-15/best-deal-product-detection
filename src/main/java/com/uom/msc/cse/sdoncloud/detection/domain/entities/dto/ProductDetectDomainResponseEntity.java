
package com.uom.msc.cse.sdoncloud.detection.domain.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Component
public class ProductDetectDomainResponseEntity {
    String resCode;
    String resDesc;
    List<FeatureDto> data;


}
