/**
 * Header module
 */
define(['knockout', 
 'ojs/ojknockout', 'ojs/ojbutton', 'ojs/ojtoolbar'
  ], function(ko) {

   /**
    * The view model for the header module
    */ 
   function viewModel() {

      // Data for application name
      var appName = {
        'id': 'sample',
        'name': 'JET Router Sample'
      };

      this.appId = appName.id;
      this.appName = appName.name;
   };

   return viewModel;
});