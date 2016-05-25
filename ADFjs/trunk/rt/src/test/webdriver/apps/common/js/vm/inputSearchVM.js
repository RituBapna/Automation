define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'comboboxVM','ojs/ojknockout',
  'ojs/ojcomponents', 'ojs/ojbutton', 'ojs/ojselectcombobox'],
function(oj, ko, $, bku, combobox)
{
    function InputSearchModel(){
        self = this;
        combobox.call(this);
        var bk = new bku();
        self.module("InputSearchModel");
        self.ojName("ojInputSearch");
        self.id("#inputSearch");
        self.idList(["#inputSearch"]);
        self.init = false;
        self.subIdList(['oj-inputsearch-input','oj-inputsearch-search','oj-listitem']);


        // Can only set handlers after ids set
        self.setHandlers();

        self.myInputSearch =
          {component: 'ojInputSearch', value: self.value,
                    multiple : self.multiple,
                    optionChange: self.valueChangeHandler,
                    beforeExpand : self.beforeExpandHandler,
                    rootAttributes: {style:'max-width:20em'}};

    oj.Logger.info("InputSearchModel created " + self.ojName());
    }

    InputSearchModel.prototype = Object.create(combobox.prototype);
    InputSearchModel.prototype.constructor = InputSearchModel;
    return InputSearchModel;
});
