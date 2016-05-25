define(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojrouter'
], function(oj, ko, $) {

   var viewModel = {

      initialize: function(params) {
         viewModel.router = params.valueAccessor().params['ojRouter']['parentRouter'];
         // The current chapter is the state of the parent router.
         this.chapter = viewModel.router.parent.currentState;
      },

   };

   return viewModel;

});
