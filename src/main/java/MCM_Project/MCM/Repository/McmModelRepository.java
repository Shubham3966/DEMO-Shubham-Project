package MCM_Project.MCM.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MCM_Project.MCM.Entities.McmModel;

@Repository
public interface McmModelRepository extends JpaRepository<McmModel, Long> {
}
