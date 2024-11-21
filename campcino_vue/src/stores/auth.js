// src/stores/auth.js
import { defineStore } from "pinia";
import { login, logout, refreshToken, setAuthHeader, clearAuthHeader } from "@/api";
export const useAuthStore = defineStore("auth", {
  state: () => ({
    accessToken: null,
    refreshToken: null,
  }),
  actions: {
    async login(credentials) {
      const response = await axios.post("/user/login", credentials);
      return response; // 서버 응답 반환
    },
    setTokens({ accessToken, refreshToken }) {
      this.accessToken = accessToken;
      this.refreshToken = refreshToken;

      // Local Storage에 저장
      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("refreshToken", refreshToken);
    },
    loadTokens() {
      this.accessToken = localStorage.getItem("accessToken");
      this.refreshToken = localStorage.getItem("refreshToken");
    },
    clearTokens() {
      this.accessToken = null;
      this.refreshToken = null;

      // Local Storage에서 제거
      localStorage.removeItem("accessToken");
      localStorage.removeItem("refreshToken");
    },
  },
});
