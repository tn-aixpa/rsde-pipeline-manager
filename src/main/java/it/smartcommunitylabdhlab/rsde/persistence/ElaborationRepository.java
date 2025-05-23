package it.smartcommunitylabdhlab.rsde.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ElaborationRepository extends CustomJpaRepository<ElaborationEntity, String>, JpaSpecificationExecutor<ElaborationEntity> {
    
    ElaborationEntity findByWorkflowId(String workflowId);
    Page<ElaborationEntity> findByTag(String tag, Pageable pageable);
    List<ElaborationEntity> findByStatus(String status);

}
