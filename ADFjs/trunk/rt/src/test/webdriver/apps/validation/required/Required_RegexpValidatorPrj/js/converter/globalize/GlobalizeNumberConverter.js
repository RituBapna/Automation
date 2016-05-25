/*
 * Copyright (c) 2008, 2013, Oracle and/or its affiliates. All rights reserved.
 */

define(['ojL10n!nls/MessageBundle', 'ojcore', 'jquery', 'globalize', 'globalize-en-US'], 
  function (bundle, oj, $, Globalize) {
  /**
   * @constructor Instantiates a GlobalizeNumberConverter that parses and formats a number value 
   * using Globalize rules.
   * @param {Object} options
   */
  function GlobalizeNumberConverter(options) {
    this.Init(options);
    this._STYLE_CURRENCY = "currency";
    this._DEFAULT_CURRENCY_FORMAT = "c0";
    this._DEFAULT_NUMBER_FORMAT = "d";
  };

  // Subclass from oj.Object 
  oj.Object.createSubclass(GlobalizeNumberConverter, oj.Converter, "GlobalizeNumberConverter");

  /**
   * Initializes converter instance with the set options
   * @param {type} options
   */
  GlobalizeNumberConverter.prototype.Init = function(options) 
  {
    GlobalizeNumberConverter.superclass.Init.call(this);
    this._options = options;
  };

  /**
   * Returns the options set on the converter instance
   * @returns {Object} options
   */
  GlobalizeNumberConverter.prototype.getOptions = function() 
  {
    return this._options;
  };

  /**
   * Parses a value into a number.
   * 
   * @param {String} value usually a String value representing a number or a formatted number
   * @returns {Number} the result of the parsed expression. This converter returns a Number
   */
  GlobalizeNumberConverter.prototype.parse = function(value) 
  {
    var parsedValue = null;
    var strval = value + "";
    var invalid = false;
    try
    {
      parsedValue = Globalize.parseInt(strval);
      // Globalize returns the parsed value as NaN
      if (isNaN(parsedValue))
        invalid = true;
    }
    catch (e)
    {
      invalid = true;
    }

    if (invalid)
      throw new Error(bundle['converter-globalizeNumber']['summary']);

    return parsedValue;
  };

  /**
   * Formats a value using the format options specified.
   * @param {Object} value
   * @returns {String} in the display format
   */
  GlobalizeNumberConverter.prototype.format = function(value) 
  {
    var options = this.getOptions();

    var gNumberFormat = (options.style === this._STYLE_CURRENCY) ? 
                          this._DEFAULT_CURRENCY_FORMAT : 
                          this._DEFAULT_NUMBER_FORMAT;
    if (typeof value === 'number')
    {
      // when reading a value we want to store the displayValue in the observable
      return Globalize.format(value, gNumberFormat);
    }

    return value;
  };

  /**
   * Returns a hint
   * @returns {String} the hint.
   */
  GlobalizeNumberConverter.prototype.getHint = function () 
  {
    var options = this.getOptions();
    if (options.type && options.type === this._STYLE_CURRENCY)
    {
      // some default hint text
      var symbol = Globalize.culture().numberFormat.currency.symbol + 34;
      // TODO use translationsManager
      return oj.Translations.applyParameters(bundle['converter-globalizeNumber']['hint-format'], symbol);
    }
    else
    {
      return bundle['converter-globalizeNumber']['hint'];
    }
  };
  
  return GlobalizeNumberConverter;
});