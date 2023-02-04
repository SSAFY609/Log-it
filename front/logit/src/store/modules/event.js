import axiosConnector from "@/utils/axios-connector";


const event = {
    namespaced: true,
    state:{
        events: [],
        event: {},
        eventUsers: [],
        progress: [],

    },
    getters: {
        // 이벤트 회원 수
        getEventUsersLength(state){
            return state.eventUsers.length;
        },
    },
    mutations: {
        GET_EVENTS(state, payload){
            state.events = payload
        },
        GET_EVENT(state, payload){
            state.event = payload
        },
        GET_EVENT_USERS(state, payload){
            state.eventUsers = payload
        },
        GET_PROGRESS(state, payload){
            state.progress = payload
        }
    },
    actions: {
        // 회원에 해당하는 모든 이벤트 가져오기
        getEvents({commit}, email) {
            const data = {
                email: email
            }
            axiosConnector.get(`event/get`, data
            ).then((res)=> {
                commit('GET_EVENTS', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 이벤트 아이디에 해당하는 모든 이벤트 가져오기
        getEvent({commit}, eventId) {
            axiosConnector.get(`event/${eventId}`
            ).then((res)=>{
                commit('GET_EVENT', res.data)
            }).catch((err)=>{
                console.log(err);
            })
        },
        // 이벤트 아이디에 해당하는 이벤트 참여 유저들 가져오기
        getEventUsers({commit}, eventId) {
            axiosConnector.get(`eventUser/${eventId}`
            ).then((res)=>{
                commit('GET_EVENT_USERS', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 이벤트 아이디에 해당하는 과정들 가져오기
        getProgress({commit}, eventId) {
            axiosConnector.get(`progress/${eventId}`
            ).then((res)=>{
                commit('GET_PROGRESS', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 이벤트에 회원 추가
        addEventUser({commit}, eventId, email){
            const data = {
                eventId: eventId,
                email: email
            }
            axiosConnector.post('eventUser', data
            ).then((res)=>{
                commit('ADD_EVENT_USER', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 이벤트에 회원 삭제 ( 호스트만 가능 )
        deleteEventUser({dispatch}, eventId, email){
            const data = {
                eventId: eventId,
                email: email
            }
            axiosConnector.delete('eventUser', data
            ).then(()=>{
                dispatch('getEventUser', eventId)
            }).catch((err)=>{
                console.log(err)
            })
        },
        // 이벤트에 과정 추가
        // createProgress({commit}, )
    }

}

export default event;