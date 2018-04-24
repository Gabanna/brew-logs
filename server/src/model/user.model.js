const model = {
    username: String,
    email: String,
    password: String
}

module.exports = (mongoose) => {
    const schema = mongoose.Schema(model);
    return mongoose.model('user', schema);
}