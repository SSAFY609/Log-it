<template>
  <div class="container b_transparentwhite">
    <div class="left_box">
      <div @click="openSidebar" class="slider_hover slider_box b_lightgray"><v-icon class="silder_icon f_white">mdi-chevron-double-right</v-icon></div>
    </div>
    <div class="right_box lay3">
      <div class="search_box_container lay1">
        <div @click="openSearch" class="search_box right_box_items hover_cursor  lay2">
          <v-icon class="search_icon lay3">mdi-magnify</v-icon>
          <div class="search_content lay3">검색</div>
          <v-autocomplete class="search_input_text nosee" autofocus
            clearable
            variant="null"
            :items=state.SearchList
          ></v-autocomplete>
        </div>
      </div>

      <div v-if="!state.loginUser" class="login_btn_box right_box_items">
        <router-link :to="{name: 'UserLogin'}" class="login_btn b_main btn_hover">
          <div class="login_btn_text f_white">로그인</div>
        </router-link>
      </div>
      <div v-else class="profile_box right_box_items lay1">
        <div @click="openProfile" class="profile_id lay2 hover_cursor ">{{state.loginUser.name}} 님</div>
        <div @click="openProfile" class="profile_img_box hover_cursor lay2">
          <v-img class="logo_img"
            :src="require('../../assets/profiles/scale (3).png')"
            height="110"
          />    
        </div>
        <div class="profile_slider_box lay3 nosee">
          <ul>
            <li>설정</li>
            <li>친구목록</li>
            <li>프로필</li>

            <li>로그아웃</li>
          </ul>
        </div>
      </div>
    </div>
    

  </div>
</template>

<script>

export default {
  name: 'TopNavbar',


  setup() {
    const state = {
      loginUser: {
        id: "leesh132",
        name: "이성훈",
        num: "848212",  
      },
      SearchList: [
        "알고리즘",
        "알고리즘A형",
        "알고리즘B형",
        "알고리즘IM형",
        "관통프로젝트",
        "알고리즘A동생",
        "알고리즘B동생",
        "알고리즘A누나",
        "알고리즘B누나",
        "이성훈",
        "공진호",
        "김설희",
        "김나현",
        "오하늘",
        "최강혁",
        "김신일",
      ]
        // loginUser: null,
    }
    const openSidebar = () => {
      const eventTarget1 = document.querySelector('.SideNavbar_box')
      const eventTarget2 = document.querySelector('.SideNavbar_space')

      eventTarget1.classList.toggle('nosee')
      eventTarget2.classList.toggle('nosee')
    }
    const openProfile = () => {
      const target = document.querySelector('.profile_slider_box')
      


      target.classList.toggle('nosee')
    }
    const openSearch = () => {
      const target = document.querySelector('.search_box')
      const target2 = document.querySelector('.search_input_text') 
      const target3 = document.querySelector('.search_content') 

      target.classList.add('max_animation')
      target2.style.display="inline-block"
      target2.focus()
      target3.classList.add('nosee')

    
    }
    return {
      state,
      openSidebar,
      openProfile,
      openSearch,
    }
  }
} 
</script>
<style scoped>
  .container {
    position: fixed;
    z-index: 100;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 10px;
    border-bottom: 0.5px solid rgba(216, 216, 216, 0.6);
    backdrop-filter: blur(5px);
  }

  .slider_box {
    width: 40px;
    height: 40px;
    border-radius: 8px;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    
  }
    
  .silder_icon {
    font-size: 34px;
  }
  .left_box {
    width: 100px;
    height: 50px;
    display: flex;
    align-items: center;
  }
  .right_box {
    width: 620px;
    height: 50px;
    display: flex;
    align-items: center;
  }
  .right_box_items {
    height: 100%;
  }
  .search_box {
    width: 70px;
    height: 40px;
    display: flex;
    align-items: center;
    justify-content: start;
    border-radius: 4px;
    margin-right: 10px;
    background-color: red;
  }
  .search_box:hover {
    background-color: #efefef;
  }
  .login_btn_box {
    width: 220px;
    height: 36px;
    display: flex;
    align-items: flex-start;
    justify-content: end;
    border-left: 1.5px solid lightgray;
    
  }
  .profile_box {
    width: 220px;
    display: flex;
    align-items: center;
    justify-content: end;

  }
  .login_btn {
    width: 100px;
    height: 36px;
    border-radius: 6px;
    margin-right: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    

  }
  .login_btn_text {
    margin-top: 2px;
    font-size: 16px;
  }
  .search_icon {
    width: 40px;
    height: 100%;
    font-size: 20px;
    padding-right: 0px;
    margin-right:0px;


  }
  .search_content {
    height: 100%;
    width: 60px;
    font-size: 18px;
    padding-top: 6px;
  }

  .search_content_box {
    width: 40px;
    height: 36px;  
    display: flex;
    align-items: center;
    justify-content: center;
  } 
  .profile_img_box {
    width: 46px;
    height: 46px;
    border-radius: 50px;
    background-color: gray;
    margin-right: 10px;
  }
  .profile_id {
    margin-right: 10px;
    margin-top: 2px;
    font-size: 18px;
    width: 154px;
    display: flex;
    justify-content: end;
    border-left: 1.5px solid lightgray;
  }
  .profile_slider_box {
    margin: 0 auto;
    width: 140px;
    height: 160px;
    position: absolute;
    border-radius: 6px;
    top: 60px;
    background-color: #ffffff;
    backdrop-filter: blur(20px);

    box-shadow: 4px 4px 20px 4px rgba(59, 59, 59, 0.1);
    

  }
  li {
    display: flex;
    justify-content: left;
    align-items: center;
    height: 40px;
    width:140px;
    list-style-type: none;
    border-radius: 4px;
    padding-left: 20px;

    
  }
  li:hover {
    background-color: #efefef;
    color:#191919;
    font-weight: 900;
    cursor: pointer;
  }

  .search_box_container{
    width: 400px;
    height: 100%;
    display: flex;
    justify-content: end;
    align-items: center;
  }

  .max_animation {
  animation-duration: 0.4s;
  animation-name: maxAnimation;
  animation-fill-mode:forwards;
  animation-direction: alternate;
  background-color: #efefef;
}


@keyframes maxAnimation {
  from {
    width: 100px;
  }
  to {    
    width: 400px
  }
}

input:focus {outline: none;} 

.search_input_text {
  height: 100%;
  background-color: none;
  margin-bottom: 14px;
}


</style>
