(function (_, Kotlin) {
  'use strict';
  var Unit = Kotlin.kotlin.Unit;
  function makeStringPromise$lambda$lambda$lambda(closure$res, closure$value) {
    return function () {
      closure$res(closure$value);
      return Unit;
    };
  }
  function makeStringPromise$lambda$lambda(closure$value, closure$delay) {
    return function (res, f) {
      setTimeout(makeStringPromise$lambda$lambda$lambda(res, closure$value), closure$delay);
      return Unit;
    };
  }
  function makeStringPromise$lambda(value, delay) {
    return new Promise(makeStringPromise$lambda$lambda(value, delay));
  }
  var makeStringPromise;
  function Hello() {
    return makeStringPromise('Hello', 2000);
  }
  function Punctuation() {
    return makeStringPromise(', ', 2000);
  }
  function World() {
    return makeStringPromise('world!', 2000);
  }
  function main$lambda(it) {
    console.log(it);
    return Unit;
  }
  function main() {
    coHelloWorld().then(main$lambda);
    console.log('End of main');
  }
  function coHelloWorld$lambda(closure$rtn) {
    return function (it) {
      closure$rtn.v += it;
      return Punctuation();
    };
  }
  function coHelloWorld$lambda_0(closure$rtn) {
    return function (it) {
      closure$rtn.v += it;
      return World();
    };
  }
  function coHelloWorld$lambda_1(closure$rtn) {
    return function (it) {
      return closure$rtn.v + it;
    };
  }
  function coHelloWorld() {
    var rtn = {v: ''};
    return Hello().then(coHelloWorld$lambda(rtn)).then(coHelloWorld$lambda_0(rtn)).then(coHelloWorld$lambda_1(rtn));
  }
  var package$main = _.main || (_.main = {});
  Object.defineProperty(package$main, 'makeStringPromise', {
    get: function () {
      return makeStringPromise;
    },
    set: function (value) {
      makeStringPromise = value;
    }
  });
  package$main.Hello = Hello;
  package$main.Punctuation = Punctuation;
  package$main.World = World;
  package$main.main = main;
  package$main.coHelloWorld = coHelloWorld;
  makeStringPromise = makeStringPromise$lambda;
  main();
  Kotlin.defineModule('AsyncAwait', _);
  return _;
}(module.exports, require('kotlin')));
