var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var bcrypt = require('bcrypt-nodejs');

<!--creating user schema-->
var userSchema = new Schema({
   email: {type: String, required: true},
   password: {type: String, required: true}
});

userSchema.methods.encryptPassword = function(password) {
 return bcrypt.hashSync(password, bcrypt.genSaltSync(5), null);
};

userSchema.methods.validPassword = function(password) {
 return bcrypt.compareSync(password, this.password);
};

<!--exporting user schema-->
module.exports = mongoose.model('User', userSchema);
