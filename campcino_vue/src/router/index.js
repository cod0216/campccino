// src/router/index.js
import { createRouter, createWebHistory } from "vue-router";
import BoardView from "@/views/board/BoardView.vue";
import CreatePost from "@/views/post/CreatePost.vue";
import PostDetail from "@/views/post/PostDetail.vue";
import MainView from "@/views/MainView.vue";
import CampDetailView from "@/views/camp/CampDetailView.vue";
import CampStoreView from "@/views/store/CampStoreView.vue";
import CampStoreDetailView from "@/views/store/CampStoreDetailView.vue";
import LoginView from "@/views/user/LoginView.vue";
import JoinView from "@/views/user/JoinView.vue";
import CampSearchView from "@/views/camp/CampSearchView.vue";

const routes = [
  { path: "/", name: "MainView", component: MainView },
  { path: "/board", name: "BoardView", component: BoardView },
  {
    path: "/camps/:id",
    name: "CampDetail",
    component: CampDetailView,
    props: true, // 라우트 파라미터를 props로 전달
  },
  { path: "/create", name: "CreatePost", component: CreatePost },
  { path: "/post/:id", name: "PostDetail", component: PostDetail },
  { path: "/main", name: "Main", component: MainView },
  { path: "/camp/:id", name: "CampDetail", component: CampDetailView },
  { path: "/store", name: "CampStore", component: CampStoreView },
  { path: "/search", name: "CampSearchView", component: CampSearchView },
  {
    path: "/store/:id",
    name: "CampStoreDetail",
    component: CampStoreDetailView,
    props: true,
  },
  { path: "/login", name: "Login", component: LoginView },
  { path: "/join", name: "Join", component: JoinView },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      // 뒤로 가기/앞으로 가기 시 저장된 스크롤 위치로 이동
      return savedPosition;
    } else {
      // 새로운 페이지로 이동할 때 맨 위로 스크롤
      return { top: 0 };
    }
  },
});

export default router;
