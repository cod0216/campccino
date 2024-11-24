// src/api.js
import axios from "axios";

// Backend API base URL
const BASE_URL = "http://localhost:8080";

// Axios 인스턴스 생성
const apiClient = axios.create({
  baseURL: BASE_URL,
  withCredentials: true, // 쿠키를 자동으로 포함하도록 설정
  headers: {
    "Content-Type": "application/json",
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

// 보드 API (unchanged)
export const getBoards = async () => {
  try {
    const response = await apiClient.get("/boards");
    return response.data;
  } catch (error) {
    console.error("보드 데이터를 가져오는 중 오류 발생:", error);
    throw error;
  }
};

export const getBoardById = async (id) => {
  try {
    const response = await apiClient.get(`/boards/${id}`);
    return response.data;
  } catch (error) {
    console.error("ID로 보드를 가져오는 중 오류 발생:", error);
    throw error;
  }
};

export const createBoard = async (boardData) => {
  try {
    const response = await apiClient.post("/boards", boardData);
    return response.data;
  } catch (error) {
    console.error("보드를 생성하는 중 오류 발생:", error);
    throw error;
  }
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

// 사용자 로그인
export const loginUser = async (credentials) => {
  const response = await apiClient.post("/user/login", credentials);
  return response.data.accessToken; // 액세스 토큰 반환
};
// 사용자 로그아웃
export const logoutUser = () => {
  return apiClient.post("/user/logout");
};

// Refresh Token 요청 (토큰 갱신)
export const refreshToken = () => {
  return apiClient.post("/user/refresh");
};

export default apiClient;