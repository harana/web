'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

function _interopDefault (ex) { return (ex && (typeof ex === 'object') && 'default' in ex) ? ex['default'] : ex; }

var React = require('react');
var React__default = _interopDefault(React);
var $ = _interopDefault(require('jquery'));
var PropTypes = _interopDefault(require('prop-types'));
var moment = _interopDefault(require('moment'));
require('bootstrap-daterangepicker');

var getOptions = (function () {
    return ['<input>', 'alwaysShowCalendars', 'applyClass', 'autoApply', 'autoUpdateInput', 'buttonClasses', 'cancelClass', 'dateLimit', 'drops', 'endDate', 'isCustomDate', 'isInvalidDate', 'linkedCalendars', 'locale', 'maxDate', 'minDate', 'opens', 'parentEl', 'ranges', 'showCustomRangeLabel', 'showDropdowns', 'showISOWeekNumbers', 'showWeekNumbers', 'singleDatePicker', 'startDate', 'template', 'timePicker', 'timePicker24Hour', 'timePickerIncrement', 'timePickerSeconds'];
});

var classCallCheck = function (instance, Constructor) {
    if (!(instance instanceof Constructor)) {
        throw new TypeError("Cannot call a class as a function");
    }
};

var createClass = function () {
    function defineProperties(target, props) {
        for (var i = 0; i < props.length; i++) {
            var descriptor = props[i];
            descriptor.enumerable = descriptor.enumerable || false;
            descriptor.configurable = true;
            if ("value" in descriptor) descriptor.writable = true;
            Object.defineProperty(target, descriptor.key, descriptor);
        }
    }

    return function (Constructor, protoProps, staticProps) {
        if (protoProps) defineProperties(Constructor.prototype, protoProps);
        if (staticProps) defineProperties(Constructor, staticProps);
        return Constructor;
    };
}();

var inherits = function (subClass, superClass) {
    if (typeof superClass !== "function" && superClass !== null) {
        throw new TypeError("Super expression must either be null or a function, not " + typeof superClass);
    }

    subClass.prototype = Object.create(superClass && superClass.prototype, {
        constructor: {
            value: subClass,
            enumerable: false,
            writable: true,
            configurable: true
        }
    });
    if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass;
};

var possibleConstructorReturn = function (self, call) {
    if (!self) {
        throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
    }

    return call && (typeof call === "object" || typeof call === "function") ? call : self;
};

var DateRangePicker = function (_Component) {
    inherits(DateRangePicker, _Component);

    function DateRangePicker(props) {
        classCallCheck(this, DateRangePicker);

        var _this = possibleConstructorReturn(this, (DateRangePicker.__proto__ || Object.getPrototypeOf(DateRangePicker)).call(this, props));

        _this.state = { inputValue: '', isTyped: false };
        _this.$picker = null;
        _this.options = getOptions();
        _this.handleInput = _this.handleInput.bind(_this);
        _this.handleKeypress = _this.handleKeypress.bind(_this);
        _this.handleBlur = _this.handleBlur.bind(_this);
        return _this;
    }

    createClass(DateRangePicker, [{
        key: 'makeEventHandler',
        value: function makeEventHandler(eventType) {
            var _this2 = this;

            var onEvent = this.props.onEvent;

            return function (event, picker) {
                if (typeof onEvent === 'function') {
                    onEvent(event, picker);
                }
                if (typeof _this2.props[eventType] === 'function') {
                    _this2.props[eventType](event, picker);
                }
                if (!_this2.props.children && eventType === 'onApply') {
                    if (typeof _this2.props.handleSet === 'function') {
                        _this2.props.handleSet(picker.startDate.format(_this2.props.locale.format), picker.endDate.format(_this2.props.locale.format));
                    }
                }
            };
        }
    }, {
        key: 'getOptionsFromProps',
        value: function getOptionsFromProps(props) {
            var options;
            props = props || this.props;
            this.options.forEach(function (option) {
                if (props.hasOwnProperty(option) && (props[option] !== null || option === 'startDate' || option === 'endDate')) {
                    options = options || {};
                    if (option === 'startDate' && !props[option] || option === 'endDate' && !props[option]) {
                        var defaultDate = moment().startOf('day');
                        options[option] = defaultDate;
                    } else {
                        options[option] = props[option];
                    }
                }
            });
            return options || {};
        }
    }, {
        key: 'setOptionsFromProps',
        value: function setOptionsFromProps(currentOptions) {
            var _this3 = this;

            var keys = Object.keys(currentOptions);
            if (this.$picker) {
                if (currentOptions) {
                    keys.forEach(function (key) {
                        if (key === 'startDate') {
                            _this3.$picker.data('daterangepicker').setStartDate(currentOptions[key]);
                        } else if (key === 'endDate') {
                            _this3.$picker.data('daterangepicker').setEndDate(currentOptions[key]);
                        } else if (key === 'locale') {
                            $.extend(_this3.$picker.data('daterangepicker')[key], currentOptions[key]);
                        } else {
                            _this3.$picker.data('daterangepicker')[key] = currentOptions[key];
                        }
                    });
                }
            }
        }
    }, {
        key: 'componentDidUpdate',
        value: function componentDidUpdate(prevProps) {
            if (prevProps.startDate !== this.props.startDate || prevProps.endDate !== this.props.endDate) {
                if (!this.props.startDate) {
                    this.setState({ inputValue: '' });
                }
                if (this.props.startDate && !this.props.endDate || this.props.startDate === this.props.endDate) {
                    this.setState({ inputValue: this.props.startDate });
                } else {
                    this.setState({
                        inputValue: this.props.startDate + ' - ' + this.props.endDate
                    });
                }
            }
        }
    }, {
        key: 'componentWillReceiveProps',
        value: function componentWillReceiveProps(nextProps) {
            if (this.$picker) {
                var currentOptions = this.getOptionsFromProps();
                var nextOptions = this.getOptionsFromProps(nextProps);
                var changedOptions = {};
                this.options.forEach(function (option) {
                    if (currentOptions[option] !== nextOptions[option]) {
                        changedOptions[option] = nextOptions[option];
                    }
                });
                this.setOptionsFromProps(changedOptions);
            }
        }
    }, {
        key: 'componentDidMount',
        value: function componentDidMount() {
            this.initializeDateRangePicker();
            if (!this.props.startDate) {
                this.setState({ inputValue: '' });
            } else if (this.props.startDate && !this.props.endDate || this.props.startDate === this.props.endDate) {
                this.setState({ inputValue: this.props.startDate });
            } else {
                this.setState({
                    inputValue: this.props.startDate + ' - ' + this.props.endDate
                });
            }
        }
    }, {
        key: 'componentWillUnmount',
        value: function componentWillUnmount() {
            this.removeDateRangePicker();
        }
    }, {
        key: 'removeDateRangePicker',
        value: function removeDateRangePicker() {
            if (this.$picker && this.$picker.data('daterangepicker')) {
                this.$picker.data('daterangepicker').remove();
            }
        }
    }, {
        key: 'initializeDateRangePicker',
        value: function initializeDateRangePicker() {
            var _this4 = this;

            // initialize
            this.$picker.daterangepicker(this.getOptionsFromProps(),function(start, end, label) {
                _this4.props.onSelect(start ? start.valueOf().toString() : "", end ? end.valueOf().toString() : "", label ? label : "");
            });

            // attach event listeners
            ['Show', 'Hide', 'ShowCalendar', 'HideCalendar', 'Apply', 'Cancel'].forEach(function (event) {
                var lcase = event.toLowerCase();
                _this4.$picker.on(lcase + '.daterangepicker', _this4.makeEventHandler('on' + event));
            });

            var daterangepicker = this.$picker.data("daterangepicker");
            var tabletMinResolution = parseInt(this.props.tabletMinResolution);
            daterangepicker._move = daterangepicker.move;
            daterangepicker.move = function () {
                this._move();
                this.container.css("width", "");
                if (!isNaN(tabletMinResolution) && $(window).width() < tabletMinResolution) {
                    this.container.width(this.container.hasClass('show-calendar') ? this.container.find('.calendar.left').outerWidth() : this.container.find('.ranges').outerWidth());
                }
                if (this.opens !== 'left' && this.opens !== 'center') {
                    if (this.container.offset().left + this.container.outerWidth() >= $(window).width()) {
                        this.container.css({
                            left: 'auto',
                            right: 0
                        });
                    }
                }
            };
        }
    }, {
        key: 'handleInput',
        value: function handleInput(evt) {
            this.setState({ inputValue: evt.target.value });
        }
    }, {
        key: 'handleKeypress',
        value: function handleKeypress(evt) {
            if (evt.charCode === 13) {
                if (evt.target.value) {
                    this.props.handleSet(this.$picker.data('daterangepicker').startDate.format(this.props.locale.format), this.$picker.data('daterangepicker').endDate.format(this.props.locale.format));
                } else {
                    this.props.handleSet(null, null);
                }
            }
            this.setState({ isTyped: true });
        }
    }, {
        key: 'handleBlur',
        value: function handleBlur(evt) {
            if (this.state.isTyped) {
                this.props.handleSet(this.$picker.data('daterangepicker').startDate.format(this.props.locale.format), this.$picker.data('daterangepicker').endDate.format(this.props.locale.format));
            }
            if (!evt.target.value && this.state.isTyped) {
                this.props.handleSet(null, null);
            }
            this.setState({ isTyped: false });
        }
    }, {
        key: 'render',
        value: function render() {
            var _this5 = this;

            var _props = this.props,
                containerStyles = _props.containerStyles,
                containerClass = _props.containerClass,
                children = _props.children;

            if (children) {
                return React__default.createElement(
                    'div',
                    {
                        ref: function ref(picker) {
                            _this5.$picker = $(picker);
                        },
                        className: containerClass,
                        style: containerStyles
                    },
                    children
                );
            } else {
                return React__default.createElement('input', {
                    ref: function ref(picker) {
                        _this5.$picker = $(picker);
                    },
                    className: containerClass,
                    style: containerStyles,
                    value: this.state.inputValue ? this.state.inputValue : '',
                    onChange: this.handleInput,
                    onKeyDown: this.handleKeypress,
                    onBlur: this.handleBlur,
                    placeholder: this.props.inputPlaceholder ? this.props.inputPlaceholder : this.props.locale.format.toLowerCase() + ' - ' + this.props.locale.format.toLowerCase()
                });
            }
        }
    }]);
    return DateRangePicker;
}(React.Component);

DateRangePicker.defaultProps = {
    containerClass: 'react-bootstrap-daterangepicker-container',
    containerStyles: {
        display: 'inline-block'
    }
};

DateRangePicker.propTypes = {
    '<input>': PropTypes.any,
    alwaysShowCalendars: PropTypes.bool,
    applyClass: PropTypes.string,
    autoApply: PropTypes.bool,
    autoUpdateInput: PropTypes.bool,
    buttonClasses: PropTypes.array,
    cancelClass: PropTypes.string,
    containerClass: PropTypes.string,
    containerStyles: PropTypes.object,
    dateLimit: PropTypes.object,
    drops: PropTypes.oneOf(['down', 'up']),
    endDate: PropTypes.oneOfType([PropTypes.object, PropTypes.string]),
    isCustomDate: PropTypes.func,
    isInvalidDate: PropTypes.func,
    linkedCalendars: PropTypes.bool,
    locale: PropTypes.object,
    maxDate: PropTypes.oneOfType([PropTypes.object, PropTypes.string]),
    minDate: PropTypes.oneOfType([PropTypes.object, PropTypes.string]),
    onApply: PropTypes.func,
    onCancel: PropTypes.func,
    onEvent: PropTypes.func,
    onHide: PropTypes.func,
    onHideCalendar: PropTypes.func,
    onSelect: PropTypes.func,
    onShow: PropTypes.func,
    onShowCalendar: PropTypes.func,
    opens: PropTypes.oneOf(['left', 'right', 'center']),
    parentEl: PropTypes.any,
    ranges: PropTypes.object,
    showCustomRangeLabel: PropTypes.bool,
    showDropdowns: PropTypes.bool,
    showISOWeekNumbers: PropTypes.bool,
    showWeekNumbers: PropTypes.bool,
    singleDatePicker: PropTypes.bool,
    startDate: PropTypes.oneOfType([PropTypes.object, PropTypes.string]),
    template: PropTypes.any,
    timePicker: PropTypes.bool,
    timePickerIncrement: PropTypes.number,
    timePicker24Hour: PropTypes.bool,
    timePickerSeconds: PropTypes.bool
};

exports.DateRangePicker = DateRangePicker;
exports.default = DateRangePicker;