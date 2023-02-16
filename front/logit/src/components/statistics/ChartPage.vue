<template >
  <div class="chart-box">
    <div class="container" style="width:600px">
      <!-- <h2>면접 질문 유형 통계</h2>-->
      <div class="iv_container">
        <Doughnut :data="data" :options="options"  />
      </div>

      <!-- <h2>코테 유형 통계</h2>-->
      <div class="iv_container">
        <Doughnut :data="codingTestData" :options="codingTestDataOptions" />
      </div>

      <!-- <h2>내 지원 통계</h2>-->
      <div class="iv_container">
        <Doughnut :data="myApplyData" :options="myApplyDataOptions" />
      </div>
      <div>
      
      </div>
    </div>
  </div>
</template>


<script>
import { Chart as ChartJS,Title,  ArcElement, Tooltip, Legend ,Colors} from 'chart.js'
import { Doughnut } from 'vue-chartjs';
import * as chartConfig from './chartConfig'
import {codingTestData} from "./chartConfig";
import {myApplyData} from "./chartConfig";
import ChartDataLabels from 'chartjs-plugin-datalabels';

ChartJS.register(ArcElement,Title, Tooltip, Legend,Colors,ChartDataLabels)

export default {
  name: 'App',
  computed: {
    codingTestData() {
      return codingTestData
    },

    myApplyData(){
      return myApplyData
    }
  },
  components: {Doughnut},

  data() {
    return chartConfig
  }
}
</script>

<style scoped>
.container {
  margin-top: 100px;
  /*background-color: aquamarine;*/
}
.chart-box {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: rgb(206, 155, 155);
}


.iv_container {
  background-color: green;
  outline: 2px solid red;
}
</style>