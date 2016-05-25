/**
 * Example of Require.js boostrap javascript
 */


// Workaround for Knockout not initializing the global "ko" variable when it detects Require.js
define('knockout.global', ['knockout'], function(kno)
{
    window.ko = kno; // Initialize a global 'ko' variable
    return kno;
});



requirejs.config({
    // Path mappings for the logical module names
    paths: {
        'knockout' : 'libs/knockout/knockout-3.4.0', 
        'jquery' : 'libs/jquery/jquery-2.2.3.min', 
        'jqueryui-amd': 'libs/jquery/jqueryui-amd-1.11.4',
        'ojcore' : 'libs/oj/ojcore', 
        'ojmodel' : 'libs/oj/ojmodel', 
        'ojknockout' : 'libs/oj/ojknockout', 
        'ojcomponents' : 'libs/oj/ojcomponents', 
        'ojcomponentcore' : 'libs/oj/ojcomponentcore', 
       
        'ojL10n' : 'libs/oj/ojL10n', 
        'ojtranslations' : 'libs/oj/resources', 
          'ojvalidation':'libs/oj/ojvalidation',
        'app': 'app',
        'brand_bar': 'brand_bar'
    },
    // Shim configurations for modules that do not expose AMD
    shim: {
        'jquery': {
            exports: ['jQuery', '$']
        },
        'app': {
            deps: ['ojmodel', 'ojcore', 'ojknockout']
        },
        'brand_bar': {
            deps: ['jquery']
        }
    },
    map: {
        '*': {'knockout': 'knockout.global'}, // All modules referencing 'knockout' will be loading 'knockout.global'
        'knockout.global': {'knockout': 'knockout'} // 'knockout.global' itself will be referencing the original 'knockout'
    }
});


/**
 * A top-level require call executed by the Application.
 * Although 'ojcore' and 'knockout' would be loaded in any case (they are specified as dependencies
 * by the modules themselves), we are listing them explicitly to get the references to the 'oj' and 'ko'
 * objects in the callback
 */
require(['app', 'brand_bar'],
        function() // this callback gets executed when all required modules are loaded
        {

            // global nav icon buttons
            $("#buttonNotifications").button({icons: {primary: "oj-icon-projects"}, text: false});
            $("#buttonSearch").button({icons: {primary: "oj-icon-search"}, text: false});

            // global nav sign in dropdown
            $("#buttonSignIn").button({icons: {secondary: "oj-icon-caret-down"}});
            $("#ulSignInMenu").hide().menu();
            $("#labelSignIn").click(function() {
                hideOpenMenus($(this).closest(".oj-dropdown-container").find(".oj-dropdown-menu"));
                toggleDropdown($(this).closest(".oj-dropdown-container"), "right"); // todo: menu placements should be determined at runtime
                return false;
            });
           
            
            // Now that the menu is populated, show the containing div
            $('#ulGlobalNav').show();
            
            
        }
);


