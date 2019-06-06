<!--importing product model-->
var Product = require('../models/product');

var mongoose = require('mongoose');

mongoose.connect('localhost:27017/shopping');

<!--creating product-->
var products = [
   new Product({
       imagePath: 'https://upload.wikimedia.org/wikipedia/commons/4/40/Aurora_australis_dancing_over_an_LED_illuminated_igloo.jpg',
       title: 'Aurora australis',
       description: 'Aurora australis dancing over an LED illuminated igloo giving a blue tinge to the color of snow. !!!!',
       price: 549
   }),
   new Product({
       imagePath: 'http://www.hf.uio.no/ifikk/bilder/Lied/LIED013.JPG',
       title: 'Themistokles von Eckenbrecher',
       description: 'View of Laerdalsoren, on the Sognefjord ...',
       price: 530
   }),
   new Product({
       imagePath: 'https://upload.wikimedia.org/wikipedia/commons/7/78/Church_Heart_of_the_Andes.jpg',
       title: 'Frederick Edwin Church. The Heart of the Andes',
       description: 'A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my',
       price: 450
   }),
   new Product({
       imagePath: 'http://www.sinoorigin.com/images/modern-landscape/large/modern-landscape-painting-015.jpg',
       title: 'La Jolla California',
       description: 'I painted this view from a cocktail party at the Birch Aquarium. It was a bright sunny February day with no clouds. The flowers were in bloom, but it needed clouds to add interest to the sky.',
       price: 525
   }),
   new Product({
       imagePath: 'http://www.sinoorigin.com/images/seascape-painting-boat-painting/large/Moon%20and%20Yachts.jpg',
       title: 'Moon and Yachts',
       description: 'I painted this view from a cocktail party at the Birch Aquarium. It was a bright sunny February day with no clouds. eather experiences.by Mary Helmreich',
       price: 655
   }),
   new Product({
       imagePath: 'http://www.sinoorigin.com/images/modern-landscape/large/modern-landscape-painting-001.jpg',
       title: 'Modern landscape',
       description: 'A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my',
       price: 500
   })
];

var done = 0;
for (var i = 0; i < products.length; i++) {
   products[i].save(function(err, result) {
       done++;
       if (done === products.length) {
           exit();
       }
   });
}

function exit() {
   mongoose.disconnect();
}
