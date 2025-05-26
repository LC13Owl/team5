package org.example.dogphotoboard.service;

import org.example.dogphotoboard.entity.Photo;
import org.example.dogphotoboard.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    // 사진 저장
    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    // 모든 사진 조회 (테스트용)
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }
    // 강아지 종으로 검색
    public List<Photo> searchByDogBreed(String dogBreed) {
        return photoRepository.findByDogBreedContaining(dogBreed);
    }

    // 키워드로 검색
    public List<Photo> searchByKeyword(String keyword) {
        return photoRepository.findByKeywordsContaining(keyword);
    }

    // 둘 다 검색 (선택)
    public List<Photo> searchByBreedAndKeyword(String dogBreed, String keyword) {
        return photoRepository.findByDogBreedContainingAndKeywordsContaining(dogBreed, keyword);
    }

    // 👉 좋아요 수 증가
    public Photo addLike(Long photoId) {
        Optional<Photo> optionalPhoto = photoRepository.findById(photoId);
        if (optionalPhoto.isPresent()) {
            Photo photo = optionalPhoto.get();
            photo.setLikes(photo.getLikes() + 1);  // 좋아요 수 1 증가
            return photoRepository.save(photo);    // DB 업데이트 후 반환
        } else {
            throw new RuntimeException("Photo not found with id: " + photoId);
        }
    }
}
