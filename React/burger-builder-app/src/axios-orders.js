import axios from 'axios';

const instance = axios.create({
  baseURL: 'https://react-burger-builder-e84a6.firebaseio.com/'
});

export default instance;