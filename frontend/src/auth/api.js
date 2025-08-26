import { http } from "./http";

export const authApi = {
  login: (username, password) =>
    http.post("/auth/login", { username, password }).then((r) => r.data),

  register: (payload) =>
    http.post("/auth/register", payload).then((r) => r.data),


};
