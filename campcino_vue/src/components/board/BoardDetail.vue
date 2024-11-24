<!-- src/components/board/BoardDetail.vue -->
<template>
  <div class="board-detail-container" v-if="board">
    <h2 class="text-2xl font-bold mb-4">{{ board.boardTitle }}</h2>
    <div class="flex items-center justify-between mb-2">
      <span class="text-gray-600">작성자: {{ board.userId }}</span>
      <span class="text-gray-600">조회수: {{ board.boardView }}</span>
    </div>
    <p class="text-gray-700 mb-4">{{ board.boardContent }}</p>
    <div v-if="board.imgUrl" class="mb-4">
      <img :src="board.imgUrl" alt="게시글 이미지" class="w-full h-auto rounded" />
    </div>
    <p class="text-gray-500">작성일: {{ formattedDate }}</p>

    <!-- 댓글 목록 -->
    <CommentList :boardId="board.boardId" />

    <!-- 댓글 작성 폼 -->
    <CommentForm :boardId="board.boardId" @commentSubmitted="refreshComments" />
  </div>
  <div v-else>
    <p>게시글을 로드 중입니다...</p>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { getBoardById } from '@/api';
import CommentList from './CommentList.vue';
import CommentForm from './CommentForm.vue';

export default {
  name: 'BoardDetail',
  components: {
    CommentList,
    CommentForm,
  },
  props: {
    id: {
      type: String,
      required: true,
    },
  },
  setup(props) {
    const board = ref(null);
    const formattedDate = ref('');
    const commentList = ref(null); // CommentList 컴포넌트의 ref

    const fetchBoard = async () => {
      try {
        const response = await getBoardById(props.id);
        board.value = response;
        formattedDate.value = new Date(response.boardCreatedAt).toLocaleString();
      } catch (error) {
        console.error('게시글을 가져오는 중 오류가 발생했습니다:', error);
      }
    };

    const refreshComments = () => {
      if (commentList.value) {
        commentList.value.fetchComments(1);
      }
    };

    onMounted(() => {
      fetchBoard();
    });

    return {
      board,
      formattedDate,
      commentList,
      refreshComments,
    };
  },
};
</script>

<style scoped>
.board-detail-container {
  padding: 1rem;
}
.board-detail-container img {
  max-height: 400px;
  object-fit: cover;
}
</style>
