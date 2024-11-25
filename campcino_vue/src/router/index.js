// src/router/index.js
import { createRouter, createWebHistory } from "vue-router";
import BoardView from "@/views/board/BoardView.vue";
import CreatePost from "@/components/board/CreateBoard.vue";
import PostDetail from "@/components/board/PostDetail.vue";
import MainView from "@/views/MainView.vue";
import CampDetailView from "@/views/camp/CampDetailView.vue";
import CampStoreView from "@/views/store/CampStoreView.vue";
import CampStoreDetailView from "@/views/store/CampStoreDetailView.vue";
import LoginView from "@/views/user/LoginView.vue";
import JoinView from "@/views/user/JoinView.vue";
import CampSearchView from "@/views/camp/CampSearchView.vue";
import ReviewListView from "@/views/review/ReviewListView.vue"; // 새로운 뷰 임포트
import ReviewForm from "@/components/camp/ReviewForm.vue";
import BoardList from '@/components/board/BoardList.vue';
import BoardDetail from '@/components/board/BoardDetail.vue';
import CreateBoard from '@/components/board/CreateBoard.vue';
const routes = [
  { path: "/", name: "MainView", component: MainView },
  { path: "/boards", name: "BoardView", component: BoardView },
<<<<<<< HEAD
=======
  { path: "/boards/:id", name: "BoardDetail", component: BoardDetail, props: true },
  { path: "/boards/create", name: "CreateBoard", component: CreateBoard },
>>>>>>> ec005d9df6d9c4bddd045ee78461aead6298464d
  {
    path: "/camps/:id",
    name: "CampDetail",
    component: CampDetailView,
    props: true,
  },
  // 별도의 리뷰 작성 페이지를 원할 경우
  {
    path: "/camps/:id/review",
    name: "ReviewForm",
    component: ReviewForm,
    props: true,
  },
  { path: "/main", name: "Main", component: MainView },
  // 중복된 라우트 제거 ("/camp/:id")
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
  { path: "/reviews", name: "ReviewList", component: ReviewListView }, // 새로운 라우트 추가
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
