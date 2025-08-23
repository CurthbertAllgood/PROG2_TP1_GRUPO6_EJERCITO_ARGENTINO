import axios from "axios";

const API_URL = process.env.REACT_APP_API_URL || "http://localhost:8080";

export const tokenStore = {
  get: () => localStorage.getItem("token"),
  set: (t) => (t ? localStorage.setItem("token", t) : localStorage.removeItem("token")),
  clear: () => localStorage.removeItem("token"),
};

export const http = axios.create({
  baseURL: API_URL,
  headers: { "Content-Type": "application/json" },
});

// Inyecta Bearer en cada request si hay token
http.interceptors.request.use((cfg) => {
  const t = tokenStore.get();
  if (t) cfg.headers.Authorization = `Bearer ${t}`;
  return cfg;
});

// Manejo simple de 401
http.interceptors.response.use(
  (res) => res,
  (err) => {
    if (err?.response?.status === 401) {
      tokenStore.clear();
      // Opcional: redirigir al login
      // window.location.assign("/login");
    }
    return Promise.reject(err);
  }
);
