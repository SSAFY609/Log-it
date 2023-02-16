import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import Toast from 'vue-toastification'
import Aos from 'aos'
import 'vue-toastification/dist/index.css'
import 'aos/dist/aos.css';

loadFonts()

createApp(App)
  .use(router)
  .use(store)
  .use(vuetify)
  .use(Toast)
  .use(Aos)
  .mount('#app')
