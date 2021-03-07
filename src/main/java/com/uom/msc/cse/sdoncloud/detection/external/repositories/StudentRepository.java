package com.uom.msc.cse.sdoncloud.detection.external.repositories;

import com.uom.msc.cse.sdoncloud.detection.domain.entities.Student;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

public interface StudentRepository extends DatastoreRepository<Student, String> {

}
