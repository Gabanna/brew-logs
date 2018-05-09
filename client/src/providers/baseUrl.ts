import config from "../config/config";

const url = "<protokoll>://<host>:<port>/server-java/api"
.replace("<protokoll>", config.api.ssl ? "https" : "http")
.replace("<host>", config.api.host)
.replace("<port>", "" + config.api.port);

export default url;
