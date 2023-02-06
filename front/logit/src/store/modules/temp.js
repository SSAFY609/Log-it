// import { createStore } from 'vuex'
// import axios from 'axios'
// import router from '@/router'



const temp = {
  namespaced: true,
  state: {
    sidebar: true,
    loginUser: {email: 'tjdgns@naver.com', name: '이성훈', profile: '1'},
    users: [{user_id: 1, name: '이성훈', profile: '1', email: 'tjdgns@naver.com'},{user_id: 2, name: '김나현', profile: '10', email: 'skgus@naver.com'},{user_id: 3, name: '오하늘', profile: '4', email: 'sky5@naver.com'}],
    event: {event_id: 1, start_date: new Date(2023,0,16), end_date: new Date(2023,1,12), name: '알고리즘 IM형', done: false},
    eventUsers: {owner: {user_id: 1, name: '이성훈'}, users: [{user_id: 1, name: '이성훈', profile: '1', email: 'tjdgns@naver.com'},{user_id: 2, name: '김나현', profile: '10', email: 'skgus@naver.com'},{user_id: 3, name: '오하늘', profile: '4', email: 'sky5@naver.com'}]},
    events: [
      {event_id: 4, start_date: new Date(2023,0,10), end_date: new Date(2023,1,10), name: 'SQLD 시험 준비', done: true},
      {event_id: 1, start_date: new Date(2023,0,16), end_date: new Date(2023,1,12), name: '알고리즘 IM형', done: false},
      {event_id: 3, start_date: new Date(2023,0,24), end_date: new Date(2023,1,7), name: '알고리즘 A형', done: false},
      {event_id: 2, start_date: new Date(2023,1,8), end_date: new Date(2023,3,13), name: '정보처리기사', done: false},
      // {event_id: 2, start_date: new Date(2023,1,8), end_date: new Date(2023,3,13), name: '정보처리기사', done: false},
    ],
    progress: [
      {writer: {user_id: 1, name: '이성훈'}, progress_id: 1, event_id: 1, date: new Date(2023, 0, 17), content: '열심히 했따ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ'},
      {writer: {user_id: 1, name: '이성훈'}, progress_id: 2, event_id: 1, date: new Date(2023, 0, 18), content: '안녕하세요 111111111111'},
      {writer: {user_id: 1, name: '이성훈'}, progress_id: 3, event_id: 1, date: new Date(2023, 0, 19), content: '안녕하이 2222222222222222222'},
      {writer: {user_id: 1, name: '이성훈'}, progress_id: 4, event_id: 1, date: new Date(2023, 0, 24), content: '우히히히ㅏㅏㅏ'},
      {writer: {user_id: 1, name: '이성훈'}, progress_id: 5, event_id: 1, date: new Date(2023, 0, 26), content: '우하하하하하하하하하하하ㅏㅎ하하핳하'},
      {writer: {user_id: 1, name: '이성훈'}, progress_id: 6, event_id: 1, date: new Date(2023, 0, 27), content: '열심히 했따ㅏㅏㅏㅏ'},
      {writer: {user_id: 1, name: '이성훈'}, progress_id: 7, event_id: 1, date: new Date(2023, 0, 29), content: '열심히 했따ㅏㅏㅏㅏㅏㅏㅏㅏ'},
      {writer: {user_id: 1, name: '이성훈'}, progress_id: 8, event_id: 1, date: new Date(2023, 0, 30), content: '열심히 ㅏㅏㅏㅏㅏ'},
      {writer: {user_id: 1, name: '이성훈'}, progress_id: 9, event_id: 1, date: new Date(2023, 1, 1), content: '열심히 ㅏㅏㅏㅏㅏ'},
      {writer: {user_id: 1, name: '이성훈'}, progress_id: 10, event_id: 1, date: new Date(2023, 1, 2), content: '열심히 ㅏㅏㅏㅏㅏ'},
      {writer: {user_id: 1, name: '이성훈'}, progress_id: 11, event_id: 1, date: new Date(2023, 1, 4), content: '열심ddㅏ'},
      // {writer: {user_id: 1, name: '이성훈'}, progress_id: 12, event_id: 1, date: new Date(2023, 1, 5), content: '열심히 ㅏㅏㅏㅏㅏ'},
      // {writer: {user_id: 1, name: '이성훈'}, progress_id: 13, event_id: 1, date: new Date(2023, 1, 7), content: '그래그래'},
      // {writer: {user_id: 1, name: '이성훈'}, progress_id: 14, event_id: 1, date: new Date(2023, 1, 10), content: '열심히 ㅏㅏ'},
      // {writer: {user_id: 1, name: '이성훈'}, progress_id: 15, event_id: 1, date: new Date(2023, 1, 11), content: '화이팅이다'},
    ],
    // shareProgress: [],
    shareProgress: [
      {date: new Date(2023, 0, 17), contents: [
        {progressId: 1, email: 'tjdgns@naver.com', name: '이성훈', profile: '1', content: '안녕하세요안녕하세요안녕하세요'},
        {progressId: 2, email: 'skgus@naver.com', name: '김나현', profile: '11', content: '2222222233333333333333333333333322222222222222222222'},
        {progressId: 3, email: '5sky@naver.com', name: '오하늘', profile: '16', content: '뀨어엉암;ㄻ라ㅓㅁ라머라머00'},
      ]},
      {date: new Date(2023, 0, 18), contents: [
        {progressId: 4, email: 'tjdgns@naver.com', name: '이성훈', profile: '1', content: '<p>안녕하세요안녕하세요안녕하세요</p><p>예 반갑습니다</p>'},
        {progressId: 5, email: '5sky@naver.com', name: '오하늘', profile: '16', content: '뀨어엉암;ㄻ라ㅓㅁ라머라머00'},
      ]},
      {date: new Date(2023, 0, 19), contents: [
        {progressId: 6, email: 'tjdgns@naver.com', name: '이성훈', profile: '1', content: '안녕하세요안녕하세요안녕하세요'},
        {progressId: 7, email: 'skgus@naver.com', name: '김나현', profile: '11', content: '2222222233333333333333333333333322222222222222222222'},
      ]},
      {date: new Date(2023, 0, 23), contents: [
        {progressId: 8, email: 'skgus@naver.com', name: '김나현', profile: '11', content: '<p>22222222333333333333333333333333222222333322222233332222223333222222333322222233332222223333222222333322222222222222222222</p>'},
        {progressId: 9, email: '5sky@naver.com', name: '오하늘', profile: '16', content: '뀨어엉암;ㄻ라ㅓㅁ라머라머00'},
      ]},
      {date: new Date(2023, 1, 1), contents: [
        {progressId: 10, email: 'tjdgns@naver.com', name: '이성훈', profile: '1', content: '안녕하세요안녕하세요안녕하세요'},
      ]},
      {date: new Date(2023, 1, 3), contents: [
        {progressId: 11, email: 'tjdgns@naver.com', name: '이성훈', profile: '1', content: '안녕하세요안녕하세요안녕하세요'},
        {progressId: 12, email: '5sky@naver.com', name: '오하늘', profile: '16', content: '뀨어엉암;ㄻ라ㅓㅁ라머라머00'},
      ]},
    ]
  },
  getters: {
  },
  mutations: {
    CLOSE_SIDEBAR(state){
      state.sidebar = false;
    },
    OPEN_SIDEBAR(state){
      state.sidebar = true;
    }
  },
  actions: {
    closeSidebar({commit}){
      commit('CLOSE_SIDEBAR');
    },
    openSidebar({commit}){
      commit('OPEN_SIDEBAR');
    }
  },
}

export default temp;
