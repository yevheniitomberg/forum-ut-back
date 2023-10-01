package tech.tomberg.forumut.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.tomberg.forumut.entity.forum.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}