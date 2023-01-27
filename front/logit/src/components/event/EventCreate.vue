<template>
  <div class="container">
    <div class="event-create-box">
        <div class="event-create-title">
            성장 여정 추가
        </div>
    </div>
  </div>
</template>

<script>
export default {
    name: 'EventCreate',

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
}

.event-create-title {
    font-family: appleH;
    font-size: 40px;
    margin-top: 75px;
}

</style>