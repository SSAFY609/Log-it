<template>
    <div class="memo-dialog">
        <div class="detail-form memo-bg memo-create">
            <QuillEditor 
              class="text-editor" 
              theme="bubble"
              v-model:content="progress_content"
              content-type="html"
              toolbar="essential" 
              :read-only="false" />
            <div class="check">
              <v-icon size="large" @click="updateProgress">mdi-check</v-icon>
            </div>
        </div>
    </div>
</template>

<script>
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.bubble.css';
import { mapState } from 'vuex'
export default {
    name: 'ProgressUpdate',
    data() {
      return {
        progress_content: ''
      }
    },
    components: {
        QuillEditor,
    },
    computed: {
      ...mapState('growth', ['progress'])
    },
    methods: {
      updateProgress() {
        if(this.progress.content != this.progress_content){
          const progress = {
            progressId: this.progress.progressId,
            growthId: this.progress.growth.growthId,
            content: this.progress_content,
            progressDate: {
              date: this.progress.progressDate.date
            }
          }
          this.$store.dispatch('growth/updateProgress', progress)
        }
      }
    },
    created(){
      this.progress_content = this.progress.content
    }

}
</script>

<style>
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
</style>