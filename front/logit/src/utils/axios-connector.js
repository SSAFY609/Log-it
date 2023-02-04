import axios from 'axios';


// local 주소
const baseURL = "http://localhost:9090/"


const axiosConnector = axios.create({
  baseURL
})

axiosConnector.interceptors.request.use(
  (config) => {
    const token = sessionStorage.getItem('token');

    if (token) {
      config.headers['Authorization'] = `bearer ${token}`;
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