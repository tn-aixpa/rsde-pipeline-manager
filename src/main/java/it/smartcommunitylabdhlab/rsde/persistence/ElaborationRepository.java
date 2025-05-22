package it.smartcommunitylabdhlab.rsde.persistence;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ElaborationRepository extends CustomJpaRepository<ElaborationEntity, String>, JpaSpecificationExecutor<ElaborationEntity> {
    
    ElaborationEntity findByworkflowId(String workflowId);

}
