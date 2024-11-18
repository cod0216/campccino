// src/api.js
import axios from "axios";

// 더미 데이터
const dummyBoards = [
  {
    board_id: 1,
    board_title: "서울 근교 캠핑장 추천",
    board_content:
      "서울에서 1시간 이내에 갈 수 있는 좋은 캠핑장 추천 부탁드립니다!",
    board_created_at: "2023-10-01T14:30:00Z",
    board_view: 25,
    img_url: "https://example.com/image1.jpg",
    user_id: "user1",
    category: "추천"
  },
  {
    board_id: 2,
    board_title: "텐트 선택 팁",
    board_content:
      "가족과 함께하기 좋은 텐트 종류와 선택 시 고려사항에 대해 알고 싶어요.",
    board_created_at: "2023-10-05T10:15:00Z",
    board_view: 40,
    img_url: "https://example.com/image2.jpg",
    user_id: "user2",
    category: "장비"
  },
  {
    board_id: 3,
    board_title: "초보자를 위한 캠핑 준비물",
    board_content: "처음 캠핑을 가는데 꼭 필요한 준비물이 무엇인지 알려주세요.",
    board_created_at: "2023-10-10T08:45:00Z",
    board_view: 30,
    img_url: "https://example.com/image3.jpg",
    user_id: "user3",
    category: "질문"
  },
];

// API 함수들
export const getBoards = () => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve({ data: dummyBoards });
    }, 500); // 0.5초 후에 응답
  });
};

export const getBoardById = (id) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const board = dummyBoards.find((b) => b.board_id === parseInt(id));
      if (board) {
        resolve({ data: board });
      } else {
        reject(new Error("게시글을 찾을 수 없습니다."));
      }
    }, 500);
  });
};

export const createBoard = (data) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      const newBoard = {
        board_id: dummyBoards.length + 1,
        board_created_at: new Date().toISOString(),
        board_view: 0,
        ...data,
      };
      dummyBoards.push(newBoard);
      resolve({ data: newBoard });
    }, 500);
  });
};

export const updateBoard = (id, data) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const index = dummyBoards.findIndex((b) => b.board_id === parseInt(id));
      if (index !== -1) {
        dummyBoards[index] = { ...dummyBoards[index], ...data };
        resolve({ data: dummyBoards[index] });
      } else {
        reject(new Error("게시글을 찾을 수 없습니다."));
      }
    }, 500);
  });
};

export const deleteBoard = (id) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const index = dummyBoards.findIndex((b) => b.board_id === parseInt(id));
      if (index !== -1) {
        dummyBoards.splice(index, 1);
        resolve({ data: { message: "삭제되었습니다." } });
      } else {
        reject(new Error("게시글을 찾을 수 없습니다."));
      }
    }, 500);
  });
};
