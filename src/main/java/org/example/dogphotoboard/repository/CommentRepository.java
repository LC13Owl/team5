package org.example.dogphotoboard.repository;

import org.example.dogphotoboard.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 사진의 모든 댓글 조회
    List<Comment> findByPhotoId(Long photoId);
}
