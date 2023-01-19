<template>
  <div class="container">
    <div class="discription_box">
      <div><h1>당신의 여정을 기록해보세요.</h1></div>
    </div>
  </div>
</template>

<script>
export default {
    name: 'MainPage',
}
</script>

<style scoped>
  .container {
    height: 100%;
    display:flex;
    align-items: center;
    justify-content: center;
  }
  .discription_box {
    margin-top: 400px;
    height: 1300px;
  }
  h1 {
    font-size: 80px;
    font-weight: 300;
  }


</style>