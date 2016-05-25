define(['ojrouter', 'ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout',
], function(router, oj, ko, $) {

   var viewModel = {
      
      initialize: function(parentRouter) {
         this.wizard = parentRouter.createChildRouter('wiz');
         this.wizard.addStates({
            'show': {
               label: 'Show',
               data: 'showChapter',
               isDefault: true
            },
            'edit': {
               label: 'Edit',
               data: 'editChapter'
            }
         });
      },

      chapter: ko.pureComputed(function() {
         return viewModel.wizard.parentRouter.getCurrentState();
      }),

      displayMode: ko.pureComputed(function() {
         return viewModel.wizard.getCurrentState();
      }),
   };

   return viewModel;
});
