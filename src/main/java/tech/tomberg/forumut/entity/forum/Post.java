package tech.tomberg.forumut.entity.forum;

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
    private User author;
    @OneToMany(mappedBy = "relatedPost")
    @ToString.Exclude
    private List<Comment> comments;
    private String header;
    private LocalDateTime publicationDate;
    private String content;
    private boolean anonymous;
    private boolean commentable;
    private SpecializationSphere sphere;
}
