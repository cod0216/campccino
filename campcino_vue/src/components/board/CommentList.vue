<template>
  <div class="comment-list-container">
    <h3 class="text-lg font-bold mb-4">댓글</h3>

    <div v-if="comments.items.length === 0">
      <p>작성된 댓글이 없습니다.</p>
    </div>
    <div v-else>
      <div
        v-for="comment in comments.items"
        :key="comment.commentId"
        class="comment-item mb-4 p-4 border rounded"
      >
        <div class="flex justify-between mb-2">
          <span class="font-semibold">{{ comment.userId }}</span>
          <span class="text-sm text-gray-500">{{
            formatDate(comment.commentCreatedAt)
          }}</span>
        </div>

        <!-- 댓글 내용 표시 또는 수정 -->
        <div v-if="isEditing === comment.commentId">
          <textarea
            v-model="editingContent"
            class="w-full border rounded p-2 mb-2"
            rows="3"
          ></textarea>
          <div class="flex space-x-4">
            <button
              @click="saveEdit(comment.commentId)"
              class="px-3 py-1 bg-green-500 text-white rounded hover:bg-green-600"
            >
              저장
            </button>
            <button
              @click="cancelEdit"
              class="px-3 py-1 bg-gray-500 text-white rounded hover:bg-gray-600"
            >
              취소
            </button>
          </div>
        </div>
        <div v-else>
          <p class="mb-2">{{ comment.commentContent }}</p>

          <!-- 댓글 작성자와 로그인 사용자가 같은 경우 수정, 삭제 버튼 -->
          <div
            v-if="isCommentOwner(comment.userId)"
            class="flex space-x-4 mt-2"
          >
            <button
              @click="startEdit(comment.commentId, comment.commentContent)"
              class="px-3 py-1 bg-blue-500 text-white rounded hover:bg-blue-600"
            >
              수정
            </button>
            <button
              @click="deleteComment(comment.commentId)"
              class="px-3 py-1 bg-red-500 text-white rounded hover:bg-red-600"
            >
              삭제
            </button>
          </div>
        </div>
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
import { ref, onMounted, watch, defineExpose } from "vue";
import { useAuthStore } from "@/stores/auth"; // 로그인 사용자 정보 가져오기
import { getCommentsByBoardId } from "@/api";

export default {
  name: "CommentList",
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
    const authStore = useAuthStore(); // 로그인 사용자 정보 가져오기

    const isEditing = ref(null); // 현재 수정 중인 댓글 ID
    const editingContent = ref(""); // 수정 중인 댓글 내용

    const fetchComments = async (page = 1) => {
      if (page < 1 || page > totalPages.value) return;
      try {
        const response = await getCommentsByBoardId(props.boardId, page, 10);
        comments.value = response;
        currentPage.value = response.currentPage;
        totalPages.value = response.totalPages;
      } catch (error) {
        console.error("댓글을 가져오는 중 오류가 발생했습니다:", error);
      }
    };

    const formatDate = (dateString) => {
      const options = {
        year: "numeric",
        month: "short",
        day: "numeric",
        hour: "2-digit",
        minute: "2-digit",
      };
      return new Date(dateString).toLocaleDateString(undefined, options);
    };

    // 작성자와 로그인 사용자가 같은지 확인
    const isCommentOwner = (commentUserId) => {
      return commentUserId === authStore.user?.id;
    };

    // 수정 시작
    const startEdit = (commentId, content) => {
      isEditing.value = commentId;
      editingContent.value = content;
    };

    // 수정 저장
    const saveEdit = async (commentId) => {
      try {
        // TODO: 댓글 수정 API 호출
        console.log("수정된 내용:", editingContent.value);
        alert(`수정된 댓글 ID: ${commentId}, 내용: ${editingContent.value}`);
        isEditing.value = null; // 수정 상태 초기화
        fetchComments(currentPage.value); // 수정 후 댓글 목록 갱신
      } catch (error) {
        console.error("댓글 수정 중 오류 발생:", error);
        alert("댓글 수정에 실패했습니다.");
      }
    };

    // 수정 취소
    const cancelEdit = () => {
      isEditing.value = null;
      editingContent.value = ""; // 수정 내용 초기화
    };

    // 댓글 삭제 (임시)
    const deleteComment = (commentId) => {
      alert(`삭제 기능은 아직 구현되지 않았습니다. (댓글 ID: ${commentId})`);
    };

    onMounted(() => {
      fetchComments();
    });

    watch(
      () => props.boardId,
      () => {
        fetchComments(1);
      }
    );

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
      isEditing,
      editingContent,
      isCommentOwner,
      startEdit,
      saveEdit,
      cancelEdit,
      deleteComment,
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
