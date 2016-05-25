define([
   'ojs/ojcore',
   'knockout',
   'jquery',
   'ojs/ojknockout','ojs/ojbutton','ojs/ojrouter',
   'ojs/ojnavigationlist'
], function(oj, ko, $) {

      var chapters = {
         'preface': 'Darn beamed hurriedly because banal more \
giraffe shuffled and well rid placidly where hence or and and hound lantern cutely \
instead inaudibly but demonstrable imitatively one regarding a where much fruitlessly \
more depending goodness less as dear shark directed this one.',
         'chapter1': 'Affectingly and yikes one that along \
versus growled unwitting vulnerably fish far pouting otter some as this hamster \
hatchet where amicably far deftly curtsied.',
         'chapter2': 'More up mistaken for a kissed therefore \
ahead thus on dear wow undertook flabbily less much far sourly impala resolutely and \
and as overheard dachshund this.',
         'chapter3': 'Reindeer up while the far darn falcon \
concurrent iguana this strived unicorn hedgehog depending more lemming was swam \
unlike prosperously regarding shameful when and extravagant that then cat contagious.'
      };

      /**
       * The view model for the book page.
       */
      var viewModel = {
         router: undefined,

         initialize: function(params) {
            var parentRouter = params.valueAccessor().params['ojRouter']['parentRouter'];
            // Restore current state from parent router, if exist.
            var currentState = parentRouter.currentState();
            if (!currentState.storage) {
               currentState.storage = chapters;
            }

            var canEnterCallBack = function() {

               oj.Logger.info('Create canEnterCallBack promise.');
               var promise = new Promise(function(resolve, reject) {
//                  var isOk = confirm("Do you want to enter 'chapter1' ?");

                  window.setTimeout(
                     function() {
                        oj.Logger.info('Resolve canEnterCallBack promise.');
                        resolve(false);
                     }, 3000);

                  // oj.Logger.info('Resolve canEnterCallBack promise.');
                  // resolve(isOk);

//                  reject(Error("No Good"));
               });

               return promise;
            }

            var canExitCallBack = function() {

               oj.Logger.info('Create canExitCallBack promise.');
               var promise = new Promise(function(resolve, reject) {
//                  var isOk = confirm("Do you want to enter 'chapter1' ?");

                  window.setTimeout(
                     function() {
                        oj.Logger.info('Resolve canExitCallBack promise.');
                        resolve(true);
                     }, 3000);

                  // oj.Logger.info('Resolve canEnterCallBack promise.');
                  // resolve(isOk);

//                  reject(Error("No Good"));
               });

               return promise;
            }

            this.router = parentRouter.createChildRouter('chapter')
               .configure({
                  'preface': {
                     label: 'Preface',
                     value: currentState.storage['preface']
//                        canExit: canExitCallBack
                     // exit:
                  },
                  'chapter1': {
                     label: 'Chapter 1',
//                        canEnter: canEnterCallBack,
                     value: currentState.storage['chapter1']
                  },
                  'chapter2': {
                     label: 'Chapter 2',
                     value: currentState.storage['chapter2']
                  },
                  'chapter3': {
                     label: 'Chapter 3',
                     value: currentState.storage['chapter3']
                  }
               });

            // this.otherRouter = parentRouter.createChildRouter('test')
            //    .configure({
            //       'a': {
            //          label: 'A',
            //          value: 'AAA'
            //       },
            //       'b': {
            //          label: 'B',
            //          value: 'BBB'
            //       }
            //    });

            this.wizardRouter = this.router.createChildRouter('wiz')
               .configure({
                  'show': {
                     label: 'Ok',
                     value: 'showChapter',
                     isDefault: true,
                     enter: function() {
                        var yyy = 'ooo';
                     }
                  },
                  'edit': {
                     label: 'Edit',
                     value: 'editChapter',
                     enter: function() {
                        var yyy = 'ooo';
                     }
                  }
               });

            // Now that the router for this view exist, synchronise it with the URL
            oj.Router.sync().then(
               function() {
                  var color = viewModel.router.retrieve();
                  if (color) {
                     $('#chapter').css('background', color);
                  }
               },
               function(error) {
                  oj.Logger.error('Error during refresh: ' + error.message);
               }
            );
         },

         selectHandler: function(event, ui) {
            if ('menu' === event.target.id && event.originalEvent) {
               // Invoke go() with the selected item.
               viewModel.router.go(ui.key);
            }
         },

         changeColor: function() {
            try {
               var color = '#99CCFF';
               viewModel.router.store(color);
               $('#chapter').css('background', color);
            }
            catch (error) {
               oj.Logger.error('Error while storing data: ' + error.message);
            }
         },

         // On exit save the changes in the parent router storage.
         canExit: function() {
            var currentState = viewModel.router.parent.currentState();
            var data = {};
            for (var i = 0; i < viewModel.router.states.length; i++) {
               var state = viewModel.router.states[i];
               data[state.id] = state.value;
            }
            // Store changes in parent current state object
            viewModel.router.parent.currentState().storage = data;

            return true;
         },

         handleActivated: function(params) {
            var test = params;
         },

         handleAttached: function(params) {
            var test = params;
         },

         handleBindingsApplied: function(params) {
            var test = params;
         },

         handleDeactivated: function(params) {
            var test = params;
         },

         handleDetached: function(params) {
            var test = params;
         },

         dispose: function() {
            this.router.dispose();
            this.router = null;
            this.wizardRouter = null;
         }
      };

   return viewModel;
});