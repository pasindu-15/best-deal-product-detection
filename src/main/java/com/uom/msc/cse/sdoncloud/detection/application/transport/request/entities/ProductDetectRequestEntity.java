
package com.uom.msc.cse.sdoncloud.detection.application.transport.request.entities;

import com.uom.msc.cse.sdoncloud.detection.application.validator.RequestEntityInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetectRequestEntity implements RequestEntityInterface {

    private String image;

}
