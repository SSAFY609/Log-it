<template>
  <div class="container">
    <div class="event-create-box">
        <div class="event-create-title">
            성장 여정 추가
        </div>
        <div class="category-title">
            ✔ 카테고리 선택 또는 생성
        </div>
        <v-chip-group filter class="category" column>
            <v-chip color="#FF0A54" size="large" v-for="i in state.categorys" :value="i" :key="i" @click="select_chip(i), flag = false" >
                {{ i }}
            </v-chip>
            <v-chip color="#FF0A54" size="large" @click="flag = !flag, select_category=''">카테고리 생성</v-chip>
        </v-chip-group>
        <div v-show="!flag" style="height: 78px"></div>
        <div v-show="flag"><v-text-field label="생성할 카테고리를 입력하세요" v-model="select_category"></v-text-field></div>
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
import { useToast } from 'vue-toastification';
const toast = useToast();

export default {
    name: 'GrowthCreate',
    props: {
        date: {
            type: String,
        }
    },
    data() {
        return {
            flag: false,
            select_category: "",
            start_date: null,
            end_date: null,
        }
    },
    methods: {
        select_chip(i) {
            if(this.select_category == i){
                this.select_category = ''
            } else {
                this.select_category = i;
            }
        },
        create() {
            if (!this.select_category) {
                toast.error('카테고리를 선택해 주세요.', {
                    timeout: 2000,
                    position: 'bottom-right',
                    // icon: {
                    //     iconClass: 'mdi-bell mdi v-icon',
                    //     iconTag: 'i'
                    // }
                })
            } else if (!this.start_date || !this.end_date) {
                toast.error('날짜를 선택해 주세요.', {
                    timeout: 2000,
                    position: 'bottom-right',
                })
            } else {
                const growth = {
                    eventDate: {
                        startDate: this.start_date,
                        endDate: this.end_date,
                    },
                    category: this.select_category,
                }
                this.$store.dispatch('growth/createGrowth', growth)
                console.log(growth);
                // this.$router.push({name: 'GrowthProgress', params: {growthId: 1}})
            }
        }
    },
    created() {
        this.start_date = this.$route.query.st
    },
    setup() {
        const state = {
            categorys: ['알고리즘 IM형', '알고리즘 A형', 'JAVA', 'Python', '일타싸피', '월말평가', '관통프로젝트', '싸피레이스'],
        }
        return {
            state,
        }
    }

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
    margin-top: 30px;
    margin-bottom: 20px;
    font-size: 20px;
    text-align: start;
}

.category {
    font-family: appleB;

}

.select-date {
    display: flex;
    align-items: center;
    font-size: 25px;
}

.create_btn_box {
    width: 100%;
    height: 60px;
    border-radius: 6px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0 auto;
    margin-top: 20px;
  }
  .create_btn_text {
    margin-top: 2px;
    font-size: 22px;
    font-weight: 500;
  }
</style>