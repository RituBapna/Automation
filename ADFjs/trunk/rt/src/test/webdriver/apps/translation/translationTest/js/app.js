define([ 'ojs/ojcore', 'knockout','ojs/ojknockout-validation', 'ojs/ojvalidation'], 
      function ( oj, ko) {
/* 
 * Your application specific code will go here
 */


 function employeeVM() {
    var self = this;
  
     self.required = new oj.RequiredValidator();
     self.redcolor = ko.observable(oj.Translations.getResource("red"));
     self.bluecolor = ko.observable(oj.Translations.getResource("blue"));
     self.greencolor = ko.observable(oj.Translations.getResource("green"));
     self.buttonLabel = ko.observable(oj.Translations.getResource("buttonLabel"));
     self.inputTextLabel = ko.observable(oj.Translations.getResource("inputTextLabel"));
     self.inputTextPlaceholderValue = ko.observable(oj.Translations.getResource("inputTextPlaceholderValue"));
     self.titleValue = ko.observable(oj.Translations.getResource("titleValue"));
     self.inputTextValue = ko.observable("someValue");
     
      
  self.switchLocale = function(d, e){
    if (oj.Config.getLocale()=="fr")
     oj.Config.setLocale("en-us",self.switchPageTranslation );
     else
     oj.Config.setLocale("fr",self.switchPageTranslation );
     
    
      return false;
  }
   self.switchPageTranslation = function(){
      self.redcolor(oj.Translations.getResource("red"));
      self.bluecolor(oj.Translations.getResource("blue"));
      self.greencolor(oj.Translations.getResource("green"));
      self.buttonLabel(oj.Translations.getResource("buttonLabel"));  
      self.inputTextLabel(oj.Translations.getResource("inputTextLabel"));
      self.inputTextPlaceholderValue(oj.Translations.getResource("inputTextPlaceholderValue"));
      self.titleValue(oj.Translations.getResource("titleValue"));
     }
}

$(document).ready(function() {
    //console.log("oj current locale setting: " +   oj.Config.getLocale());
    //console.log("translated red value: (app.js) " + oj.Translations.getResource("red"));
  
    ko.applyBindings(new employeeVM(), document.getElementById('mainContent'));
   
});
});