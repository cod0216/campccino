// src/stores/auth.js
import { defineStore } from "pinia";
import { loginUser, logoutUser, refreshToken } from "@/api";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    isAuthenticated: false,
    user: null, // 사용자 정보
  }),
  actions: {
    async initializeAuth() {
      try {
        await refreshToken(); // 리프레시 토큰으로 상태 초기화
        this.isAuthenticated = true;
      } catch {
        this.isAuthenticated = false;
      }
    },
    async loginUser(credentials) {
      try {
        const accessToken = await loginUser(credentials);
        localStorage.setItem("accessToken", accessToken); // 액세스 토큰 로컬 스토리지에 저장
        this.isAuthenticated = true;
      } catch (error) {
        console.error("로그인 실패:", error.response?.data?.message || "로그인에 실패했습니다.");
        throw new Error(error);
      }
    },
    async logoutUser() {
      try {
        await logoutUser();
        localStorage.removeItem("accessToken"); // 로컬 스토리지에서 액세스 토큰 제거
        this.isAuthenticated = false;
        this.user = null;
      } catch (error) {
        console.error("로그아웃 실패:", error);
        throw error;
      }
    },
    async refreshAccessToken() {
      try {
        await refreshToken();
        this.isAuthenticated = true;
      } catch (error) {
        console.error("토큰 갱신 실패:", error);
        this.isAuthenticated = false;
        throw error;
      }
    },
    async getAccessToken() {
      return localStorage.getItem("accessToken"); // 로컬 스토리지에서 액세스 토큰 가져오기
    },
  },
});
