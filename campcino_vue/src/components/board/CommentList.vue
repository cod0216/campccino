<!-- src/components/board/CommentList.vue -->
<template>
  <div class="comment-list-container">
    <h3 class="text-lg font-bold mb-4">댓글</h3>

    <div v-if="comments.items.length === 0">
      <p>작성된 댓글이 없습니다.</p>
    </div>
    <div v-else>
      <div v-for="comment in comments.items" :key="comment.commentId" class="comment-item mb-4 p-4 border rounded">
        <div class="flex justify-between mb-2">
          <span class="font-semibold">{{ comment.userId }}</span>
          <span class="text-sm text-gray-500">{{ formatDate(comment.commentCreatedAt) }}</span>
        </div>
        <p>{{ comment.commentContent }}</p>
      </div>

      <!-- 페이징 처리 -->
      <div class="pagination flex justify-center mt-4">
        <button
          @click="fetchComments(currentPage - 1)"
          :disabled="currentPage === 1"
          class="px-3 py-1 mr-2 bg-gray-200 rounded disabled:opacity-50"
        >
          이전
        </button>
        <span>페이지 {{ currentPage }} / {{ totalPages }}</span>
        <button
          @click="fetchComments(currentPage + 1)"
          :disabled="currentPage === totalPages"
          class="px-3 py-1 ml-2 bg-gray-200 rounded disabled:opacity-50"
        >
          다음
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted, watch, defineExpose } from 'vue';
import { getCommentsByBoardId } from '@/api';

export default {
  name: 'CommentList',
  props: {
    boardId: {
      type: Number,
      required: true,
    },
  },
  setup(props) {
    const comments = ref({
      items: [],
      currentPage: 1,
      totalPages: 1,
      totalItems: 0,
    });
    const currentPage = ref(1);
    const totalPages = ref(1);

    const fetchComments = async (page = 1) => {
      if (page < 1 || page > totalPages.value) return;
      try {
        const response = await getCommentsByBoardId(props.boardId, page, 10);
        comments.value = response;
        currentPage.value = response.currentPage;
        totalPages.value = response.totalPages;
      } catch (error) {
        console.error('댓글을 가져오는 중 오류가 발생했습니다:', error);
      }
    };

    const formatDate = (dateString) => {
      const options = { year: 'numeric', month: 'short', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return new Date(dateString).toLocaleDateString(undefined, options);
    };

    onMounted(() => {
      fetchComments();
    });

    watch(() => props.boardId, () => {
      fetchComments(1);
    });

    // fetchComments 메서드를 부모 컴포넌트에 노출
    defineExpose({
      fetchComments,
    });

    return {
      comments,
      currentPage,
      totalPages,
      fetchComments,
      formatDate,
    };
  },
};
</script>

<style scoped>
.comment-list-container {
  margin-top: 2rem;
}

.comment-item {
  background-color: #f9f9f9;
}
</style>
