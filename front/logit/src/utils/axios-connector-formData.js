import axios from 'axios';


// local 주소
// const baseURL = "http://localhost:9090/"
// 서버 주소
const baseURL = "https://i8a609.p.ssafy.io/api/"



const axiosConnector = axios.create({
  baseURL
})

axiosConnector.interceptors.request.use(
  (config) => {
    const token = sessionStorage.getItem('token');
    const formData = "multipart/form-data";

    // header : {
    //   "Context-type" : "multipart/form-data", 
    //   }
      

    if (token) {
      config.headers['Authorization'] = `bearer ${token}`;
      config.headers['Context-Type'] = formData;
    } else {
      config.withCredentials = false;
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

axiosConnector.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// axios 객체 생성
export default axiosConnector;