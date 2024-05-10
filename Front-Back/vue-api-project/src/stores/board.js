import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router';

const REST_BOARD_API = 'http://localhost:8080/api-board/board'

export const useBoardStore = defineStore('board', () => {
  
  const createBoard = function (board) {
    axios({
      url: REST_BOARD_API,
      method: 'POST',
      // 이 작업을 하지 않아도 JSON형태로 Content-type을 결정해서 보냄
      // headers: {
      //   "Content-Type":"application/json"
      // },
      data: board,
      
    })
      .then(() => {
        router.push({ name: 'boardList' });
      })
      .catch((err) => {
        console.log(err)
      })
  }

  const boardList = ref([]);
  const getBoardList = function () {
    axios.get(REST_BOARD_API)
      .then((response) => {
        boardList.value = response.data
      })
  }

  const board = ref({});
  const getBoard = function (id) {
    axios.get(`${REST_BOARD_API}/${id}`)
      .then((response) => {
        board.value = response.data;
      })
  }

  const updateBoard = function () {
    axios.put(REST_BOARD_API, board.value)
      .then(() => {
        router.push({ name: 'boardList' });
    })
  }

  const searchBoardList = function (searchConditon) {
    axios.get(REST_BOARD_API, {
      params: searchConditon
    })
      .then((response) => {
        boardList.value = response.data;
    })
  }

  return {
    createBoard,
    boardList,
    getBoardList,
    board,
    getBoard,
    updateBoard,
    searchBoardList,

  }
})
