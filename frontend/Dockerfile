### ETAPA 1: REALIZAR EL BUILD
FROM node:14.20.1 AS build
WORKDIR /usr/src/app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

### ETAPA 2: EJECUTAR ###
FROM nginx:1.17.1-alpine
COPY default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /usr/src/app/dist/frontend /usr/share/nginx/html
EXPOSE 80
