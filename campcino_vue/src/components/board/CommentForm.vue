<!-- src/components/board/CommentForm.vue -->
<template>
  <div class="comment-form-container mt-6">
    <h3 class="text-lg font-bold mb-4">댓글 작성</h3>
    <form @submit.prevent="submitComment" class="flex flex-col gap-4">
      <div>
        <label class="block text-sm font-medium text-gray-700">댓글 내용</label>
        <textarea
          v-model="newComment.commentContent"
          required
          rows="4"
          class="mt-1 block w-full border border-gray-300 rounded-md p-2"
          placeholder="댓글을 입력하세요..."
        ></textarea>
      </div>
      <div class="flex gap-4">
        <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded">작성</button>
        <button type="button" @click="resetForm" class="px-4 py-2 bg-gray-500 text-white rounded">취소</button>
      </div>
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
  setup(props) {
    const newComment = ref({
      commentContent: '',
    });

    const submitComment = async () => {
      if (!newComment.value.commentContent) {
        alert('댓글 내용을 입력해주세요.');
        return;
      }
      try {
        await createComment(props.boardId, newComment.value);
        alert('댓글이 성공적으로 작성되었습니다!');
        resetForm();
        // 부모 컴포넌트에 댓글이 추가되었음을 알릴 수 있음 (예: emit 이벤트)
      } catch (error) {
        console.error('댓글 작성 중 오류가 발생했습니다:', error);
        alert('댓글 작성에 실패했습니다.');
      }
    };

    const resetForm = () => {
      newComment.value.commentContent = '';
    };

    return {
      newComment,
      submitComment,
      resetForm,
    };
  },
};
</script>

<style scoped>
.comment-form-container {
  background-color: #f9f9f9;
  padding: 1rem;
  border-radius: 0.5rem;
}
</style>
