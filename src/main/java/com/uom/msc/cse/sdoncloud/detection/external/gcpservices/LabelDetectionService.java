package com.uom.msc.cse.sdoncloud.detection.external.gcpservices;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.uom.msc.cse.sdoncloud.detection.domain.boundary.LabelDetectionInterface;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Log4j2
public class LabelDetectionService implements LabelDetectionInterface {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;


    public String detectLabel(String imgPath){

        String features = "";
        try {
            AnnotateImageResponse response = this.cloudVisionTemplate.analyzeImage(
//                this.resourceLoader.getResource("classpath:temp-images/air-max-270-shoe-62kcVV.jpg"), Feature.Type.LABEL_DETECTION);
                    this.resourceLoader.getResource("file:"+imgPath), Feature.Type.LABEL_DETECTION);

            // This gets the annotations of the image from the response object.
            List<EntityAnnotation> annotations = response.getLabelAnnotationsList();

            List<String> imageLabels =
                    response.getLabelAnnotationsList().parallelStream()
                            .filter(f->f.getScore()>0.8)
                            .map(f->f.getDescription())
                            .collect(Collectors.toList());
            for (String l:imageLabels) {
                features += l+"|";
            }

            log.info("Image Classification results: {}",imageLabels);

            return features.substring(0,features.length()-1);
        }catch (Exception ex){
            log.info("Image detection error: {}", ex.getMessage() );
            return null;
        }


    }


}
