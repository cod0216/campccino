// src/main.js
import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "./assets/index.css"; // Tailwind CSS

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
app.component("font-awesome-icon", FontAwesomeIcon);
app.use(router);
app.mount("#app");
