var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var schema = new Schema({
    user: {type: Schema.Types.ObjectId, ref: 'User'},//storing the user in the mongos
    cart: {type: Object, required: true},//storing the shopping cart in the mongos
    address: {type: String, required: true},//storing the user address in the mongos
    name: {type: String, required: true},//storing the user name in the mongos
    paymentId: {type: String, required: true}//storing the paymentid in the mongos
});

module.exports = mongoose.model('Order', schema);
