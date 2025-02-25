package MCM_Project.MCM.Entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class McmClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;

    @OneToMany(mappedBy = "mcmClass", cascade = CascadeType.ALL)
    private List<McmModel> models;
}
