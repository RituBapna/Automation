define(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojrouter', 'ojs/ojinputtext', 'ojs/ojdialog'
], function(oj, ko, $) {

   var state = null,
       stateCopy = null;

   function isDirty() {
      return viewModel.data() !== stateCopy.value;
   };

   var viewModel = {

      initialize: function(params) {
         if (!viewModel.router) {
            // Copy parent router
            viewModel.router = params.valueAccessor().params['ojRouter']['parentRouter'];
         }

         // Keep a clone of the current state
         state = viewModel.router.parent.currentState();
         stateCopy = $.extend({}, state);
         viewModel.label = state.label;
         viewModel.data = ko.observable(state.value);
      },

      canExit: function() {
         // var result = true;
         // if (isDirty()) {
         //    result = window.confirm('Are you sure you want to exit without saving?');
         // }
         // return result;

         if (isDirty()) {
            // Make a confirm dialog promise
            var promise = new Promise(function(resolve, reject) {
               $('#dlgOkBtn').click(function() {
                  resolve(true);
                  $('#exitDialog').ojDialog('close');
               });
               $('#exitDialog').ojDialog({
                  'close': function(event, ui) {
                     // No-op if promise already resolved (like when 'OK' is clicked)
                     resolve(false);
                  }
               });

               $('#exitDialog').ojDialog('open');
            });

            return promise;
         }
         return true;
      },

      // The Ok button click
      ok: function(data, event) {
         if (isDirty()) {
            // Assign the new value to the current state
            stateCopy.value = state.value = viewModel.data();
         }
         viewModel.router.go('show');
         return true;
      },

      // The Cancel button click
      cancel: function(data, event) {
         // Restore local state data so canExit doesn't trigger confirm.
         viewModel.data(stateCopy.value);
         viewModel.router.go('show');
         return true;
      },

      dispose: function() {
         state = null;
         stateCopy = null;
         delete viewModel.router;
         delete viewModel.label;
         delete viewModel.data;
      }
   };

   return viewModel;
});
