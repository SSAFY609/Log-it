import router from "@/router";
import axiosConnector from "@/utils/axios-connector";


const growth = {
    namespaced: true,
    state:{
        allGrowth: [],
        growth: {},
        growthUsers: [],
        progress: [],
        allUser: [],
        searchUser: [],
    },
    getters: {
        // 이벤트 회원 수
        getGrowthUsersLength(state){
            return state.growthUsers.length;
        },
    },
    mutations: {
        CREATE_GROWTH(context, payload){
            // state.growth = payload
            // payload를 growthId로 할지, 등록한 growth로 할지,,, 
            // 그냥 id만 반환해도 될듯 어차피 growthId로 getGrowth할거니깡
            router.push({name: 'GrowthProgress', params: {growthId: payload}})
        },
        GET_ALL_GROWTH(state, payload){
            console.log(payload)
            state.allGrowth = payload
        },
        GET_GROWTH(state, payload){
            state.growth = payload
        },
        GET_GROWTH_USERS(state, payload){
            state.growthUsers = payload
        },
        GET_PROGRESS(state, payload){
            state.progress = payload
        },
        GET_ALL_USER(state, payload){
            state.allUser = payload
        },
        SEARCH_USER(state, payload){
            state.searchUser = payload
        },
        SEARCH_USER_RESET(state){
            state.searchUser = []
        }
    },
    actions: {
        // 성장 여정 추가
        createGrowth({commit}, growth){
            axiosConnector.post(`growth/regist`, growth
            ).then((res)=>{
                commit('CREATE_GROWTH', res.data)
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
                console.log(res.data)
                commit('GET_GROWTH', res.data)
            }).catch((err)=>{
                console.log(err);
            })
        },
        // 이벤트 아이디에 해당하는 이벤트 참여 유저들 가져오기
        getGrowthUsers({commit}, growthId) {
            console.log('여기야여기')
            axiosConnector.get(`growth/get_user`, {
                params: {
                    growthId: growthId
                }
            }
            ).then((res)=>{
                console.log(res.data, '우잉')
                commit('GET_GROWTH_USERS', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 이벤트 아이디에 해당하는 과정들 가져오기
        getProgress({commit}, growthId) {
            axiosConnector.get(`progress/${growthId}`
            ).then((res)=>{
                commit('GET_PROGRESS', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 회원 추가를 위한 검색용 유저들 (가입되어있지 않은...)
        getAllUser({commit}, growthId) {
            axiosConnector.get(`growth/invite/get`, {
                params: { growthId: growthId }
            }).then((res)=>{
                console.log(res.data)
                commit('GET_ALL_USER', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 회원 검색
        searchUser({commit}, data){
            axiosConnector.post(`growth/invite/search`, data
            ).then((res)=>{
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
        registProgress(context, progress){
            axiosConnector.post('growth/write', progress
            ).then((res)=>{
                console.log(res)
                // 이때 res.data는 eventId
                // dispatch('getProgress', res.data);
            }).catch((err)=>{
                console.log(err);
            })
        },
        // 이벤트에 과정 수정
        updateProgress({dispatch}, progress){
            axiosConnector.put('progress', progress
            ).then((res)=> {
                dispatch('getProgress', res.data)
            }).catch((err)=>{
                console.log(err);
            })
        }
        // 이벤트에 과정 삭제 => 그냥 안하면 어때,,, ? ㅋㅋㅋㅋ
    }

}

export default growth;