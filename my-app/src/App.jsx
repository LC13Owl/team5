import { useState, useRef } from 'react'
import './App.css'

const defaultPosts = [
  {
    id: 1,
    username: 'puppy_lover',
    breed: '비숑 프리제',
    profilePic: 'https://placekitten.com/40/40',
    photo: 'https://placedog.net/500/300?id=1',
    caption: '산책 나온 귀요미 🐶 #비숑 #산책 #귀여워',
    comments: [],
  },
  {
    id: 2,
    username: 'dog_hero',
    breed: '시바견',
    profilePic: 'https://placekitten.com/41/41',
    photo: 'https://placedog.net/500/300?id=2',
    caption: '오늘은 공원에서 🐕 #시바 #공원',
    comments: [],
  },
  {
    id: 3,
    username: 'fluffy_dog',
    breed: '말티즈',
    profilePic: 'https://placekitten.com/42/42',
    photo: 'https://placedog.net/500/300?id=3',
    caption: '간식 줘! 🍖 #말티즈 #간식 #귀여움폭발',
    comments: [],
  },
]

function App() {
  const [posts, setPosts] = useState(defaultPosts)
  const [likedPosts, setLikedPosts] = useState([])
  const [searchText, setSearchText] = useState('')
  const [uploadImg, setUploadImg] = useState(null)
  const [uploadText, setUploadText] = useState('')
  const fileInput = useRef()
  const [commentInputs, setCommentInputs] = useState({})

  // 좋아요 토글
  const toggleLike = (postId) => {
    setLikedPosts((prev) =>
      prev.includes(postId)
        ? prev.filter((id) => id !== postId)
        : [...prev, postId]
    )
  }

  // 이미지 업로드 시 미리보기
  const handleImgChange = (e) => {
    const file = e.target.files[0]
    if (file) setUploadImg(URL.createObjectURL(file))
  }

  // 새 게시물 업로드
  const handleUpload = (e) => {
    e.preventDefault()
    if (!uploadImg || !uploadText) return
    const newPost = {
      id: Date.now(),
      username: '나',
      breed: '업로드',
      profilePic: 'https://placekitten.com/43/43',
      photo: uploadImg,
      caption: uploadText,
      comments: [],
    }
    setPosts([newPost, ...posts])
    setUploadImg(null)
    setUploadText('')
    fileInput.current.value = ''
  }

  // 댓글 입력 값 변경
  const handleCommentInput = (postId, value) => {
    setCommentInputs({ ...commentInputs, [postId]: value })
  }

  // 댓글 추가
  const addComment = (postId) => {
    setPosts(posts =>
      posts.map(post =>
        post.id === postId
          ? {
              ...post,
              comments: [...(post.comments || []), commentInputs[postId] || ''],
            }
          : post
      )
    )
    setCommentInputs({ ...commentInputs, [postId]: '' })
  }

  // 검색 필터
  const filteredPosts = posts.filter((post) => {
    const lower = searchText.toLowerCase()
    return (
      post.username.toLowerCase().includes(lower) ||
      post.breed.toLowerCase().includes(lower) ||
      post.caption.toLowerCase().includes(lower)
    )
  })

  return (
    <div className="app-background">
      <header className="search-header">
        <input
          type="text"
          placeholder="작성자, 강아지 종, 키워드로 검색"
          value={searchText}
          onChange={(e) => setSearchText(e.target.value)}
        />
      </header>

      {/* 사진 업로드 폼 */}
      <div className="upload-form">
        <form onSubmit={handleUpload}>
          <input
            type="file"
            accept="image/*"
            onChange={handleImgChange}
            ref={fileInput}
          />
          <input
            type="text"
            placeholder="게시글 멘트를 입력하세요"
            value={uploadText}
            onChange={(e) => setUploadText(e.target.value)}
            maxLength={100}
          />
          <button type="submit">업로드</button>
          {uploadImg && (
            <div style={{ marginTop: '10px' }}>
              <img src={uploadImg} alt="preview" width={200} />
            </div>
          )}
        </form>
      </div>

      {/* 피드 */}
      <div className="feed-container">
        {filteredPosts.map((post) => (
          <div key={post.id} className="post-card">
            <div className="post-header">
              <img
                src={post.profilePic}
                alt="profile"
                className="profile-pic"
              />
              <div className="user-info">
                <div className="username">{post.username}</div>
                <div className="dog-breed">{post.breed}</div>
              </div>
            </div>
            <img src={post.photo} alt="dog" className="post-image" />
            <div className="post-actions">
              <button
                onClick={() => toggleLike(post.id)}
                className={likedPosts.includes(post.id) ? 'liked' : ''}
              >
                {likedPosts.includes(post.id) ? '❤️' : '🤍'} 좋아요
              </button>
              <button>💾 저장</button>
              <button>🔗 공유</button>
            </div>
            <div className="post-caption">{post.caption}</div>

            {/* 댓글 리스트 */}
            <div className="comments-list">
              {(post.comments || []).map((c, idx) => (
                <div className="comment" key={idx}>💬 {c}</div>
              ))}
            </div>

            {/* 댓글 입력창 */}
            <div className="comment-form">
              <input
                type="text"
                placeholder="댓글 달기"
                value={commentInputs[post.id] || ''}
                onChange={(e) => handleCommentInput(post.id, e.target.value)}
                maxLength={80}
              />
              <button
                type="button"
                onClick={() => addComment(post.id)}
                disabled={!(commentInputs[post.id] || '').trim()}
              >
                등록
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default App
