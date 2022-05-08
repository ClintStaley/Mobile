(function (_, Kotlin) {
  'use strict';
  var Unit = Kotlin.kotlin.Unit;
  var toString = Kotlin.toString;
  function makeGoodPromise$lambda$lambda$lambda(closure$res, closure$value) {
    return function () {
      closure$res(closure$value);
      return Unit;
    };
  }
  function makeGoodPromise$lambda$lambda(closure$value, closure$delay) {
    return function (res, f) {
      setTimeout(makeGoodPromise$lambda$lambda$lambda(res, closure$value), closure$delay);
      return Unit;
    };
  }
  function makeGoodPromise$lambda(value, delay) {
    return new Promise(makeGoodPromise$lambda$lambda(value, delay));
  }
  var makeGoodPromise;
  function makeBadPromise$lambda$lambda$lambda(closure$rej, closure$value) {
    return function () {
      closure$rej(Kotlin.newThrowable(closure$value));
      return Unit;
    };
  }
  function makeBadPromise$lambda$lambda(closure$value, closure$delay) {
    return function (f, rej) {
      setTimeout(makeBadPromise$lambda$lambda$lambda(rej, closure$value), closure$delay);
      return Unit;
    };
  }
  function makeBadPromise$lambda(value, delay) {
    return new Promise(makeBadPromise$lambda$lambda(value, delay));
  }
  var makeBadPromise;
  function main$lambda(res) {
    console.log(res);
    return Unit;
  }
  function main$lambda_0(res) {
    console.log('Doing first then with result ' + res);
    return makeGoodPromise('New promise from ' + res, 3000);
  }
  function main$lambda_1(res) {
    console.log('Second promise sez: ' + res);
    return 'Second promise from ' + res;
  }
  function main$lambda_2(f) {
    console.log('Or maybe not');
    return 'Wow, an error!';
  }
  function main$lambda_3(f) {
    console.log('Happy outcome!');
    return Unit;
  }
  function main$lambda_4(f) {
    console.log('And another happy outcome!');
    return Unit;
  }
  function main$lambda_5(err) {
    console.log('Failed because ' + toString(err));
    return Unit;
  }
  function main$lambda_6(res) {
    console.log('Where were we? Oh yeah: ' + res);
    return Unit;
  }
  function main() {
    var simplePrm = makeGoodPromise('Simple Result', 4000);
    simplePrm.then(main$lambda);
    var complexPrm = makeGoodPromise('Complex Result', 3000);
    complexPrm = complexPrm.then(main$lambda_0).then(main$lambda_1, main$lambda_2);
    var failedPrm = makeBadPromise('Bad promise, bad!', 8000);
    failedPrm.then(main$lambda_3).then(main$lambda_4).then(null, main$lambda_5);
    complexPrm.then(main$lambda_6);
    console.log('All done!');
  }
  var package$main = _.main || (_.main = {});
  Object.defineProperty(package$main, 'makeGoodPromise', {
    get: function () {
      return makeGoodPromise;
    },
    set: function (value) {
      makeGoodPromise = value;
    }
  });
  Object.defineProperty(package$main, 'makeBadPromise', {
    get: function () {
      return makeBadPromise;
    },
    set: function (value) {
      makeBadPromise = value;
    }
  });
  package$main.main = main;
  makeGoodPromise = makeGoodPromise$lambda;
  makeBadPromise = makeBadPromise$lambda;
  main();
  Kotlin.defineModule('Example', _);
  return _;
}(module.exports, require('kotlin')));
