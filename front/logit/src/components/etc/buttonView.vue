<template>
  <button>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span>LOG-IT</span>
  </button>
</template>

<script>
export default {
    name: 'buttonView',

}
</script>

<style scoped>
button {
    transform: rotate(-25deg) skew(25deg);
    transform-style: preserve-3d;
    position: relative;
    width: 100px;
    height: 32px;
    border: none;
    background: transparent;
}

button::before{
    content: "";
    position: absolute;
    bottom: -10px;
    left: -5px;
    width: 100%;
    height: 10px;
    background: #FF0A54;
    transform: skewX(-41deg);
}

button::after{
    content: "";
    position: absolute;
    top: 5px;
    left: -9px;
    width: 9px;
    height: 100%;
    background: #FF0A54;
    transform: skewY(-49deg);
}


button span {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: #FF0A54;
    color: white;
    font-size: 25px;
    transition: 1.1s ease-out;
}

button:hover span {
    z-index: 1000;
    transition: 0.3s;
    color: white;
}

button:hover span:nth-child(5){
    transform: translate(40px, -40px);
    opacity: 1;
}
button:hover span:nth-child(4){
    transform: translate(30px, -30px);
    opacity: 0.8;
}
button:hover span:nth-child(3){
    transform: translate(20px, -20px);
    opacity: 0.6;
}
button:hover span:nth-child(2){
    transform: translate(10px, -10px);
    opacity: 0.4;
}
button:hover span:nth-child(1){
    transform: translate(0px, 0px);
    opacity: 0.2;
}
button:nth-child(1):hover span {
    background: rgb(253, 255, 114);
}
</style>