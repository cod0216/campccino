// src/api.js
import axios from "axios";
// Backend API base URL
const BASE_URL = "http://localhost:8080/api";


// 캠프 API
export const getCamps = async (regionId = 0, categoryId = 0, query = "") => {
  try {
    const response = await axios.get(`${BASE_URL}/camps`, {
      params: {
        region: regionId,
        category: categoryId,
        text: query,
      },
    });
    return response.data;
  } catch (error) {
    console.error("캠핑장 데이터를 가져오는 중 오류 발생:", error);
    throw error;
  }
};

export const getRegions = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/regions`);
    return response.data;
  } catch (error) {
    console.error("지역 데이터를 가져오는 중 오류 발생:", error);
    throw error;
  }
};

export const getCategories = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/categories`);
    return response.data;
  } catch (error) {
    console.error("카테고리 데이터를 가져오는 중 오류 발생:", error);
    throw error;
  }
};

// 보드 API
export const getBoards = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/boards`);
    return response.data;
  } catch (error) {
    console.error("보드 데이터를 가져오는 중 오류 발생:", error);
    throw error;
  }
};

export const getBoardById = async (id) => {
  try {
    const response = await axios.get(`${BASE_URL}/boards/${id}`);
    return response.data;
  } catch (error) {
    console.error("ID로 보드를 가져오는 중 오류 발생:", error);
    throw error;
  }
};

export const createBoard = async (boardData) => {
  try {
    const response = await axios.post(`${BASE_URL}/boards`, boardData);
    return response.data;
  } catch (error) {
    console.error("보드를 생성하는 중 오류 발생:", error);
    throw error;
  }
};

export const updateBoard = async (id, updatedData) => {
  try {
    const response = await axios.put(`${BASE_URL}/boards/${id}`, updatedData);
    console.log("보드 업데이트 API 응답:", response.data); // 디버깅
    return response.data;
  } catch (error) {
    console.error("보드를 업데이트하는 중 오류 발생:", error);
    throw error;
  }
};
