package org.example.dogphotoboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;         // 댓글 고유 ID

    private Long photoId;    // 어떤 사진의 댓글인지
    private Long userId;     // 댓글 작성자 ID
    private String content;  // 댓글 내용
}
