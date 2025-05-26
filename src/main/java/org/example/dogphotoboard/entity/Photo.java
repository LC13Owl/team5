package org.example.dogphotoboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;     // 사진 URL
    private String caption;      // 멘트
    private String dogBreed;     // 강아지 종
    private String hashtags;     // 해시태그
    private String keywords;     // 키워드

    private Long userId;         // 작성자 ID
    private int likes = 0;       //좋아요 수 추가
}
