package MCM_Project.MCM.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class McmInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String status;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private McmModel mcmModel;
}
