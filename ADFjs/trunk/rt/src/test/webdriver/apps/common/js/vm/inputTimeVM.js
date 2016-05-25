define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'inputDateTimeVM','ojs/ojknockout',
  'ojs/ojinputnumber', 'ojs/ojbutton'],
function(oj, ko, $, bku, inputDateTime)
{
    function InputTimeModel(){
        self = this;
        inputDateTime.call(this);
        var bk = new bku();
        self.module("InputTimeModel");
        self.ojName("ojInputTime");
        self.id("#text-input");
        self.idList(["#text-input"]);
        self.minList = ko.observableArray([null,"T01:00:00.00","2014-06-01T01:00:00.000"]);
        self.maxList = ko.observableArray([null,"T04:00:00.00","2014-06-01T04:00:00.000"]);
        self.subIdList(['oj-inputdatetime-time-icon','oj-inputdatetime-time-input','oj-listbox-drop']);


        // Can only set handlers after ids set
        self.setHandlers();

        var datetimeConverterFactory = oj.Validation.converterFactory(oj.ConverterFactory.CONVERTER_TYPE_DATETIME);
        self.timeConverter1 = datetimeConverterFactory.createConverter({ "hour": "2-digit", "hour12": true, "minute": "2-digit"});
        self.timeConverter2 = datetimeConverterFactory.createConverter({ "hour": "2-digit", "hour12": false, "minute": "2-digit"});

        self.converterList([self.timeConverter1, self.timeConverter2]);


        self.value(oj.IntlConverterUtils.dateToLocalIso(new Date(2014,5,14,12,30,20)));
        self.value2 = ko.observable(oj.IntlConverterUtils.dateToLocalIso(new Date(2014,5,14,13,30,10)));

    oj.Logger.info("InputTimeModel created " + self.ojName());
    }

    InputTimeModel.prototype = Object.create(inputDateTime.prototype);
    InputTimeModel.prototype.constructor = InputTimeModel;

    return InputTimeModel;
});
