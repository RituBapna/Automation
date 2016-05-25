requirejs.config({
  paths: {
    'ojPopupApp': '../../popup/popupTest/js/ojpopup/ojPopupAppVM_API'
  }
});

require(['ojs/ojcore',
  'knockout',
  'jquery',
  'ojPopupApp',
  'ojs/ojmodel',
  'ojs/ojknockout',
  'ojs/ojknockout-model',
  'ojs/ojinputnumber', 'ojs/ojtable', 'ojs/ojdatagrid',
  'ojs/ojinputtext', 'ojs/ojdatetimepicker', 'ojs/ojtree', 'ojs/ojchart',
  'ojs/ojdialog', 'ojs/ojmenu', 'ojs/ojtabs',
  'ojs/ojselectcombobox', 'ojs/ojtrain',
  'ojs/ojaccordion', 'ojs/ojcollapsible', 'ojs/ojradioset',
  'ojs/ojknockout-validation',
  'ojs/ojvalidation',
],
function(oj, ko, $, appvm)
{
  // add any startup code that you want here
  $(document).ready(function()
  {

    popupappvm = new appvm();
    // popupvm = new vm();

    $("#popupspan").on('ojcreate', function(e, d)
    {
      console.log("popup from span Created")
    });


    var options = {'initialFocus': popupappvm.initialFocus(), 'autoDismiss': popupappvm.autoDismiss(), 'chrome': popupappvm.chrome(), 'tail': popupappvm.tail(), 'position': popupappvm.positionValue(), 'create': popupappvm.popupCreateHandler};
    $("#popupdiv").ojPopup(options);

    $("#ad").on('ojoptionchange', function(e, d)
    {
      if (d.option == 'value') {
        $("#popupdiv").ojPopup("option", "autoDismiss", d.value[0]);
        $("#popupdiv").ojPopup("refresh");
      }
    });

    $("#c").on('ojoptionchange', function(e, d)
    {
      if (d.option == 'value') {
        $("#popupdiv").ojPopup("option", "chrome", d.value[0]);
        $("#popupdiv").ojPopup("refresh");
      }
    });

    $("#if").on('ojoptionchange', function(e, d)
    {
      if (d.option == 'value') {
        $("#popupdiv").ojPopup("option", "initialFocus", d.value[0]);
        $("#popupdiv").ojPopup("refresh");
      }
    });

    $("#t").on('ojoptionchange', function(e, d)
    {
      if (d.option == 'value') {
        $("#popupdiv").ojPopup("option", "tail", d.value[0]);
        $("#popupdiv").ojPopup("refresh");
      }
    });

    ko.applyBindings(popupappvm, document.getElementById('main-container'));

    //  ko.applyBindings(popupvm, document.getElementById('popup-container'));
    $("#train").on("ojoptionchange", function(e, d)
    {
      oj.Logger.info("optionChange called...");
      //   $("#train").ojTrain("deselect", d.previousValue, "off", "disabled", "complete");
      //   $("#train").ojTrain("select", d.value);

    });

    $("#train1").on("ojoptionchange", function(e, d)
    {
      oj.Logger.info("optionChange called...");
      //  $("#train").ojTrain("deselect", d.previousValue, "off", "disabled", "complete");
      //   $("#train").ojTrain("select", d.value);

    });

  });
}
);




