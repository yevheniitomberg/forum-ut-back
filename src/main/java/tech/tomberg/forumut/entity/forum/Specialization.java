package tech.tomberg.forumut.entity.forum;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Specialization {
    @Id
    private String code;
    private String name;
    private SpecializationSphere specializationSphere;
}
