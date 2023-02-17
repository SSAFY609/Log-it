<template>
    <div class="container">
        <div>
            <img :src="require('@/assets/images/bell2.png')" class="img">
        </div>
        <div class="text">
            <div>알림메세지</div>
            <div style="display:flex; align-items:center">
                <div class="title">{{ growth.category }}</div>    
                <div>에 초대되었습니다.</div>
            </div>
            <div class="btns">
                <button
                class="action no"
                @click.stop="clicked(false)"
                >거절</button>
                <button
                class="action yes"
                @click.stop="clicked(true)"
                >수락</button>
            </div>
        </div>
    </div>
</template>

<script>
import { useToast } from 'vue-toastification';
// import { useStore } from 'vuex';
const toast = useToast()
// const store = useStore()

export default {
props: {
    toastId: {
    type: [String, Number],
    required: true
    },
    growth: {
        type: Object,
    }
},
methods: {
    clicked(sel) {
        let data = null;
        if(sel) {
            data = {
                growthId: this.growth.growthId,
                accept: true
            }
            toast.update(this.toastId, {
                content: "초대를 수락하였습니다.",
                options: { type: "success", timeout: 2000 }
            });
            this.$emit('accept', data)
            
        }else{
            data = {
                growthId: this.growth.growthId,
                accept: false,
            }
            toast.update(this.toastId, {
                content: "초대를 거절하였습니다.",
                options: { type: "warning", timeout: 2000 }
            });
            this.$emit('reject', data)
        }
        console.log(data)
    }
},
};
</script>
<style scoped>

.img {
    position: relative;
    bottom: 26px;
    margin-left: 10px;
    width: 20px;
    height: 20px;
}
.container {
    font-family: appleL;
    display: flex;
    /* flex-direction: column; */
    align-items: center;
    /* justify-content: center; */
    font-size: 16px;
    width: 300px;
    height: 70px;
    color: #000000;
}
.text {
    margin-left: 20px;
    width: 250px;
}
.title{
    font-family: appleM;
    font-size: 18px;
}
.btns {
    width: 100%;
    display: flex;
    justify-content: end;
}
.action {
    /* border: solid 1px white; */
    border-radius: 5px;
    margin-top: 10px;
    margin-left: 10px;
    width: 50px;
    height: 26px;
    background-color: rgb(253, 253, 253);
    color: #797979;
}

.yes {
    background-color: #fe9823d8;
    color: white;
}
.yes:hover {
    /* color: #000000; */
    background-color: #ef860fd8;
}

.no:hover {
    background-color: rgba(213, 213, 213, 0.436);
}
</style>