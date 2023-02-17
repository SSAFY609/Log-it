<template>
  <div class="container">
    <div class="event-create-box">
        <div class="event-create-title">
            취업 여정 추가
        </div>
        <div class="category-title">
            💼&nbsp;&nbsp;어떤 기업을 목표로 하시나요?
        </div>
        <div><v-text-field label="ex) 삼성전자" v-model="companyName"></v-text-field></div>

        <div class="category-title">
            📌&nbsp;&nbsp;채용을 위한 전형 추가
        </div>
        <v-chip-group filter class="category" column multiple>
            <v-chip color="#FF0A54" size="large" v-for="i in categoryList" :value="i" :key="i" @click="select_chip(i), flag = false" >
                {{ i }}
            </v-chip>
        </v-chip-group> 

        <div class="category-title category-title2">
            📆&nbsp;&nbsp;기간 설정
        </div>
        <div class="select-date">
            <v-text-field label="시작 날짜" type="date" v-model="start_date"></v-text-field> ~
            <v-text-field label="종료 날짜" type="date" v-model="end_date"></v-text-field>
        </div>
        <div class="create_btn_box b_main btn_hover" @click="create">
            <div class="create_btn_text f_white">생성하기</div>
        </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import { useToast } from 'vue-toastification';
const toast = useToast()

export default {
    name: 'JobCreate',

    props: {
        date: {
            type: String,
        }
    },
    computed: {
      ...mapState("job", ["categoryList"]),
      ...mapState("tempJob", ["jobs"])
    },

    data() {
        return {
            companyName: "",
            categoryList: [],
            selectedList: [],
            start_date: null,
            end_date: null,
            flag: false,
            sendData: {},
            
        }
    },
    methods: {

        select_chip(i) {

            const target = event.target

            console.log(target.innerText)
            if(this.selectedList.indexOf(i) != -1){

                // console.log("선택 취소")
                const idx = this.selectedList.indexOf(i)

                this.selectedList.splice(idx,1)

            } else {
                // console.log("선택")
                // console.log(this.selectedList)
                // console.log(i)
                this.selectedList.push(i);
            }
        },



        create() {
            if (!this.companyName) {
                toast.error('기업명을 입력해주세요.', {
                    timeout: 2000,
                    position: 'bottom-right'
                })
            } else if (this.selectedList.length == 0) {
                toast.error('전형을 최소 1개 선택해 주세요', {
                    timeout: 2000,
                    position: 'bottom-right'
                })
            } else if (!this.start_date || !this.end_date) {
                toast.error('날짜를 선택해 주세요.', {
                    timeout: 2000,
                    position: 'bottom-right'
                })
            } else {
                const jobs = {
                        companyName: this.companyName,
                        startDate : this.start_date,
                        endDate: this.end_date,
                        jobCategoryList: [],
                }

                console.log('selectedList')
                console.log(this.selectedList)
                this.selectedList.forEach(element => {
                    jobs.jobCategoryList.push(element)
                });
                this.$store.dispatch('tempJob/createJob', jobs)
                
                
            }
        }
    },
    created() {
        this.start_date = this.$route.query.st;
        this.categoryList = this.$store.state.job.categoryList

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
    margin-top: 40px;
    text-align: center;
}

.category-title {
    margin-top: 40px;
    margin-bottom: 10px;
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
    margin-top: 30px;
  }
  .create_btn_text {
    margin-top: 2px;
    font-size: 22px;
    font-weight: 500;
  }
  .category-title2 {
    margin-top: 40px;
  }
</style>