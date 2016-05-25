/*
 * Copyright (c) 2008, 2013, Oracle and/or its affiliates. All rights reserved.
 */
define(['ojL10n!nls/MessageBundle', 'ojcore', 'jquery', 'globalize', 'globalize-en-US'], 
  function (bundle, oj, $, Globalize) {
  /**
   * @constructor Instantiates a GlobalizeDateConverter that parses and formats date values using 
   * Globalize's rules.
   * @param {Object} options
   */
  function GlobalizeDateConverter(options) {
    this.Init(options);
    GlobalizeDateConverter._DEFAULT_DATE_FORMAT = "d";
  };

  // Subclass from oj.Object 
  oj.Object.createSubclass(GlobalizeDateConverter, oj.Converter, "GlobalizeDateConverter");

  /**
   * Initializes converter instance with the set options
   * @param {type} options
   */
  GlobalizeDateConverter.prototype.Init = function(options) 
  {
    GlobalizeDateConverter.superclass.Init.call(this);
    this._options = options;
  };

  /**
   * Returns the options set on the converter instance
   * @returns {Object} options
   */
  GlobalizeDateConverter.prototype.getOptions = function() 
  {
    return this._options;
  };

  /**
   * Parses a value into a date.
   * 
   * @param {String} value usually a String value representing a date in some format
   * @returns {Number} the result of the parsed expression. This converter returns a Date
   */
  GlobalizeDateConverter.prototype.parse = function(value) 
  {
    var strval = value + "", invalid = false, parsedVal;
    try
    {
      parsedVal = +Globalize.parseDate(strval);
      if (!parsedVal && strval)
        invalid = true;
    }
    catch (e)
    {
      invalid = true;
    }

    if (invalid)
      throw new Error(bundle['converter-globalizeDate']['summary']);
    return parsedVal;
  };

  /**
   * Formats a value using the format options specified.
   * @param {Object} value
   * @returns {String} in the display format
   */
  GlobalizeDateConverter.prototype.format = function(value) 
  {
    var options = this.getOptions();
    var format = options['format'];

    if (!format)
      format = GlobalizeDateConverter._DEFAULT_DATE_FORMAT;
    return Globalize.format(new Date(value), format);
  };

  /**
   * Returns a hint
   * @returns {String} the hint.
   */
  GlobalizeDateConverter.prototype.getHint = function () 
  {
    return "";
  };
  
  return GlobalizeDateConverter;
});