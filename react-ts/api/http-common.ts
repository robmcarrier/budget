import axios from 'axios';

export default axios.create({
  baseURL: process.env.VITE_SOME_KEY,
  headers: {
    'Content-type': 'application/json',
  },
});
