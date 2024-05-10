import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

export const useYoutubeStore = defineStore('youtube', () => {
  const videos = ref([])
  const selectedVideo = ref(null)
  const youtubeSearch = function (keyword) {
    // console.log(keyword);
    
      const URL = 'https://www.googleapis.com/youtube/v3/search';
      const API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY;

      axios({
        url: URL,
        method: 'GET',
        params: {
          key : API_KEY,
          part: 'snippet',
          maxResults: 10,
          q: keyword,
          type: 'video',
        }
      })
        .then((response) => {
          videos.value = response.data.items;
          // console.log(response.data);
        })
        
    }

  const clickVideo = function (video) {
      selectedVideo.value = video
    }
  
    return {
      youtubeSearch,
      videos,
      selectedVideo,
      clickVideo,
      
    }
  })
