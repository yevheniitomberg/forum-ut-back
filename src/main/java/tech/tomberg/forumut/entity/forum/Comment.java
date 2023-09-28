package tech.tomberg.forumut.entity.forum;

import jakarta.persistence.*;
import lombok.*;
import tech.tomberg.forumut.entity.auth.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User author;
    private String content;
    private LocalDateTime publicationDate;
    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    private Post relatedPost;
}
