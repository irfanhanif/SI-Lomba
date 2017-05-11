var express = require('express');
var router = express.Router();

var UserController = require('../controller/UserController');
var TimController = require('../controller/TimController');

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
router.post('/editprofile', function(req, res, next){
  var get_profile = new UserController();
  get_profile.getUserProfile(res, req.body);
});
router.post('/changeprofile', function(req, res, next){
  var user = new UserController();
  user.changeProfile(res, req.body);
});

/* Routing Tim */
router.post('/detailtim', function(req, res, next){
  var tim = new TimController();
  tim.getTimData(res, req.body);
});

module.exports = router;
