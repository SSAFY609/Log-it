<template>
    <div class="memo-dialog">
        <div class="detail-form memo-bg memo-create">
            <QuillEditor 
                class="text-editor" 
                theme="bubble"
                v-model:content="content"
                content-type="html"
                toolbar="essential" 
                placeholder="텍스트를 입력하세요"
                :read-only="false" />
            <div class="check">
                <v-icon size="large" @click="createProgress">mdi-check</v-icon>
            </div>
        </div>
    </div>
</template>

<script>
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.bubble.css';
import { mapState } from 'vuex';

export default {
    name: 'ProgressCreate',
    components: {
        QuillEditor,
    },
    data() {
        return {
            content: ''
        }
    },
    computed: {
        ...mapState("growth", ['growth'])
    },
    methods: {
        todaytoStr(){
            const today = new Date()
            const year = today.getFullYear()
            const month = today.getMonth() + 1
            const date = today.getDate()
            return `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;
        },
        createProgress() {
            if (this.content){
                const progress = {
                    growthId: this.growth.growthId,
                    progressDate: { date: this.todaytoStr() },
                    content: this.content,
                }
                console.log(progress)
                this.$store.dispatch('growth/registProgress', progress)
            } else {
                alert('내용이 없잖아!')
            }
        }
    }
}
</script>

<style scoped>
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