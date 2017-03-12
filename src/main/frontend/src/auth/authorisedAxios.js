import axios from 'axios';
import { tokenName } from '../constants/config';

export const getToken = () => (localStorage.getItem(tokenName));

export const getAuthConfig = () => ({
  headers: { Authorization: `Bearer ${getToken()}` } });

export default axios.create(getAuthConfig());
