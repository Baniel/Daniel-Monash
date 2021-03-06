/* */ 
'use strict';
exports.__esModule = true;
var _extends = Object.assign || function(target) {
  for (var i = 1; i < arguments.length; i++) {
    var source = arguments[i];
    for (var key in source) {
      if (Object.prototype.hasOwnProperty.call(source, key)) {
        target[key] = source[key];
      }
    }
  }
  return target;
};
function _interopRequireDefault(obj) {
  return obj && obj.__esModule ? obj : {'default': obj};
}
var _invariant = require('invariant');
var _invariant2 = _interopRequireDefault(_invariant);
var _Actions = require('./Actions');
var _ExecutionEnvironment = require('./ExecutionEnvironment');
var _DOMUtils = require('./DOMUtils');
var _DOMStateStorage = require('./DOMStateStorage');
var _createDOMHistory = require('./createDOMHistory');
var _createDOMHistory2 = _interopRequireDefault(_createDOMHistory);
function createBrowserHistory(options) {
  _invariant2['default'](_ExecutionEnvironment.canUseDOM, 'Browser history needs a DOM');
  var isSupported = _DOMUtils.supportsHistory();
  function getCurrentLocation(historyState) {
    historyState = historyState || window.history.state || {};
    var path = _DOMUtils.getWindowPath();
    var _historyState = historyState;
    var key = _historyState.key;
    var state = undefined;
    if (key) {
      state = _DOMStateStorage.readState(key);
    } else {
      state = null;
      key = history.createKey();
      if (isSupported)
        window.history.replaceState(_extends({}, historyState, {key: key}), null, path);
    }
    return history.createLocation(path, state, undefined, key);
  }
  function startPopStateListener(_ref) {
    var transitionTo = _ref.transitionTo;
    function popStateListener(event) {
      if (event.state === undefined)
        return;
      transitionTo(getCurrentLocation(event.state));
    }
    _DOMUtils.addEventListener(window, 'popstate', popStateListener);
    return function() {
      _DOMUtils.removeEventListener(window, 'popstate', popStateListener);
    };
  }
  function finishTransition(location) {
    var basename = location.basename;
    var pathname = location.pathname;
    var search = location.search;
    var hash = location.hash;
    var state = location.state;
    var action = location.action;
    var key = location.key;
    if (action === _Actions.POP)
      return;
    _DOMStateStorage.saveState(key, state);
    var path = (basename || '') + pathname + search + hash;
    var historyState = {key: key};
    if (action === _Actions.PUSH) {
      if (isSupported) {
        window.history.pushState(historyState, null, path);
      } else {
        window.location.href = path;
      }
    } else {
      if (isSupported) {
        window.history.replaceState(historyState, null, path);
      } else {
        window.location.replace(path);
      }
    }
  }
  var history = _createDOMHistory2['default'](_extends({}, options, {
    getCurrentLocation: getCurrentLocation,
    finishTransition: finishTransition,
    saveState: _DOMStateStorage.saveState
  }));
  var listenerCount = 0,
      stopPopStateListener = undefined;
  function listenBefore(listener) {
    if (++listenerCount === 1)
      stopPopStateListener = startPopStateListener(history);
    var unlisten = history.listenBefore(listener);
    return function() {
      unlisten();
      if (--listenerCount === 0)
        stopPopStateListener();
    };
  }
  function listen(listener) {
    if (++listenerCount === 1)
      stopPopStateListener = startPopStateListener(history);
    var unlisten = history.listen(listener);
    return function() {
      unlisten();
      if (--listenerCount === 0)
        stopPopStateListener();
    };
  }
  function registerTransitionHook(hook) {
    if (++listenerCount === 1)
      stopPopStateListener = startPopStateListener(history);
    history.registerTransitionHook(hook);
  }
  function unregisterTransitionHook(hook) {
    history.unregisterTransitionHook(hook);
    if (--listenerCount === 0)
      stopPopStateListener();
  }
  return _extends({}, history, {
    listenBefore: listenBefore,
    listen: listen,
    registerTransitionHook: registerTransitionHook,
    unregisterTransitionHook: unregisterTransitionHook
  });
}
exports['default'] = createBrowserHistory;
module.exports = exports['default'];
