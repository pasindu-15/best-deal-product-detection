package com.uom.msc.cse.sdoncloud.detection.external.gcpservices;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.uom.msc.cse.sdoncloud.detection.domain.boundary.LabelDetectionInterface;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.FeatureDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Log4j2
public class LabelDetectionService implements LabelDetectionInterface {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;


    public FeatureDto detectLabel(String imgPath){

        List<String> features = new ArrayList();
        List<String> featuresParts = new ArrayList<>();
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

            for (String f:imageLabels) {

                features.add(f);
                featuresParts.addAll(Arrays.asList(f.split(" ")));
            }
            FeatureDto featureDto = new FeatureDto();
            featureDto.setFeatures(features);

            int max = 0;
            int curr = 0;
            String currKey =  null;
            Set<String> unique = new HashSet<String>(featuresParts);

            for (String key : unique) {
                key = key.substring(0,1).toUpperCase()+key.substring(0,1);
                curr = Collections.frequency(featuresParts, key);

                if(max < curr){
                    max = curr;
                    currKey = key;
                }
            }

            featureDto.setMainFeatures(currKey);

            log.info("Image Classification results: {}",imageLabels);

            return featureDto;
        }catch (Exception ex){
            log.info("Image detection error: {}", ex.getMessage() );
            return null;
        }

    }


}
