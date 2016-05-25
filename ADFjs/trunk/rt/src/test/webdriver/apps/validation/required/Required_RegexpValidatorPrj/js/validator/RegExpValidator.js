/*
 * Copyright (c) 2008, 2013, Oracle and/or its affiliates. All rights reserved.
 */
define(['ojL10n!nls/MessageBundle', 'ojcore'], function (bundle, oj) {
  /**
   * @constructor Instantiates a RegExpValidator that ensures the value matches the provided pattern
   * @param {String} options
   */
  function RegExpValidator(options) {
    this.Init(options);
  };

  // Subclass from oj.Object or oj.Validator. It does not matter
  oj.Object.createSubclass(RegExpValidator, oj.Object, "RegExpValidator");

  /**
   * Initializes validator instance with the set options
   * @param {type} options
   */
  RegExpValidator.prototype.Init = function(options) 
  {
    RegExpValidator.superclass.Init.call(this);
    this._options = options;
    this._pattern = "";
    if (options)
    {
      this._pattern = options['pattern'];
      this._hint = options['hint'];
      this._message = options['message'];
    }
  };

  /* 
   * To change this template, choose Tools | Templates
   * and open the template in the editor.
   */

  RegExpValidator.prototype.validate = function(parseString)
  {
    //For some reason when using digits as input values
    // parseString becomes a integer type, so get away with it.
    parseString = parseString + '';
    // We intend that the pattern provided is matched exactly
    var exactPattern = "^(" + this._pattern + ")$";
    var matchArr = parseString.match(exactPattern);
    if ((matchArr !== null) && (matchArr[0] === parseString))
    {
      return parseString;
    }
    else
    {
      var message = (this._message) ? this._message : bundle['validator-regExp']['summary'];
      throw new Error(message);
    }
  };

  RegExpValidator.prototype.getHint = function()
  {
    return this._hint;
  };
  
  return RegExpValidator;
});