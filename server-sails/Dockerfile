FROM node:carbon
WORKDIR /usr/src/app
ENV port 1337
COPY package*.json ./
RUN npm install
COPY ./api ./api
COPY ./config ./config
COPY ./tasks ./tasks
COPY ./views ./views
COPY ./.sailsrc ./.sailsrc
COPY ./app.js ./app.js
EXPOSE 1337
CMD ["npm", "start"]