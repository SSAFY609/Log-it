<template>
  <div class="signup-container">
    <div class="signup-content">
        <v-icon class="signup-content-icon">mdi-check</v-icon>
        <div class="signup-content-text-top">회원가입이 완료되었습니다.</div>
        <div class="signup-content-text-bottom">{{state.uName}}님의 회원가입 축하드립니다.</div>
    </div>
    <div @click="signupEmail" class="signup-button b_lightgray_l">
          <div>시작하기</div>
        </div>
  </div>

</template>

<script>
import{reactive,onBeforeMount} from "vue";

export default {
name:"UserSignupComplete",
props:["user"],
setup(props){
    const state = reactive({
        uName:"메렁",
    });
    onBeforeMount(()=>{
        state.uName = props.user.uName;
    })
    return{
        state,
    }
}

}
</script>

<style scoped>
.signup-button:hover {
  background-color: #ff0a54;
  cursor: pointer;
}
.signup-button:active {
  background-color: #c0003a;
}
.signup-button {
  margin-top:100px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  font-size: 18.5px;
  color: white;
  width: 380px;
  height: 60px;
  border-radius: 10px;
}
.signup-content-icon{
    height: 120px;
    font-size: 120px;
    color: rgb(3, 179, 3);
}
.signup-content{
    margin-top:60px;
    display: flex;
    flex-direction: column;
    align-items: center;
}
.signup-content-text-top{
    font-size: 27px;
    font-family: appleB;
    margin-top:5px;

}
.signup-content-text-bottom{
    font-size: 18px;
    font-family: appleM;
    margin-top:10px;

}
.signup-content-text-top{
}
</style>