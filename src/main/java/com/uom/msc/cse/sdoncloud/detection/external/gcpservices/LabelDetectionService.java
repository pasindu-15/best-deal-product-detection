package com.uom.msc.cse.sdoncloud.detection.external.gcpservices;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
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
                    this.resourceLoader.getResource("file:"+imgPath), Feature.Type.LABEL_DETECTION,Feature.Type.OBJECT_LOCALIZATION);
            // This gets the annotations of the image from the response object.
            List<EntityAnnotation> annotations = response.getLabelAnnotationsList();
            List<String> imageLabels =
                    response.getLabelAnnotationsList().parallelStream()
                            .filter(f->f.getScore()>0.8)
                            .map(f->f.getDescription())
                            .collect(Collectors.toList());

            LocalizedObjectAnnotation object =
                    response.getLocalizedObjectAnnotationsList().parallelStream()
                            .max(Comparator.comparingDouble(LocalizedObjectAnnotation::getScore)).get();
            FeatureDto featureDto = new FeatureDto();
            featureDto.setFeatures(imageLabels);


            featureDto.setMainFeature(object.getName());
            log.info("Image Classification results: {}",imageLabels);
            return featureDto;
        }catch (Exception ex){
            log.info("Image detection error: {}", ex.getMessage() );
            return null;
        }
    }
    public FeatureDto detectLabelFromByteStringImage(ByteString imageByteString){
        try {
            Image img = Image.newBuilder().setContent(imageByteString).build();
            Feature.Type[] types = new Feature.Type[]{Feature.Type.OBJECT_LOCALIZATION,Feature.Type.LABEL_DETECTION,Feature.Type.PRODUCT_SEARCH,Feature.Type.IMAGE_PROPERTIES,Feature.Type.LOGO_DETECTION,Feature.Type.SAFE_SEARCH_DETECTION,Feature.Type.LANDMARK_DETECTION};
            List<Feature> featureList = Arrays.asList(types).stream().map((featureType) -> {
                return Feature.newBuilder().setType(featureType).build();
            }).collect(Collectors.toList());
            BatchAnnotateImagesRequest request = BatchAnnotateImagesRequest.newBuilder().addRequests(AnnotateImageRequest.newBuilder().addAllFeatures(featureList).setImageContext(ImageContext.getDefaultInstance()).setImage(img)).build();
            ImageAnnotatorClient client = ImageAnnotatorClient.create();
            BatchAnnotateImagesResponse batchResponse = client.batchAnnotateImages(request);
            List<AnnotateImageResponse> annotateImageResponses = batchResponse.getResponsesList();
            AnnotateImageResponse response = annotateImageResponses.get(0);
            // This gets the annotations of the image from the response object.
            List<EntityAnnotation> annotations = response.getLabelAnnotationsList();
            List<String> imageLabels =
                    response.getLabelAnnotationsList().parallelStream()
                            .filter(f->f.getScore()>0.8)
                            .map(f->f.getDescription())
                            .collect(Collectors.toList());
            LocalizedObjectAnnotation object =
                    response.getLocalizedObjectAnnotationsList().parallelStream()
                            .max(Comparator.comparingDouble(LocalizedObjectAnnotation::getScore)).get();
            FeatureDto featureDto = new FeatureDto();
            featureDto.setFeatures(imageLabels);

            featureDto.setMainFeature(object.getName());
            log.info("Image Classification results: {}",annotateImageResponses.toString());
            return featureDto;
        }catch (Exception ex){
            log.info("Image detection error: {}", ex.getMessage() );
            return null;
        }
    }

}