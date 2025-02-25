package MCM_Project.MCM.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class McmModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private McmClass mcmClass;

    @OneToMany(mappedBy = "mcmModel", cascade = CascadeType.ALL)
    private List<McmInstance> instances;
}
