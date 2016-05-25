requirejs.config({
  paths: {
    'ojPopupApp': '../../popup/popupTest/js/ojpopup/ojPopupMiscApp'
  }
});

require(['ojs/ojcore',
  'knockout',
  'jquery',
  'ojPopupApp',
  'ojs/ojknockout',
  'ojs/ojknockout-model',
  'ojs/ojbutton',
  'ojs/ojpopup',
  'ojs/ojknockout-validation',
  'ojs/ojvalidation'
],
function(oj, ko, $, appvm)
{
  // add any startup code that you want here
  $(document).ready(function()
  {
    var appVM = new appvm();
    ko.applyBindings(appVM, document.getElementById('main-container'));
  });
}
);


