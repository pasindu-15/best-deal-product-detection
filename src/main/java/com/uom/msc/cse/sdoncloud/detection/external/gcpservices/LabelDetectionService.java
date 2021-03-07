package com.uom.msc.cse.sdoncloud.detection.external.gcpservices;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.uom.msc.cse.sdoncloud.detection.domain.boundary.LabelDetectionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LabelDetectionService implements LabelDetectionInterface {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;


    public List<String> detectLabel(List<String> images){
        AnnotateImageResponse response = this.cloudVisionTemplate.analyzeImage(
                this.resourceLoader.getResource("classpath:temp-images/air-max-270-shoe-62kcVV.jpg"), Feature.Type.LABEL_DETECTION);

        // This gets the annotations of the image from the response object.
        List<EntityAnnotation> annotations = response.getLabelAnnotationsList();
        System.out.println("Image Classification results: " + response.getLabelAnnotationsList());


        return null;

    }


}
