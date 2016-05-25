define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils','buttonBaseVM','ojs/ojknockout', 'ojs/ojbutton'],
function(oj, ko, $,bku, bbVM)
{
function ButtonsetModel() {
    var self = this;
    bbVM.call(this);
    var bk = new bku();
    self.module("ButtonsetModel");
    self.ojName("ojButtonset");
    self.id("#bs1");
    self.idList(["#bs1","#bs2", "#bs3","#bs4","#stoogeSet","#entertainerSet"]);

    // Can only set handlers after ids set
    self.setHandlers();

    var bk = new bku();
    this.isAdvanced = ko.observableArray([]);
    this.userText = ko.computed(function() {
        var mymsg = "User is: " + (this.isAdvanced().length ? "an expert" : "a beginner");
        oj.Logger.info(mymsg);
        return mymsg;
    }, this);

    // For checkbox tests, some custom stuff

    // for ojButtonset Push buttons
    // if the contents of the array can change, then replace the [...] with ko.observableArray([...])
    self.entertainers = [
        {label: "Frank", url: "https://en.wikipedia.org/wiki/Frank_Sinatra"},
        {label: "Dean", url: "https://en.wikipedia.org/wiki/Dean_Martin"},
        {label: "Sammy", url: "https://en.wikipedia.org/wiki/Sammy_Davis_Jr."},
    ];

    self.quotes = {
        "Moe": "Moe says: Why I oughta...!",
        "Curly": "Curly says: Oh, a wise guy, eh?",
        "Larry": "Larry says: What's the idea!?"
    };
    self.quote = ko.observable("(No pokes yet)");
    self.buttonClick = function(data, event){
        self.quote(self.quotes[event.currentTarget.id]);
        return true;
    }

            //Checkbox and toggle button bindings
        self.formattedText = ko.observable("The quick brown fox jumps over the lazy dog!");
        self.setBold = ko.observable(true);
        self.setItalic = ko.observable(false);
        self.setUnderline = ko.observable(false);

        $('#formattedText').addClass('bold');

        self.toggleBold = function() {
            var isBold = $("#check1").is(':checked');
            bk.msgLog("toggleBold " + isBold);
            if (isBold) {
                bk.msgLog("toggleBold: adding class bold");
                $("#formattedText").addClass("bold");
            } else {
                bk.msgLog("toggleBold: removing class bold");
                $("#formattedText").removeClass("bold");
            }
            return true;
        };

        self.toggleItalic = function() {
            var isItalic = $("#check2").is(':checked')
            bk.msgLog("toggleItalic " + isItalic);
            if (isItalic) {
                bk.msgLog("toggleItalic: adding class italic");
                $("#formattedText").addClass("italic");
            } else {
                bk.msgLog("toggleItalic: removing class italic");
                $("#formattedText").removeClass("italic");
            }
            return true;
        };

        self.toggleUnderline = function() {
            var isUnderline = $("#check3").is(':checked')
            bk.msgLog("toggleUnderline " + isUnderline);
            if (isUnderline) {
                bk.msgLog("toggleUnderline: adding class underline");
                $("#formattedText").addClass("underline");
            } else {
                bk.msgLog("toggleUnderline: removing class underline");
                $("#formattedText").removeClass("underline");
            }
            return true;
        };


    // For radio tests, some custom stuff
        // if the labels can change, then replace the [...] with ko.observableArray([...])
    self.drinkRadios = [
        {id: 'coffee', label: 'Coffee'},
        {id: 'tea',    label: 'Tea'},
        {id: 'milk',   label: 'Milk'},
    ];
    // observable bound to the Buttonset:
    self.drink = ko.observable("tea");
    // use that observable:
    self.drinkText = ko.computed(function() {
        var selectedDrink = this.drink();
        var mymsg =  "Now serving: " + (selectedDrink ? selectedDrink : "A dry dusty glass of nothing");
        // bk.msgLog(mymsg);
        return mymsg;
    }, this);
    // illustrates the app setting the observable:
    self.toggleCoffee = function() {
        var coffeeChecked = this.drink() == "coffee";
        this.drink(coffeeChecked ? null : "coffee");
    };
    self.enableDrinkset = function() {
        var curVal = $( "#drinkset" ).ojButtonset( "option", "disabled");
        bk.msgLog("#drinkset option disabled = " + curVal + ", setting to false");
        $( "#drinkset" ).ojButtonset( "option", "disabled", false );
        $( "#drinkset" ).ojButtonset( "refresh" );
        curVal = $( "#drinkset" ).ojButtonset( "option", "disabled");
        bk.msgLog("#drinkset option disabled now = " + curVal);
    };




    oj.Logger.info("ButtonsetModel created");
}
    ButtonsetModel.prototype = Object.create(bbVM.prototype);
    ButtonsetModel.prototype.constructor = ButtonsetModel;

return ButtonsetModel;
});
