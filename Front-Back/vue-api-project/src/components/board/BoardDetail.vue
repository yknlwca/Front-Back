<template>
    <div>
        <h4>게시글 상세</h4>
        <hr>
        <div>{{ store.board.title }}</div>
        <div>{{ store.board.writer }}</div>
        <div>{{ store.board.regDate }}</div>
        <div>{{ store.board.viewCnt }}</div>
        <div>{{ store.board.content }}</div>

        <button @click="deleteBoard">삭제</button>
        <button @click="updateBoard">수정</button>
    </div>
</template>

<script setup>
import { useBoardStore } from '@/stores/board';
import { onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';

const store = useBoardStore();

const route = useRoute();
const router = useRouter();

onMounted(() => {
    // 게시글 번호
    store.getBoard(route.params.id);
})

const deleteBoard = function () {
    axios.delete(`http://localhost:8080/api-board/board/${route.params.id}`)
        .then(() => {
            router.push({ name:'boardList' });
    })
}

const updateBoard = function () {
    router.push({ name: 'boardUpdate' });
}
</script>

<style scoped></style>