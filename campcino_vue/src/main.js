// src/main.js
import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "./assets/index.css"; // Tailwind CSS
import axios from 'axios';


createApp(App).use(router).mount("#app");
