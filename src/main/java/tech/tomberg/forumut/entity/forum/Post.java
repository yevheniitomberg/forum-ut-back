package tech.tomberg.forumut.entity.forum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import tech.tomberg.forumut.entity.auth.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnore
    private User author;
    @OneToMany(mappedBy = "relatedPost")
    @ToString.Exclude
    private List<Comment> comments;
    private String header;
    private LocalDateTime publicationDate;
    @Column(length = 3000)
    private String content;
    private boolean anonymous;
    private boolean commentable;
    @Column(unique = true)
    @Enumerated(value = EnumType.STRING)
    private SpecializationSphere sphere;
}
