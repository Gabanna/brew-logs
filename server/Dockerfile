FROM node:carbon
WORKDIR /usr/src/app
ENV port 3000
COPY package*.json ./
RUN npm install
COPY ./config ./config
COPY ./src ./src
COPY ./index.js ./index.js
RUN mkdir ./data
EXPOSE 3000
CMD ["npm", "start"]