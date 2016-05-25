/*
 * Copyright (c) 2008, 2013, Oracle and/or its affiliates. All rights reserved.
 */
define(['ojL10n!nls/MessageBundle', 'ojcore'], function(bundle, oj) {
  /**
   * @constructor Instantiates a RequiredValidator that ensures the value provided is not empty
   * @param {Object} options
   */
  function RequiredValidator(options) {
    this.Init(options);
  };

  // Subclass from oj.Object 
  oj.Object.createSubclass(RequiredValidator, oj.Validator, "RequiredValidator");

  /**
   * Initializes validator instance with the set options
   * @param {type} options
   */
  RequiredValidator.prototype.Init = function(options) 
  {
    RequiredValidator.superclass.Init.call(this);
    this._options = options;
  };

  /**
   * Returns the options set on the validator instance
   * @returns {Object} options
   */
  RequiredValidator.prototype.getOptions = function() 
  {
    return this._options;
  };

  /**
   * checks value for emptiness and returns the custom message or a default
   * @param {type} value
   * @returns {Object}
   * @throws {ValidatorException} TODO
   */
  RequiredValidator.prototype.validate = function(value)
  {
    var options = this.getOptions();

    if (value&& value.length > 0)
    {
      return true;
    }
    else
    {
      var message = (options && options.message) ? options.message : bundle['validator-required']['summary'];
      throw new Error(message);
    }
  };
  
  return RequiredValidator;
});