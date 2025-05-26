package org.example.dogphotoboard.repository;

import org.example.dogphotoboard.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    // 강아지 종으로 검색
    List<Photo> findByDogBreedContaining(String dogBreed);

    // 키워드로 검색
    List<Photo> findByKeywordsContaining(String keyword);

    // 둘 다 동시에 검색 (선택사항)
    List<Photo> findByDogBreedContainingAndKeywordsContaining(String dogBreed, String keyword);
}
