import router from "@/router";
import axiosConnector from "@/utils/axios-connector"

const statistics = {
    namespaced: true,
    state:{
        ing_cnt: 0,
        complete_cnt: 0,
        companyRank: [],
        myProgress: {},
        algorithm: [ "BFS", "DFS", "그래프 탐색", "구현", "문자열", "정렬", "이분탐색", "자료구조", "완전탐색", "힙", "트리", "동적 프로그래밍", "그래프 구현", "백트래킹"],
        algorithm_cnt: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        myData: {},
        
    },
    getters: {

    },
    mutations: {
        SET_ALGORITHM_CNT(state, payload){
            for(let i=0; i<14; i++){
                for(let j=0; j<14; j++){
                    if(state.algorithm[i] == payload[j].name){
                        state.algorithm_cnt[i] = payload[j].count
                        break;
                    }
                }
            }
            const data = {
                labels: state.algorithm,
                datasets: {
                    backgroundColor: 'rgba(255,185,185, 0.7)',
                    data: state.algorithm_cnt
                }
            }
            state.myData = data
        },
        SET_MY_APPLY(state, payload){
            state.ing_cnt = payload[0].count
            state.complete_cnt = payload[1].count
        },
        SET_COMPANY_RANK(state, payload){
            if(payload.length <= 6){
                state.companyRank = payload
            }else{
                state.companyRank = payload.splice(0,6);
            }
            // console.log(state.companyRank)
        },
        SET_MY_PROGRESS(state, payload){
            state.myProgress = payload
            router.push({name: 'StatisticsPage'})
        },
    },
    actions: {
        getMyStatistics({commit}){
            axiosConnector.get(`statistics`
            ).then((res)=>{
                commit('SET_ALGORITHM_CNT', res.data[0].data)
                commit('SET_MY_APPLY', res.data[2].data)
                commit('SET_COMPANY_RANK', res.data[3].data)
                commit('SET_MY_PROGRESS', res.data[4].data)

            }).catch((err)=>{
                console.log(err)
            })
        }
        
    }

}

export default statistics;