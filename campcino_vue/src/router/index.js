// src/router/index.js
import { createRouter, createWebHistory } from "vue-router";
import BoardView from "../views/BoardView.vue";
import CreatePost from "../views/CreatePost.vue";
import PostDetail from "../views/PostDetail.vue";
import MainView from "../views/MainView.vue";
import CampDetailView from "../views/CampDetailView.vue";
import CampStoreView from "../views/CampStoreView.vue";
import CampStoreDetailView from "../views/CampStoreDetailView.vue";
import LoginView from "../views/LoginView.vue";
import JoinView from "../views/JoinView.vue";
import CampSearchView from "../views/CampSearchView.vue";

const routes = [
  { path: "/", name: "CampSearch", component: CampSearchView },
  { path: "/board", name: "BoardView", component: BoardView },
  { path: "/create", name: "CreatePost", component: CreatePost },
  { path: "/post/:id", name: "PostDetail", component: PostDetail },
  { path: "/main", name: "Main", component: MainView },
  { path: "/camp/:id", name: "CampDetail", component: CampDetailView },
  { path: "/store", name: "CampStore", component: CampStoreView },
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
