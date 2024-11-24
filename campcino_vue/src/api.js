// src/api.js
import axios from "axios";
import qs from "qs"; // qs 라이브러리 임포트

// Backend API base URL
const BASE_URL = "http://localhost:8080/api";

// Axios 인스턴스 생성
const apiClient = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
  paramsSerializer: params => {
    return qs.stringify(params, { arrayFormat: 'repeat' }); // 'repeat' 옵션 사용
  },
});

// 캠프 API
export const getCamps = (region, categories, query) => {
  return apiClient
    .get("/camps", {
      params: {
        region,
        category: categories, // Ensure categories are sent as an array
        text: query,
      },
      paramsSerializer: (params) => {
        // Serialize arrays correctly
        const searchParams = new URLSearchParams();
        if (params.region) searchParams.append("region", params.region);
        if (params.category && Array.isArray(params.category)) {
          params.category.forEach((cat) =>
            searchParams.append("category", cat)
          );
        }
        if (params.text) searchParams.append("text", params.text);
        return searchParams.toString();
      },
    })
    .then((res) => res.data);
};

export const getRegions = () =>
  apiClient.get("/regions").then((res) => res.data);

export const getCategories = () =>
  apiClient.get("/categories").then((res) => res.data);

/**
 * 게시글 목록 조회
 * @param {number} region - 지역 필터 (0은 전체)
 * @param {Array<string>} categories - 카테고리 필터
 * @param {string} text - 검색 텍스트
 * @param {number} page - 페이지 번호
 * @param {number} size - 페이지 당 게시글 수
 * @param {string} sortBy - 정렬 기준
 * @param {string} sortOrder - 정렬 순서
 * @returns {Promise<object>}
 */
export const getBoards = (
  categories = ["일반", "질문", "공지", "자유", "정보", "기타"],
  text = "",
  page = 1,
  size = 10,
  sortBy = "board_created_at",
  sortOrder = "DESC"
) => {
  return apiClient.get("/boards", {
    params: {categories, text, page, size, sortBy, sortOrder },
    paramsSerializer: params => {
      return qs.stringify(params, { arrayFormat: 'repeat' }); // 'repeat' 옵션 사용
    },
  }).then((res) => res.data);
};

/**
 * 특정 게시글 조회
 * @param {number} id - 게시글 ID
 * @returns {Promise<object>}
 */
export const getBoardById = (id) => {
  return apiClient.get(`/boards/${id}`).then((res) => res.data);
};

/**
 * 게시글 작성
 * @param {object} boardData - 게시글 데이터
 * @returns {Promise<object>}
 */
export const createBoard = (boardData) => {
  return apiClient.post("/boards", boardData).then((res) => res.data);
};

export const updateBoard = async (id, updatedData) => {
  try {
    const response = await apiClient.put(`/boards/${id}`, updatedData);
    console.log("보드 업데이트 API 응답:", response.data); // 디버깅
    return response.data;
  } catch (error) {
    console.error("보드를 업데이트하는 중 오류 발생:", error);
    throw error;
  }
};

/**
 * 댓글 목록 조회 (페이징 적용)
 * @param {number} boardId - 게시글 ID
 * @param {number} page - 페이지 번호
 * @param {number} size - 페이지 당 댓글 수
 * @returns {Promise<object>}
 */
export const getCommentsByBoardId = (boardId, page = 1, size = 10) => {
  return apiClient
    .get(`/boards/${boardId}/comments`, {
      params: { page, size },
    })
    .then((res) => res.data);
};

/**
 * 댓글 작성
 * @param {number} boardId - 게시글 ID
 * @param {object} commentData - 댓글 데이터
 * @returns {Promise<string>}
 */
export const createComment = (boardId, commentData) => {
  return apiClient
    .post(`/boards/${boardId}/comments`, commentData)
    .then((res) => res.data);
};

// 캠프 상세 조회 API 수정
export const getCampById = async (campId) => {
  try {
    const response = await apiClient.get(`/camps/${campId}`);
    console.log(`캠핑장 ID ${campId}로부터 받은 데이터:`, response.data); // 디버깅 로그
    return response.data; // 데이터 형식 확인 필요
  } catch (error) {
    console.error(
      `캠핑장 ID ${campId}로 데이터를 가져오는 중 오류 발생:`,
      error
    );
    throw error;
  }
};

export const getCampReviewSummaries = (sortBy, sortOrder, page, size) => {
  return apiClient
    .get("/reviews", {
      params: {
        sortBy,
        sortOrder,
        page,
        size,
      },
    })
    .then((res) => res.data);
};

// 캠핑장 리뷰 페이징 조회 API 추가
export const getPaginatedReviewsByCampId = (campId, page, size) => {
  return apiClient
    .get(`/camps/${campId}/reviews`, {
      params: {
        page,
        size,
      },
    })
    .then((res) => res.data);
};


// JWT 토큰 저장/삭제 헬퍼
export const setAuthHeader = (token) => {
  apiClient.defaults.headers.common["Authorization"] = `Bearer ${token}`;
};

export const clearAuthHeader = () => {
  delete apiClient.defaults.headers.common["Authorization"];
};

// 로그인 API
export const login = (credentials) => {
  return apiClient.post("/user/login", credentials).then((res) => res.headers);
};

// 로그아웃 API
export const logout = (refreshToken) => {
  return apiClient.post("/user/logout", {}, {
    headers: {
      Authorization: refreshToken,
    },
  });
};

// Access Token 재발급
export const refreshToken = (refreshToken) => {
  return apiClient.post("/user/refresh", {}, {
    headers: {
      Authorization: refreshToken,
    },
  });
};
