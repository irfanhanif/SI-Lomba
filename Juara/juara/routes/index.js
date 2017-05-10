var express = require('express');
var router = express.Router();

var UserController = require('../controller/UserController');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

/* Routing User */
router.post('/login', function(req, res, next){
  var login = new UserController();
  result = login.loginCheck(res, req.body);
});
router.post('/register', function(req, res, next){
  var register = new UserController();
  result = register.registerUser(res, req.body);
});
router.get('/editprofile', function(req, res, next){
  console.log(req);
  var get_profile = new UserController();
  get_profile.getUserProfile(res, req.headers);
});

module.exports = router;
