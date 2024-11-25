import { createRouter, createWebHistory } from "vue-router";
import { useAuthStore } from "@/stores/auth"; // Pinia 스토어 가져오기
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
import ReviewListView from "@/views/review/ReviewListView.vue";

const routes = [
  { path: "/", name: "MainView", component: MainView },
  { path: "/board", name: "BoardView", component: BoardView },
  {
    path: "/camps/:id",
    name: "CampDetail",
    component: CampDetailView,
    props: true,
  },
  { path: "/create", name: "CreatePost", component: CreatePost },
  { path: "/post/:id", name: "PostDetail", component: PostDetail },
  { path: "/main", name: "Main", component: MainView },
  { path: "/store", name: "CampStore", component: CampStoreView },
  {
    path: "/store/:id",
    name: "CampStoreDetail",
    component: CampStoreDetailView,
    props: true,
  },
  { path: "/search", name: "CampSearchView", component: CampSearchView },
  {
    path: "/login",
    name: "Login",
    component: () => LoginView,
    beforeEnter: (to, from, next) => {
      const authStore = useAuthStore();
      if (authStore.isAuthenticated) {
        // 이미 로그인된 상태면 메인 페이지로 이동
        next("/search");
      } else {
        next();
      }
    },
  },
  { path: "/join", name: "Join", component: JoinView },
  { path: "/reviews", name: "ReviewList", component: ReviewListView },

  {
    path: "/profile",
    name: "Profile",
    component: () => import("@/views/ProfileView.vue"),
    meta: { requiresAuth: true }, // 인증 필요
  },
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

router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();
  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    // 인증이 필요한 페이지에 접근하려고 하지만 인증되지 않은 경우 로그인 페이지로 이동
    next("/login");
  } else {
    next();
  }
});

export default router;