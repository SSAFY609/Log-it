import router from "@/router";
import axiosConnector from "@/utils/axios-connector";
import { useToast } from "vue-toastification";

const toast = useToast()

const today_to_str = ()=>{
    const today = new Date();
    const y = today.getFullYear();
    const m = today.getMonth() + 1;
    const d = today.getDate();
    return `${y}-${m >= 10 ? m : '0' + m}-${d >= 10 ? d : '0' + d}`;
}

const growth = {
    namespaced: true,
    state:{
        today: false,
        allGrowth: [],
        growth: {},
        growthUsers: [],
        progress: {},
        firstProgress: [],
        dateProgress: [],
        allUser: [],
        searchUser: [],
        myLikeProgress: [],
        log: [],
    },
    getters: {
        // 이벤트 회원 수
        getGrowthUsersLength(state){
            return state.growthUsers.length;
        },
    },
    mutations: {
        GET_ALL_GROWTH(state, payload){
            console.log(payload)
            state.allGrowth = payload
        },
        GET_GROWTH(state, payload){
            state.growth = payload.data
            router.push({name: 'GrowthProgress', params: {growthId: payload.growthId}})
        },
        GET_GROWTH_USERS(state, payload){
            state.growthUsers = payload
        },
        GET_PROGRESS(state, payload){
            state.progress = payload
            router.push({ name: 'ProgressUpdate'})
        },
        GET_FIRST_PROGRESS(state, payload){
            state.firstProgress = payload
            const id = sessionStorage.getItem('id')
            if(payload.length == 0){
                state.today = false
            } else if(payload[payload.length - 1].date == today_to_str() && payload[payload.length-1].progressDto.user.id == id){
                state.today = true
            } else {
                state.today = false
            }
        },
        GET_DATE_PROGRESS(state, payload){
            state.dateProgress = payload
            router.push({name: 'ProgressDetail'})
        },
        GET_DATE_PROGRESS_ONLY(state, payload){
            state.dateProgress = payload
        },
        GET_ALL_USER(state, payload){
            state.allUser = payload
        },
        SEARCH_USER(state, payload){
            state.searchUser = payload
        },
        SEARCH_USER_RESET(state){
            state.searchUser = []
        },
        GET_LIKE_PROGRESS(state, payload){
            state.myLikeProgress = payload
        },
        GET_LOG(state, payload){
            state.log = payload
        },
        RESET_SEARCH_USER(state){
            state.searchUser = []
        }
    },
    actions: {
        // 성장 여정 추가
        createGrowth({dispatch}, growth){
            axiosConnector.post(`growth/regist`, growth
            ).then((res)=>{
                dispatch('growthSetting', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },

        // 회원에 해당하는 모든 이벤트 가져오기
        getAllGrowth({commit}) {
            axiosConnector.get(`growth/get_mine`
            ).then((res)=> {
                commit('GET_ALL_GROWTH', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },

        // test를 위해 앞에 {commit}을 지웠더니 payload가 안넘어오는 error 발생,,,,
        // 무조건 앞자리는 context, 두번째 자리가 payload의 자리라고 한다... 와우 넘 신기해
        // 관통때, mutation에서 값을 못 받아오는 이유가 이거였군... 신기

        // 이벤트 아이디에 해당하는 모든 이벤트 가져오기
        getGrowth({commit}, growthId) {
            const params = {
                growthId: growthId
            }
            axiosConnector.get(`growth/get_event`, {
                params: params
            }).then((res)=>{
                const payload = {
                    data: res.data,
                    growthId: growthId
                }
                commit('GET_GROWTH', payload)
            }).catch((err)=>{
                console.log(err);
            })
        },
        // 이벤트 아이디에 해당하는 이벤트 참여 유저들 가져오기
        getGrowthUsers({commit}, growthId) {
            axiosConnector.get(`growth/get_user`, {
                params: {
                    growthId: growthId
                }
            }
            ).then((res)=>{
                commit('GET_GROWTH_USERS', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 이벤트 아이디에 해당하는 과정들 가져오기
        getProgress({commit}, progressId) {
            axiosConnector.get(`growth/progress/${progressId}`
            ).then((res)=>{
                commit('GET_PROGRESS', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },

        // 이벤트 타임라인에 보여줄 메인 과정들 (날짜당 대표 과정 하나씩)
        getFirstProgress({commit}, growthId){
            axiosConnector.get(`growth/progress/first`,{
                params: {
                    growthId: growthId
                }
            }).then((res)=>{
                commit('GET_FIRST_PROGRESS', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 날짜별 과정들 가져오기
        getDateProgress({commit}, data){
            axiosConnector.get(`growth/progress/${data.growthId}/${data.date}`
            ).then((res)=>{
                commit('GET_DATE_PROGRESS', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        getDateProgressOnly({commit}, data){
            axiosConnector.get(`growth/progress/${data.growthId}/${data.date}`
            ).then((res)=>{
                commit('GET_DATE_PROGRESS_ONLY', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 회원 추가를 위한 검색용 유저들 (가입되어있지 않은...)
        getAllUser({commit}, growthId) {
            axiosConnector.get(`growth/invite/get`, {
                params: { growthId: growthId }
            }).then((res)=>{
                commit('GET_ALL_USER', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 회원 검색
        searchUser({commit}, data){
            axiosConnector.post(`growth/invite/search`, data
            ).then((res)=>{
                console.log(res.data)
                commit('SEARCH_USER', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 이벤트에 회원 추가
        addGrowthUser(context, data){
            axiosConnector.post('growth/invite', data
            ).then((res)=>{
                console.log(res.data)
                toast.success(`${data.userName}님을 초대하였습니다.`, {
                    timeout: 2000,
                    position: 'top-center'
                })
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 이벤트에 회원 삭제 ( 호스트만 가능 )
        deleteGrowthUser({dispatch}, growthId, email){
            const data = {
                growthId: growthId,
                email: email
            }
            axiosConnector.delete('growthUser', data
            ).then(()=>{
                dispatch('getGrowthUser', growthId)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 이벤트에 과정 추가
        registProgress({dispatch}, progress){
            axiosConnector.post('growth/write', progress
            ).then(()=>{
                const data = {
                    growthId: progress.growthId,
                    date: progress.progressDate.date
                }
                console.log(data)
                dispatch('getDateProgress', data)
                // console.log(res)
                // 이때 res.data는 eventId
                // dispatch('getProgress', res.data);
            }).catch((err)=>{
                console.log(err);
            })
        },

        // 이벤트에 과정 수정
        updateProgress({dispatch}, progress){
            axiosConnector.post(`growth/write`, progress
            ).then(()=> {
                const data = {
                    growthId: progress.growthId,
                    date: progress.progressDate.date
                }
                dispatch('getDateProgress', data)
            }).catch((err)=>{
                console.log(err);
            })
        },

        // 내가 좋아요 한 과정들 미리 받아두기
        getLikeProgress({commit}, growthId){
            axiosConnector.get(`growth/like/get`,{
                params: {
                    growthId: growthId
                }
            }).then((res)=>{
                commit('GET_LIKE_PROGRESS', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },

        getLog({commit}, growthId){
            axiosConnector.get(`growth/log/${growthId}`
            ).then((res)=>{
                commit('GET_LOG', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 좋아요 / 좋아요 취소 -> true / false 를 바꾸는 것
        likeProgress({dispatch}, data){
            axiosConnector.put(`growth/like/${data.progressId}`
            ).then(()=>{
                console.log('성공')
                const payload = {
                    growthId: data.growthId,
                    date: data.date
                }
                dispatch('getLikeProgress', data.growthId)
                dispatch('getDateProgressOnly', payload)
            }).catch((err)=>{
                console.log(err)
            })
        },
        
        // 이벤트 첫 세팅 ㄱㄱ
        growthSetting({dispatch}, growthId){
            dispatch('getFirstProgress', growthId)
            dispatch('getGrowthUsers', growthId)
            dispatch('getLikeProgress', growthId)
            dispatch('getLog', growthId)
            dispatch('getGrowth', growthId)
        },
    }

}

export default growth;