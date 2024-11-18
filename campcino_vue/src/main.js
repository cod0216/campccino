// src/main.js
import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "./assets/index.css"; // Tailwind CSS

createApp(App).use(router).mount("#app");
