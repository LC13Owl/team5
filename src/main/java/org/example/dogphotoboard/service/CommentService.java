package org.example.dogphotoboard.service;

import org.example.dogphotoboard.entity.Comment;
import org.example.dogphotoboard.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // 댓글 작성
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // 특정 사진의 댓글 전체 조회
    public List<Comment> getCommentsByPhotoId(Long photoId) {
        return commentRepository.findByPhotoId(photoId);
    }
}
