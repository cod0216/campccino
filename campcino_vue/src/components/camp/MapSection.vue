<template>
  <div id="map" class="w-full h-96 mb-6"></div>
</template>

<script>
import { onMounted, watch } from "vue";

export default {
  name: "MapSection",
  props: {
    markers: Array,
  },
  setup(props) {
    let map;
    let kakaoMarkers = [];
    let infowindows = [];

    onMounted(() => {
      if (window.kakao && window.kakao.maps) {
        initMap();
      } else {
        const script = document.createElement("script");
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=850d7b0fe59c47b55e0c0511520c3335&libraries=services,clusterer,drawing";
        script.onload = () => kakao.maps.load(() => initMap());
        document.head.appendChild(script);
      }
    });

    function initMap() {
      const mapContainer = document.getElementById("map");
      const mapOption = {
        center: new kakao.maps.LatLng(35.159545, 126.852601),
        level: 12,
      };
      map = new kakao.maps.Map(mapContainer, mapOption);
      updateMarkers();
    }

    watch(
      () => props.markers,
      (newMarkers) => {
        updateMarkers();
        updateMapCenter();
      }
    );

    function updateMarkers() {
      if (!map) return;

      // 기존 마커 및 인포윈도우 삭제
      kakaoMarkers.forEach((marker) => marker.setMap(null));
      infowindows.forEach((infowindow) => infowindow.close());
      kakaoMarkers = [];
      infowindows = [];

      // 새로운 마커 추가
      props.markers.forEach((markerData, index) => {
        const marker = new kakao.maps.Marker({
          map,
          position: markerData.latlng,
          title: markerData.title,
        });

        const infowindow = new kakao.maps.InfoWindow({
          content: `<div style="padding:5px;">${markerData.title}</div>`,
        });

        // 마커 클릭 시 지도 중심 설정 및 레벨 변경
        kakao.maps.event.addListener(marker, "click", function () {
          map.setLevel(3); // level3 수준으로 변경
          map.setCenter(marker.getPosition());
          infowindow.open(map, marker);
        });

        kakaoMarkers.push(marker);
        infowindows.push(infowindow);
      });
    }

    // 지도 중심을 마커들의 중심으로 이동시키는 함수
    function updateMapCenter() {
      if (kakaoMarkers.length === 0) return;

      let bounds = new kakao.maps.LatLngBounds();
      kakaoMarkers.forEach((marker) => {
        bounds.extend(marker.getPosition());
      });

      // 모든 마커들을 포함하는 중앙 좌표로 이동
      map.setBounds(bounds);
    }

    // 캠핑장 이름 클릭 시 지도 이동 함수
    function focusMarker(markerIndex) {
      if (kakaoMarkers[markerIndex]) {
        const marker = kakaoMarkers[markerIndex];
        const infowindow = infowindows[markerIndex];
        map.setLevel(3); // level3 수준으로 변경
        map.setCenter(marker.getPosition());
        infowindow.open(map, marker);
      }
    }

    return {
      focusMarker,
    };
  },
};
</script>

<style scoped>
/* 스타일 추가 필요 시 */
</style>
