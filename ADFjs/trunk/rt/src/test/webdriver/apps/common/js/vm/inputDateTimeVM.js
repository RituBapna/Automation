define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'inputBaseVM','ojs/ojknockout',
  'ojs/ojinputnumber', 'ojs/ojbutton'],
function(oj, ko, $, bku, inputBase)
{
  var FifteenTimeIncrementValidator = function () { };
  var ThirtyTimeIncrementValidator = function () { };

  oj.Object.createSubclass(FifteenTimeIncrementValidator, oj.Validator, "FifteenTimeIncrementValidator");
  oj.Object.createSubclass(ThirtyTimeIncrementValidator, oj.Validator, "ThirtyTimeIncrementValidator");

  FifteenTimeIncrementValidator.prototype.validate = function (value)
  {
    if (!value)
    {
      return value;
    }

    value = oj.IntlConverterUtils.isoToLocalDate(value);
    var minutes = value.getMinutes();
    //Check if the minute is in increment of 15 by taking a modulo
    if ((minutes % 15) !== 0)
    {
      throw new oj.ValidatorError("Uh oh, not an increment value.", "Hey put the increment value");
    }
    else
    {
      return value;
    }
  };

  ThirtyTimeIncrementValidator.prototype.validate = function (value)
  {
    if (!value)
    {
      return value;
    }

    value = oj.IntlConverterUtils.isoToLocalDate(value);
    var minutes = value.getMinutes();
    //Check if the minute is in increment of 30 by taking a modulo
    if ((minutes % 30) !== 0)
    {
      throw new oj.ValidatorError("Only on hour or half allowed.", "Choose hour or half");
    }
    else
    {
      return value;
    }
  };


  FifteenTimeIncrementValidator.prototype.getHint = function ()
  {
    return "Enter a time divisible by 15.";
  };

  ThirtyTimeIncrementValidator.prototype.getHint = function ()
  {
    return "Enter a time divisible by 30.";
  };

    function InputDateTimeModel(){
        self = this;
        inputBase.call(this);
        var bk = new bku();
        self.module("InputDateTimeModel");
        self.ojName("ojInputDateTime");
        self.id("#text-input");
        self.idList(["#text-input","#inline-input"]);
        self.timeIncrementList = ko.observableArray(["01:00:00:00","00:01:00:00"]);
        self.timeIncrement = ko.observable("01:00:00:00");
        self.numberOfMonths = ko.observable(1);
        self.numberOfMonthsList = ko.observableArray([1,3,6]);
        self.daysOutsideMonth = ko.observable("");
        self.daysOutsideMonthList = ko.observableArray(["visible","selectable","hidden"]);
        self.currentMonthPos = ko.observable("");
        self.currentMonthPosList = ko.observableArray([2,1]);
        self.footerLayout = ko.observable("");
        self.footerLayoutList = ko.observableArray(["","today"]);
        self.changeMonth = ko.observable("");
        self.changeMonthList = ko.observableArray(["none","select"]);
        self.changeYear = ko.observable("");
        self.changeYearList = ko.observableArray(["none","select"]);
        self.showOn = ko.observable("");
        self.showOnList = ko.observableArray(["focus","image"]);
        self.stepBigMonths = ko.observable("");
        self.stepBigMonthsList = ko.observableArray([24,12]);
        self.stepMonths = ko.observable("numberOfMonths");
        self.stepMonthsList = ko.observableArray(["numberOfMonths",3]);
        self.weekDisplay = ko.observable("");
        self.weekDisplayList = ko.observableArray(["number","none"]);
        self.yearRange = ko.observable("c-10:c+10");
        self.yearRangeList = ko.observableArray(["c-10:c+10","c-3:c+3"]);
        self.subIdList(['oj-datepicker-content','oj-datepicker-current','oj-datepicker-month',
            'oj-datepicker-next-icon','oj-datepicker-prev-icon','oj-datepicker-year','oj-inputdatetime-calendar-icon',
            'oj-inputdatetime-date-input','oj-inputdatetime-time-icon','oj-inputdatetime-time-input','oj-listbox-drop']);

//        self.minList = ko.observableArray([null,"2014-06-1T01:00:00.000Z"]);
//        self.maxList = ko.observableArray([null,"2014-06-5T01:00:00.000Z"]);

        // Can only set handlers after ids set
        self.setHandlers();

        self.minList = ko.observableArray([null,oj.IntlConverterUtils.dateToLocalIso(new Date(2014,6,1,12,30,10)),"2014-06-01T01:00:00.000"]);
        self.maxList = ko.observableArray([null,oj.IntlConverterUtils.dateToLocalIso(new Date(2014,6,5,13,30,20)),"2014-06-01T05:00:00.000"]);
        self.valueList = [null,oj.IntlConverterUtils.dateToLocalIso(new Date(2014,6,2,12,30,20)),oj.IntlConverterUtils.dateToLocalIso(new Date(2014,5,14,13,30,20)),"2014-06-02T01:00:00.000"];
        self.min = ko.observable();
        self.max = ko.observable();

//        self.value(oj.IntlConverterUtils.dateToLocalIso(new Date(2014,5,14,12,30,20)));
//        self.value2 = ko.observable(oj.IntlConverterUtils.dateToLocalIso(new Date(2014,5,14,13,30,10)));
        self.value = ko.observable();
        self.value2 = ko.observable();
//        self.value(oj.IntlConverterUtils.dateToLocalIso(new Date(2014,5,14)));
//        self.value2 = ko.observable(oj.IntlConverterUtils.dateToLocalIso(new Date(2014,5,14)));
        self.toValue = ko.observable("");

        var datetimeConverterFactory = oj.Validation.converterFactory(oj.ConverterFactory.CONVERTER_TYPE_DATETIME);
        self.dateConverter1 = datetimeConverterFactory.createConverter({"day": "2-digit", "month": "2-digit", "year": "2-digit", "hour": "2-digit", "hour12": false, "minute": "2-digit"});
        self.dateConverter2 = datetimeConverterFactory.createConverter({pattern : "EEEE, MMMM d, y HH:mm"});
        // self.dateConverter3 = datetimeConverterFactory.createConverter({pattern : "EEEE, MMMM d, y"});  // not supported
        self.dateConverter4 = datetimeConverterFactory.createConverter({"hour": "2-digit", "hour12": true, "minute": "2-digit"});

        self.converterList([self.dateConverter1, self.dateConverter2, self.dateConverter4]);


        self.dayMetaDataList = ko.observableArray([null,
        { '*' : { "12" : { "25" : { className : 'holiday', tooltip : 'xmas' }, "5" : { disabled : true} } },
          "2014" : { "1" : { '*' : { disabled : true }, "1" : { disabled : false } } } }]);

        self.dayMetaData = ko.observable();

        self.validatorsList = [[new FifteenTimeIncrementValidator()],[new ThirtyTimeIncrementValidator()]];

        self.toggleMin = function() {
            bk.cycle("min",self.minList(),self.min);
            self.setAllOption("min",self.min());
            self.setAllMethod("refresh");
        }

        self.toggleMax = function() {
            bk.cycle("max",self.maxList(),self.max);
            self.setAllOption("max",self.max());
            self.setAllMethod("refresh");
        }

        self.toggleTimeIncrement = function() {
            bk.cycle("timeIncrement",self.timeIncrementList(),self.timeIncrement);
            self.setAllOption("timePicker.timeIncrement",self.timeIncrement());
            self.setAllMethod("refresh");
        }

        self.toggleNumberOfMonths = function() {
            bk.cycle("numberOfMonths",self.numberOfMonthsList(),self.numberOfMonths);
            self.setAllOption("datePicker.numberOfMonths",self.numberOfMonths());
            self.setAllMethod("refresh");
        }

        self.toggleDaysOutsideMonth = function() {
            bk.cycle("daysOutsideMonth",self.daysOutsideMonthList(),self.daysOutsideMonth);
            self.setAllOption("datePicker.daysOutsideMonth",self.daysOutsideMonth());
            self.setAllMethod("refresh");
        }

        self.toggleCurrentMonthPos = function() {
            bk.cycle("currentMonthPos",self.currentMonthPosList(),self.currentMonthPos);
            self.setAllOption("datePicker.currentMonthPos",self.currentMonthPos());
            self.setAllMethod("refresh");
        }

        self.toggleFooterLayout = function() {
            bk.cycle("footerLayout",self.footerLayoutList(),self.footerLayout);
            self.setAllOption("datePicker.footerLayout",self.footerLayout());
            self.setAllMethod("refresh");
        }
        self.toggleChangeMonth = function() {
            bk.cycle("changeMonth",self.changeMonthList(),self.changeMonth);
            self.setAllOption("datePicker.changeMonth",self.changeMonth());
            self.setAllMethod("refresh");
        }
        self.toggleChangeYear = function() {
            bk.cycle("changeYear",self.changeYearList(),self.changeYear);
            self.setAllOption("datePicker.changeYear",self.changeYear());
            self.setAllMethod("refresh");
            }
        self.toggleShowOn = function() {
            bk.cycle("showOn",self.showOnList(),self.showOn);
            self.setAllOption("datePicker.showOn",self.showOn());
            self.setAllMethod("refresh");
        }
        self.toggleStepBigMonths = function() {
            bk.cycle("stepBigMonths",self.stepBigMonthsList(),self.stepBigMonths);
            self.setAllOption("datePicker.stepBigMonths",self.stepBigMonths());
            self.setAllMethod("refresh");
        }
        self.toggleStepMonths = function() {
            bk.cycle("stepMonths",self.stepMonthsList(),self.stepMonths);
            self.setAllOption("datePicker.stepMonths",self.stepMonths());
            self.setAllMethod("refresh");
        }
        self.toggleWeekDisplay = function() {
            bk.cycle("weekDisplay",self.weekDisplayList(),self.weekDisplay);
            self.setAllOption("datePicker.weekDisplay",self.weekDisplay());
            self.setAllMethod("refresh");
        }

        self.toggleYearRange = function() {
            bk.cycle("yearRange",self.yearRangeList(),self.yearRange);
            self.setAllOption("datePicker.yearRange",self.yearRange());
            self.setAllMethod("refresh");
        }

        self.hide = function() {
            self.get(self.id(),"hide");
        }

        self.show = function() {
            self.get(self.id(),"show");
        }

        self.toggleDayMetaData = function() {
            bk.cycle("dayMetaData",self.dayMetaDataList(),self.dayMetaData);
            self.setAllOption("dayMetaData",self.dayMetaData());
            self.setAllMethod("refresh");
        }

        self.getNodeBySubId = function() {
            self.showNode('oj-datepicker-content');
            self.showNode('oj-datepicker-current');
            self.showNode('oj-datepicker-month');
            self.showNode('oj-datepicker-next-icon');
            self.showNode('oj-datepicker-prev-icon');
            self.showNode('oj-datepicker-year');
            self.showNode('oj-inputdatetime-calendar-icon');
            }

        self.showNode = function(subid) {
            var val = $(self.id()).ojInputDateTime('getNodeBySubId',{'subId': subid});
            if (val instanceof Array) {
                bk.printArray(subid,val);
            } else
                bk.msgLog(subid + ":" + val);
        }

        self.dayFormatterFn1 = function (dateInfo) {
            var month = dateInfo["month"],date = dateInfo["date"], fullYear = dateInfo["fullYear"];
            if(date % 2)
            {
              return {disabled : true}
            }
            return {disabled: false};
        }

        self.dayFormatterList = ko.observableArray([null,self.dayFormatterFn1]);
        self.dayFormatter = ko.observable();

        self.toggleDayFormatter = function() {
            bk.cycle("min",self.dayFormatterList(),self.dayFormatter);
            self.setAllOption("dayFormatter",self.dayFormatter());
            self.setAllMethod("refresh");
        }


    oj.Logger.info("InputDateTimeModel created " + self.ojName());
    }

    InputDateTimeModel.prototype = Object.create(inputBase.prototype);
    InputDateTimeModel.prototype.constructor = InputDateTimeModel;

    return InputDateTimeModel;
});
