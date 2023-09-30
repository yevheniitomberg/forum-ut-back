package tech.tomberg.forumut.entity.forum;

import jakarta.persistence.*;
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
    @Column(unique = true)
    @Enumerated(value = EnumType.STRING)
    private SpecializationSphere specializationSphere;
}
