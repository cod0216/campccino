<!-- src/components/board/CommentForm.vue -->
<template>
  <div class="comment-form mt-4">
    <h3 class="text-xl font-semibold mb-2">댓글 작성</h3>
    <form @submit.prevent="submitComment">
      <textarea v-model="commentContent" class="w-full border rounded p-2 mb-2" rows="3" placeholder="댓글을 작성하세요..." required></textarea>
      <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded">작성</button>
    </form>
  </div>
</template>

<script>
import { ref } from 'vue';
import { createComment } from '@/api';

export default {
  name: 'CommentForm',
  props: {
    boardId: {
      type: Number,
      required: true,
    },
  },
  emits: ['commentSubmitted'], // 이벤트 선언
  setup(props, { emit }) {
    const commentContent = ref('');

    const submitComment = async () => {
      try {
        await createComment(props.boardId, { commentContent: commentContent.value });
        commentContent.value = '';
        emit('commentSubmitted'); // 댓글 작성 완료 이벤트 발생
      } catch (error) {
        console.error('댓글 작성 중 오류가 발생했습니다:', error);
        alert('댓글 작성에 실패했습니다.');
      }
    };

    return {
      commentContent,
      submitComment,
    };
  },
};
</script>

<style scoped>
.comment-form {
  background-color: #f5f5f5;
  padding: 1rem;
  border-radius: 5px;
}
</style>
