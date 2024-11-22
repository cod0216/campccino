// src/main.js
import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import { createPinia } from "pinia"; // Pinia 임포트 추가
import "./assets/index.css"; // Tailwind CSS
import axios from "axios";
import '@fortawesome/fontawesome-free/css/all.css';



axios.defaults.baseURL = "http://localhost:8080"; // 백엔드 서버 URL


// Font Awesome 설정
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import {
  faBolt,
  faHotTub,
  faWifi,
  faFire,
  faWalking,
  faSwimmer,
  faPlay,
  faShoppingCart,
  faRestroom,
  faShower,
  faSink,
  faFish,
  faHiking,
  faUmbrellaBeach,
  faWater,
  faMountain,
  faSwimmingPool,
  faUsers,
  faSeedling,
  faChild,
  faBed,
  faTv,
  faUtensils,
  faSnowflake,
  faTools,
  faQuestionCircle,
} from "@fortawesome/free-solid-svg-icons";

// 사용할 아이콘을 라이브러리에 추가
library.add(
  faBolt,
  faHotTub,
  faWifi,
  faFire,
  faWalking,
  faSwimmer,
  faPlay,
  faShoppingCart,
  faRestroom,
  faShower,
  faSink,
  faFish,
  faHiking,
  faUmbrellaBeach,
  faWater,
  faMountain,
  faSwimmingPool,
  faUsers,
  faSeedling,
  faChild,
  faBed,
  faTv,
  faUtensils,
  faSnowflake,
  faTools,
  faQuestionCircle
);

const app = createApp(App);
const pinia = createPinia(); // Pinia 인스턴스 생성 추가
app.component("font-awesome-icon", FontAwesomeIcon);
app.use(pinia); // Pinia 사용 추가
app.use(router);

// setupAxiosInterceptors();

app.mount("#app");
