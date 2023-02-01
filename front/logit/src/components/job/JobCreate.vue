<template>
  <div class="container">
    <div class="event-create-box">
        <div class="event-create-title">
            취업 여정 추가
        </div>
        <div class="category-title">
            ✔ 어떤 기업을 목표로 하시나요?
        </div>
        <div><v-text-field label="ex) 카카오" v-model="company_name"></v-text-field></div>
        <div class="category-title">
            ✔ 이벤트 기간 설정
        </div>
        <div class="select-date">
            <v-text-field label="시작 날짜" type="date" v-model="start_date"></v-text-field> ~
            <v-text-field label="종료 날짜" type="date" v-model="end_date"></v-text-field>
        </div>
        <div class="create_btn_box b_main btn_hover" @click="create">
            <div class="create_btn_text f_white">다음단계</div>
        </div>
    </div>
  </div>
</template>

<script>
export default {
    name: 'JobCreate',
    data() {
        return {
            company_name: "",
            start_date: null,
            end_date: null,
        }
    },
    methods: {
        create() {
            if (!this.company_name) {
                alert('기업명을 입력해주세요.')
            } else if (!this.start_date || !this.end_date) {
                alert('날짜를 선택해 주세요.')
            } else {
                console.log(this.company_name)
                console.log(this.start_date)
                console.log(this.end_date)
            }
        }
    },
    created() {
        this.start_date = this.$route.query.st;
    },

}
</script>

<style scoped>
.container {
    background-color: #EDEDED;
    display: flex;
    height: 100%;
    width: 100%;
    justify-content: center;
    align-items: center;
}

.event-create-box {
    background-color: white;
    width: 600px;
    border-radius: 20px;
    height: 700px;
    display: flex;
    flex-wrap: wrap;
    flex-direction: column;
    align-content: center;
    padding: 0 30px;
}

.event-create-title {
    font-family: appleB;
    font-size: 50px;
    margin-top: 60px;
    text-align: center;
}

.category-title {
    margin-top: 50px;
    margin-bottom: 20px;
    font-size: 20px;
    text-align: start;
}

.category {
    font-family: appleB;

}

.select-date {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 25px;
}

.select-date >div >div {
    width: 40%;
}

.create_btn_box {
    width: 100%;
    height: 60px;
    border-radius: 6px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0 auto;
    margin-top: 80px;
  }
  .create_btn_text {
    margin-top: 2px;
    font-size: 22px;
    font-weight: 500;
  }
</style>