requirejs.config({
   // Need to set baseUrl or nested view won't work because module location relative to current url.
   // Change to the correct baseUrl when deployed to site like: http://host/myApp
   // baseUrl: window.location.href.split('#')[0].substring(0, window.location.href.split('#')[0].lastIndexOf('/')) + '/js',
   baseUrl: 'http://localhost:7101/router/Sample-ojModule/js',

   // Path mappings for the logical module names
   paths: {
      'knockout': '../../../common/js/libs/knockout/knockout-3.4.0',
      'jquery': '../../../common/js/libs/jquery/jquery-2.2.3',
      'jqueryui-amd': '../../../common/js/libs/jquery/jqueryui-amd-1.11.4.min',
      'promise': '../../../common/js/libs/es6-promise/es6-promise.min',
      'hammerjs': '../../../common/js/libs/hammer/hammer-2.0.4.min',
      'ojdnd': '../../../common/js/libs/dnd-polyfill/dnd-polyfill-1.0.0.min',
      'ojs': '../../../common/js/libs/oj/v@jetversion@/debug',
      'ojL10n': '../../../common/js/libs/oj/v@jetversion@/ojL10n',
      'ojtranslations': '../../../common/js/libs/oj/v@jetversion@/resources',
      'signals': '../../../common/js/libs/js-signals/signals',
      'text': '../../../common/js/libs/require/text'
   },
   // Shim configurations for modules that do not expose AMD
   shim: {
      'jquery': {
         exports: ['jQuery', '$']
      },
      'jqueryui': {
         deps: ['jquery']
      }
   },
   // This section configures the i18n plugin. It is merging the Oracle JET built-in translation
   // resources with a custom translation file.
   // Any resource file added, must be placed under a directory named "nls". You can use a path mapping or you can define
   // a path that is relative to the location of this main.js file.
   config: {
      ojL10n: {
         merge: {
            //'ojtranslations/nls/ojtranslations': 'resources/nls/menu'
         }
      }
   }
});


/**
 * A top-level require call executed by the Application.
 * Although 'ojcore' and 'knockout' would be loaded in any case (they are specified as dependencies
 * by the modules themselves), we are listing them explicitly to get the references to the 'oj' and 'ko'
 * objects in the callback
 */
require(['ojs/ojcore',
      'knockout',
      'jquery',
      'ojs/ojknockout',
      'ojs/ojmodule',
      'ojs/ojrouter',
      'text'
   ], function(oj, ko, $) // this callback gets executed when all required modules are loaded
   {
      oj.Assert.forceDebug();
      // Set the log level
      oj.Logger.option('level',  oj.Logger.LEVEL_INFO);

      oj.Router.defaults['baseUrl'] = document.getElementsByTagName('base')[0].getAttribute('href');

      var router = oj.Router.rootInstance;

      router.configure({
         'home': {
            label: 'Home',
            value: 'homeContent',
            isDefault: true
         },
         'book': {
            label: 'Book',
            value: 'bookContent'
         },
         'tables': {
            label: 'Tables',
            value: 'tablesContent'
         }
      });

      router.moduleConfig.lifecycleListener.activated = function(params) {
         var parentRouter = 'hhh';
      };

      var viewModel = {
         router: router
      };

      // oj.Router.defaults['urlAdapter'] = new oj.Router.urlParamAdapter();

      oj.Router.sync().then(
         function() {
            ko.applyBindings(viewModel);
            $('#globalBody').show();
         },
         function(error) {
            oj.Logger.error('Error when starting router: ' + error.message);
         });
   });