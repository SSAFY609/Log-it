<template>
    <swiper v-if="!update_mode" 
      :effect="'cards'"
      :grabCursor="false"
      :cssMode="false"
      :modules="modules"
      class="mySwiper">
      <swiper-slide v-for="(progress, index) in dateProgress" :key="progress.progressId" class="slide">

        <!-- 내가 쓴글이라면 -->
        <div v-if="progress.user.id == loginUser.id" class="memo-bg">
          <div class="writer">
            <div>
              <v-avatar style="margin-right: 10px">
                <v-img v-if="progress.user.image.length < 5" :src="require(`@/assets/profiles/scale (${progress.user.image}).png`)"></v-img>
                <v-img v-else :src="img_to_url(progress.user.image)"></v-img>
              </v-avatar>
              {{ progress.user.name }}
            </div>
            <v-icon @click="updateProgress(progress.progressId)">mdi-pencil</v-icon>
          </div>
          <div class="detail-form">
            <QuillEditor
              class="text-editor" 
              theme="bubble"
              v-model:content="progress.content"
              content-type="html"
              toolbar="essential" 
              :read-only="true" />
          </div>
          <div class="heart">
            <v-btn v-if="myLikeProgress.indexOf(progress.progressId) != -1" variant="text" icon="mdi-heart" color="red" @click="like(progress.progressId, index)"></v-btn>
            <v-btn v-else variant="text" icon="mdi-heart-outline" color="red" @click="like(progress.progressId, index)"></v-btn>
            <div style="font-size: 20px">{{ progress.likeCnt }}</div>
          </div>
        </div>

        <!-- 내가 쓴 글이 아니라면 -->
        <div v-else class="memo-bg pink">
          <div class="writer">
            <div>
              <v-avatar style="margin-right: 10px">
                <v-img v-if="progress.user.image.length < 5" :src="require(`@/assets/profiles/scale (${progress.user.image}).png`)"></v-img>
                <v-img v-else :src="img_to_url(progress.user.image)"></v-img>
              </v-avatar>
              {{ progress.user.name }}
            </div>
          </div>
          <div class="detail-form">
            <QuillEditor 
              class="text-editor" 
              theme="bubble"
              v-model:content="progress.content"
              content-type="html"
              toolbar="essential" 
              :read-only="true" />
          </div>
          <div class="heart">
            <v-btn v-if="myLikeProgress.indexOf(progress.progressId) != -1" variant="text" icon="mdi-heart" color="red" @click="like(progress.progressId, index)"></v-btn>
            <v-btn v-else variant="text" icon="mdi-heart-outline" color="red" @click="like(progress.progressId, index)"></v-btn>
            <div style="font-size: 20px">{{ progress.likeCnt }}</div>
          </div>
        </div>
      </swiper-slide>
    </swiper>
</template>

<script>
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.bubble.css';
import { Swiper, SwiperSlide } from 'swiper/vue';
import { mapState } from 'vuex';
import { EffectCards } from 'swiper';

import 'swiper/css';

import 'swiper/css/effect-cards';

export default {
  name: 'ProgressDetail',
    components: {
      QuillEditor,
      Swiper,
      SwiperSlide,
    },
    setup() {
      return {
        modules: [EffectCards],
      };
    },
    computed: {
      ...mapState(['loginUser']),
      ...mapState('growth', ['dateProgress', 'myLikeProgress'])
    },
    methods: {
      updateProgress(progressId) {
        this.$store.dispatch('growth/getProgress', progressId);
      },
      like(progressId, idx) {
        const data = {
          progressId: progressId,
          growthId: this.dateProgress[idx].growth.growthId,
          date: this.dateProgress[idx].progressDate.date
        }
        console.log(data)
        this.$store.dispatch('growth/likeProgress', data)
      },
      unlike(progressId) {
        this.$store.dispatch('growth/unlikeProgress', progressId)
      },
      img_to_url(src) {
          return `https:/logit-s3.s3.ap-northeast-2.amazonaws.com/${src}`
        }
    },
    created(){
    }

}
</script>

<style scoped>

.mySwiper {
  width: 680px;
  height: 710px;
}

.slide {
  width: 680px;
  height: 710px;
  /* background-color: white; */
}

h1 {
  font-family: galaxy;
}

.memo-dialog {
  display: flex;
  width: 650px;
  height: 700px;
}

.memo-bg {
  /* background-image: url('../../assets/images/memo_create_bg.png'); */
  background-color: rgb(255, 255, 155);
  font-family: galaxy;
  padding: 40px;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  width: 599px;
  height: 620px;
}

.pink {
  background-color: rgb(255, 199, 224);
}

.writer {
  display: flex;
  font-family: appleL;
  font-size: 20px;
  justify-content: space-between;
  align-items: center;
  width:100%;
  margin-bottom: 30px;
}

.writer>div{
  display: flex;
  align-items: center;
}

.detail-form {
  width: 100%;
  height: 100%;
  font-size: 25px;
}

.memo-create {
  width: 650px;
  height: 700px;
  font-size: 25px;
}
.memo-bg >textarea {
  width: 93%;
  height: 90%;
  font-size: 25px;
  outline: none;
  resize: none;
}

.icon {
  height: 30px;
  width: 30px;
}

.check {
  /* position: absolute; */
  margin-top: 10px;
  top: 610px;
}

.heart {
  display: flex;
  align-items: center;
  font-family: appleB;
  font-size: 30px;
}

.heart>button{
  margin: 0 10px;
}


</style>