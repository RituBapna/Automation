requirejs.config({
  // Path mappings for the logical module names
  paths: {
    'popupapp': '../../popup/popupTest/js/ojpopup/app',
    'knockout-amd-helpers': 'libs/knockout/knockout-amd-helpers',
    'text': 'libs/require/text'
  }
});

require(['ojs/ojcore',
  'knockout',
  'jquery',
  'popupapp',
  'knockout-amd-helpers',
  'text',
  'ojs/ojknockout',
  'ojs/ojbutton',
  'ojs/ojtoolbar',
  'ojs/ojmenu',
  'ojs/ojselectcombobox', 'ojs/ojdatetimepicker', 'ojs/ojradioset', 'ojs/ojcheckboxset',
  'ojs/ojinputtext', 'ojs/ojinputnumber'
],
function(oj, ko, $, app)
{

  ko.bindingHandlers.module.baseDir = "/popup/popupTest/js/ojpopup/modules";
  ko.amdTemplateEngine.defaultPath = "/popup/popupTest/ojpopup/templates";

  $(document).ready(function() {

    // Setup bindings for the header and footer then display everything
    //ko.applyBindings(new footer(), document.getElementById('footerWrapper'));
    //ko.applyBindings(new header(), document.getElementById('headerWrapper'));

    $('#globalBody').show();

    //Replace the null value below with a referece to your ViewModel for the content of your main area.
    //ko.applyBindings(new app(), document.getElementById('mainContainer'));
    ko.applyBindings(new app());
  });
});


