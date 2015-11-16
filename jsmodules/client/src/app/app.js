var $ = require('components/jquery');
var greetModule = require('./greeting');

var spring = new greetModule.Greeting("Spring");
$("#greeting").html(spring.toString());