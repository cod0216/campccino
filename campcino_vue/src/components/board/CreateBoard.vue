<!-- src/components/board/CreatePost.vue -->
<template>
  <div class="create-post-container">
    <h2 class="text-2xl font-bold mb-4">새 게시글 작성</h2>
    <form @submit.prevent="submitPost">
      <div class="mb-4">
        <label class="block text-gray-700">제목</label>
        <input v-model="post.title" type="text" class="w-full border rounded p-2" required />
      </div>
      <div class="mb-4">
        <label class="block text-gray-700">내용</label>
        <textarea v-model="post.content" class="w-full border rounded p-2" rows="5" required></textarea>
      </div>
      <div class="mb-4">
        <label class="block text-gray-700">카테고리</label>
        <select v-model="post.category" class="w-full border rounded p-2" required>
          <option value="" disabled>카테고리를 선택하세요</option>
          <option v-for="category in categories" :key="category" :value="category">{{ category }}</option>
        </select>
      </div>
      <div class="mb-4">
        <label class="block text-gray-700">이미지 URL (선택 사항)</label>
        <input v-model="post.imgUrl" type="text" class="w-full border rounded p-2" />
      </div>
      <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded">작성</button>
    </form>
  </div>
</template>

<script>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { createBoard } from '@/api';

export default {
  name: 'CreatePost',
  setup() {
    const router = useRouter();
    const post = ref({
      title: '',
      content: '',
      category: '',
      imgUrl: '',
    });

    const categories = ["일반", "질문", "공지", "자유", "정보", "기타"];

    const submitPost = async () => {
      try {
        await createBoard({
          boardTitle: post.value.title,
          boardContent: post.value.content,
          category: post.value.category,
          imgUrl: post.value.imgUrl,
        });
        alert('게시글이 성공적으로 작성되었습니다!');
        // 게시글 목록으로 이동
        router.push('/boards'); // useRouter를 사용하여 네비게이션
      } catch (error) {
        console.error('게시글 작성 중 오류가 발생했습니다:', error);
        alert('게시글 작성에 실패했습니다.');
      }
    };

    return {
      post,
      categories,
      submitPost,
    };
  },
};
</script>

<style scoped>
.create-post-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 2rem;
}
</style>
