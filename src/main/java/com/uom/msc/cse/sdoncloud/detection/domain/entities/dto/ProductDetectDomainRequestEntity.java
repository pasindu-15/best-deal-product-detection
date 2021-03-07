
package com.uom.msc.cse.sdoncloud.detection.domain.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetectDomainRequestEntity {

    private List<String> imagesBase64;
}
