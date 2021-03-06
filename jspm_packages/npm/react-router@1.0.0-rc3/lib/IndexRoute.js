/* */ 
'use strict';
exports.__esModule = true;
var _createClass = (function() {
  function defineProperties(target, props) {
    for (var i = 0; i < props.length; i++) {
      var descriptor = props[i];
      descriptor.enumerable = descriptor.enumerable || false;
      descriptor.configurable = true;
      if ('value' in descriptor)
        descriptor.writable = true;
      Object.defineProperty(target, descriptor.key, descriptor);
    }
  }
  return function(Constructor, protoProps, staticProps) {
    if (protoProps)
      defineProperties(Constructor.prototype, protoProps);
    if (staticProps)
      defineProperties(Constructor, staticProps);
    return Constructor;
  };
})();
function _interopRequireDefault(obj) {
  return obj && obj.__esModule ? obj : {'default': obj};
}
function _classCallCheck(instance, Constructor) {
  if (!(instance instanceof Constructor)) {
    throw new TypeError('Cannot call a class as a function');
  }
}
function _inherits(subClass, superClass) {
  if (typeof superClass !== 'function' && superClass !== null) {
    throw new TypeError('Super expression must either be null or a function, not ' + typeof superClass);
  }
  subClass.prototype = Object.create(superClass && superClass.prototype, {constructor: {
      value: subClass,
      enumerable: false,
      writable: true,
      configurable: true
    }});
  if (superClass)
    Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass;
}
var _react = require('react');
var _react2 = _interopRequireDefault(_react);
var _invariant = require('invariant');
var _invariant2 = _interopRequireDefault(_invariant);
var _warning = require('warning');
var _warning2 = _interopRequireDefault(_warning);
var _RouteUtils = require('./RouteUtils');
var _PropTypes = require('./PropTypes');
var _React$PropTypes = _react2['default'].PropTypes;
var bool = _React$PropTypes.bool;
var func = _React$PropTypes.func;
var IndexRoute = (function(_React$Component) {
  _inherits(IndexRoute, _React$Component);
  function IndexRoute() {
    _classCallCheck(this, IndexRoute);
    _React$Component.apply(this, arguments);
  }
  IndexRoute.createRouteFromReactElement = function createRouteFromReactElement(element, parentRoute) {
    if (parentRoute) {
      parentRoute.indexRoute = _RouteUtils.createRouteFromReactElement(element);
    } else {
      _warning2['default'](false, 'An <IndexRoute> does not make sense at the root of your route config');
    }
  };
  IndexRoute.prototype.render = function render() {
    _invariant2['default'](false, '<IndexRoute> elements are for router configuration only and should not be rendered');
  };
  _createClass(IndexRoute, null, [{
    key: 'propTypes',
    value: {
      path: _PropTypes.falsy,
      ignoreScrollBehavior: bool,
      component: _PropTypes.component,
      components: _PropTypes.components,
      getComponents: func
    },
    enumerable: true
  }]);
  return IndexRoute;
})(_react2['default'].Component);
exports['default'] = IndexRoute;
module.exports = exports['default'];
