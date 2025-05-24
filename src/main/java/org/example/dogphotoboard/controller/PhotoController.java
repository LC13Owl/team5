package org.example.dogphotoboard.controller;

import org.example.dogphotoboard.entity.Photo;
import org.example.dogphotoboard.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

import org.example.dogphotoboard.entity.Comment;
import org.example.dogphotoboard.service.CommentService;

@RestController
@RequestMapping("/api/photos")


public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private CommentService commentService;



    // 사진 업로드 (POST)
    @PostMapping("/upload")
    public Photo uploadPhoto(@RequestBody Photo photo) {
        return photoService.savePhoto(photo);
    }
    // 전체 사진 목록 반환
    @GetMapping
    public List<Photo> getPhotos() {
        return photoService.getAllPhotos();
    }

    // 🔍 강아지 종으로 검색
    @GetMapping("/searchByBreed")
    public List<Photo> searchByBreed(@RequestParam String dogBreed) {
        return photoService.searchByDogBreed(dogBreed);
    }

    // 🔍 키워드로 검색
    @GetMapping("/searchByKeyword")
    public List<Photo> searchByKeyword(@RequestParam String keyword) {
        return photoService.searchByKeyword(keyword);
    }

    // 🔍 둘 다 검색 (선택)
    @GetMapping("/search")
    public List<Photo> searchByBreedAndKeyword(@RequestParam String dogBreed, @RequestParam String keyword) {
        return photoService.searchByBreedAndKeyword(dogBreed, keyword);
    }

    // 👉 좋아요 추가 API
    @PostMapping("/{id}/like")
    public Photo likePhoto(@PathVariable Long id) {
        return photoService.addLike(id);
    }

    // 댓글 작성
    @PostMapping("/{photoId}/comments")
    public Comment addComment(@PathVariable Long photoId, @RequestBody Comment comment) {
        comment.setPhotoId(photoId);
        return commentService.addComment(comment);
    }

    // 특정 사진의 댓글 전체 조회
    @GetMapping("/{photoId}/comments")
    public List<Comment> getComments(@PathVariable Long photoId) {
        return commentService.getCommentsByPhotoId(photoId);
    }

}
