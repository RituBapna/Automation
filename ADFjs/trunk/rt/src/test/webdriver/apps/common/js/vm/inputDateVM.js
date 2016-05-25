define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'inputDateTimeVM','ojs/ojknockout',
  'ojs/ojinputnumber', 'ojs/ojbutton'],
function(oj, ko, $, bku, inputDateTime)
{
    function InputDateModel(){
        self = this;
        inputDateTime.call(this);
        var bk = new bku();
        self.module("InputDateModel");
        self.ojName("ojInputDate");
        self.id("#text-input");
        self.idList(["#text-input","#inline-input"]);
        self.subIdList(['oj-datepicker-content','oj-datepicker-current','oj-datepicker-month',
            'oj-datepicker-next-icon','oj-datepicker-prev-icon','oj-datepicker-year','oj-inputdatetime-calendar-icon',
            'oj-inputdatetime-date-input']);


        // Can only set handlers after ids set
        self.setHandlers();

        self.minList([null,oj.IntlConverterUtils.dateToLocalIso(new Date(2014,6,1)),"2014-06-01T01:00:00.000"]);
        self.maxList([null,oj.IntlConverterUtils.dateToLocalIso(new Date(2014,6,5)),"2014-06-05T01:00:00.000"]);
        self.valueList = [null,oj.IntlConverterUtils.dateToLocalIso(new Date(2014,6,2)),oj.IntlConverterUtils.dateToLocalIso(new Date(2014,5,14)),"2014-06-02T01:00:00.000"];
        self.value(oj.IntlConverterUtils.dateToLocalIso(new Date(2014,5,14)));
        self.value2(oj.IntlConverterUtils.dateToLocalIso(new Date(2014,5,14)));

        var OddDayValidator = function () { };
        oj.Object.createSubclass(OddDayValidator, oj.Validator, "OddDayValidator");

          OddDayValidator.prototype.validate = function (value)
          {
            if (!value)
            {
              return value;
            }

            value = oj.IntlConverterUtils.isoToLocalDate(value);
            var minutes = value.getDate();
            //Check if day is odd
            if ((minutes % 2) !== 1)
            {
              throw new oj.ValidatorError("Uh oh, not an odd day.", "Hey put an odd day");
            }
            else
            {
              return value;
            }
          };

      OddDayValidator.prototype.getHint = function ()
      {
        return "Enter an odd day.";
      };

        var EvenDayValidator = function () { };
        oj.Object.createSubclass(EvenDayValidator, oj.Validator, "EvenDayValidator");

          EvenDayValidator.prototype.validate = function (value)
          {
            if (!value)
            {
              return value;
            }

            value = oj.IntlConverterUtils.isoToLocalDate(value);
            var minutes = value.getDate();
            //Check if day is even
            if ((minutes % 2) !== 0)
            {
              throw new oj.ValidatorError("Uh oh, not an even day.", "Hey put an even day");
            }
            else
            {
              return value;
            }
          };

      EvenDayValidator.prototype.getHint = function ()
      {
        return "Enter an even day.";
      };


        self.validatorsList = [[new OddDayValidator()],[new EvenDayValidator()]];

        var datetimeConverterFactory = oj.Validation.converterFactory(oj.ConverterFactory.CONVERTER_TYPE_DATETIME);
        self.dateConverter1 = datetimeConverterFactory.createConverter({pattern:"yy-MM-dd"});
        self.dateConverter2 = datetimeConverterFactory.createConverter({pattern:"MM/dd/yy"});
        self.dateConverter3 = datetimeConverterFactory.createConverter({pattern : "EEEE, MMMM d, y"});
        self.dateConverter4 = datetimeConverterFactory.createConverter({"day": "2-digit", "month": "2-digit", "year": "2-digit", "hour": "2-digit", "hour12": true, "minute": "2-digit"});

        self.converterList([self.dateConverter1, self.dateConverter2, self.dateConverter3,self.dateConverter4]);

        self.myInputDate =
            {component: 'ojInputDate',
                  placeholder: 'Enter a value',
                  title: self.title,
                  value: self.value};

    oj.Logger.info("InputDateModel created " + self.ojName());
    }

    InputDateModel.prototype = Object.create(inputDateTime.prototype);
    InputDateModel.prototype.constructor = InputDateModel;
    return InputDateModel;
});
