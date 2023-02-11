// import router from "@/router";
import axiosConnector from "@/utils/axios-connector"

const search = {
    namespaced: true,
    state:{
        growths: [],
        jobs: [],
        users: [],
    },
    getters: {
        searchLength(state) {
            return state.growths + state.jobs + state.users;
        }
    },
    mutations: {
        GET_SEARCH_RESULT(state, payload){
            state.growths = payload['GROWTH']
            state.jobs = payload['JOB']
            state.users = payload['USER']
        }
    },
    actions: {
        getSearchResult({commit}, keyword){
            axiosConnector.get(`search`, {
                params: {
                    keyword: keyword,
                }
            }).then((res)=>{
                commit('GET_SEARCH_RESULT', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        }
    }

}

export default search;