# ==== build ====
FROM node:20-alpine AS build
WORKDIR /app
COPY frontend/package*.json ./
RUN npm ci
COPY frontend/ ./

# Por defecto, apuntamos a /api (lo resuelve Nginx y no hay CORS)
ARG REACT_APP_API=/api
ENV REACT_APP_API=$REACT_APP_API
RUN npm run build

# ==== run ====
FROM nginx:1.27-alpine
# Archivo de config para servir SPA y proxy /api y /auth al backend
COPY frontend/nginx.conf /etc/nginx/conf.d/default.conf
# Archivos estáticos
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
