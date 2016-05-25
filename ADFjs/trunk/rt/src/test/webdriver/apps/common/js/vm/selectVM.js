define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'comboboxVM','ojs/ojknockout',
  'ojs/ojinputnumber', 'ojs/ojbutton'],
function(oj, ko, $, bku, comboboxVM)
{
    function SelectModel(){
        self = this;
        comboboxVM.call(this);
        var bk = new bku();
        self.module("SelectModel");
        self.ojName("ojSelect");
        self.id("#select");
        self.idList(["#select","#grpdata","#optKeys","#tridata","#child"]);
        self.minimumResultsForSearch = ko.observable(10);
        self.minimumList = [10,2];
        self.subIdList(['oj-listbox-input','oj-listbox-result-label','oj-listitem',
          'oj-select-arrow','oj-select-chosen','oj-select-drop','oj-select-input',
          'oj-select-remove','oj-select-results','oj-select-search']);


        // Can only set handlers after ids set
        self.setHandlers();

        self.toggleMinResultsForSearch = function() {
            bk.cycle("minimumResultsForSearch",self.minimumList,self.minimumResultsForSearch);
            self.setAllOption("minimumResultsForSearch", self.minimumResultsForSearch());
            self.refresh();
        }


    oj.Logger.info("SelectModel created " + self.ojName());
    }

    SelectModel.prototype = Object.create(comboboxVM.prototype);
    SelectModel.prototype.constructor = SelectModel;

    return SelectModel;
});
