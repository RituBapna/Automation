/**
 * Example of Require.js boostrap javascript
 */

// Workaround for Knockout not initializing the global "ko" variable when it detects Require.js
define('knockout.global', ['knockout'], function (kno) {
    window.ko = kno;// Initialize a global 'ko' variable
    return kno;
});


requirejs.config( {
  config :  {
        ojL10n :  {
            merge :  {
                'ojtranslations/nls/ojtranslations' : 'custombundle/nls/bundle'
            }
        }
    },
    // Path mappings for the logical module names
    paths :  {
    'knockout': 'libs/knockout/knockout-3.4.0',
    'jqueryui': 'libs/jquery/jquery-ui-1.11.4.custom',		        
    'jquery': 'libs/jquery/jquery-2.2.3.min',
    'jqueryui-amd': 'libs/jquery/jqueryui-amd-1.11.4',
    'ojs': 'libs/oj/v@jetversion@/debug',
    'ojL10n': 'libs/oj/v@jetversion@/ojL10n',
    'ojtranslations': 'libs/oj/v@jetversion@/resources',
    'hammerjs': 'libs/hammer/hammer-2.0.4.min',
    'ojdnd': 'libs/dnd-polyfill/dnd-polyfill-1.0.0.min',
    'app': currentTestApp,
    'custombundle' : 'overridebundle',
    'promise': 'libs/es6-promise/es6-promise.min',
    'buttonVM' : 'vm/buttonVM',    
    'simpleMenuVM' : 'vm/simpleMenuVM',
    'sortButtonVM' : 'vm/sortButtonVM',
    'bkUtils' : 'bkUtils',
    'buttonsetVM' : 'vm/buttonsetVM',
    'genericOptionsVM' : 'vm/genericOptionsVM',
    'inputNumberVM' : 'vm/inputNumberVM',
    'inputBaseVM' : 'vm/inputBaseVM',
    'inputTextVM' : 'vm/inputTextVM',    
    'checkboxsetVM' : 'vm/checkboxsetVM',    
    'radiosetVM' : 'vm/radiosetVM',    
    'selectVM' : 'vm/selectVM',    
    'comboboxVM' : 'vm/comboboxVM',    
    'inputSearchVM' : 'vm/inputSearchVM',    
    'inputDateVM' : 'vm/inputDateVM',    
    'inputDateTimeVM' : 'vm/inputDateTimeVM',    
    'inputTimeVM' : 'vm/inputTimeVM',    
    'textAreaVM' : 'vm/textAreaVM',    
    'inputPasswordVM' : 'vm/inputPasswordVM',
    'staticDataVM' : 'vm/staticDataVM',
    'dataVM' : 'vm/dataVM',
    'tableBaseVM' : 'vm/tableBaseVM',
    'tablePsrVM' : 'vm/tablePsrVM',
    'mockjax' : 'rest/jquery.mockjax',
    'mockrest' : 'rest/MockRESTServer',
    'mockpagingrest' : 'rest/MockPagingRESTServer',
    'menuVM' : 'vm/menuVM',
    'compBaseVM' : 'vm/compBaseVM',
    'buttonBaseVM' : 'vm/buttonBaseVM',
    'switchVM' : 'vm/switchVM',
    'filmstripVM':'vm/filmstripVM',
    'listviewVM':'vm/ojlistviewVM',
    'listviewDnDVM':'vm/listviewDnDVM',
    'sliderVM': 'vm/sliderVM'
        
        
    },
    // Shim configurations for modules that do not expose AMD
    shim :  {
        'jquery' :  {
            exports : ['jQuery', '$']
        },
'app' :  {
            deps: ['ojs/ojmodel', 'ojs/ojcore', 'ojs/ojknockout', 'ojs/ojknockout-validation', 'ojs/ojknockout-model', 'ojs/ojcomponents', , 'ojs/ojselectcombobox',  'ojs/ojnavigationlist', 'ojs/ojdatetimepicker', 'ojs/ojvalidation', 'ojs/ojcollectiontreedatasource','ojs/ojarraytabledatasource', 'ojs/ojpagingtabledatasource', 'ojs/ojcollectiontabledatasource', 'ojs/ojflattenedtreetabledatasource', 'ojs/ojflattenedtreedatagriddatasource', 'ojs/ojjsontreedatasource']
        },
'brand_bar' :  {
            deps : ['jquery']
        },
'globalize' :  {
            deps : ['jquery'], exports : 'Globalize'
        },
'globalize-en-US' :  {
            deps : ['globalize']
        }
    },
    map :  {
        '*' :  {
            'knockout' : 'knockout.global'
        },
        // All modules referencing 'knockout' will be loading 'knockout.global'
'knockout.global' :  {
            'knockout' : 'knockout'
        }
        // 'knockout.global' itself will be referencing the original 'knockout'
    }
});

/**
 * A top-level require call executed by the Application.
 * Although 'ojcore' and 'knockout' would be loaded in any case (they are specified as dependencies
 * by the modules themselves), we are listing them explicitly to get the references to the 'oj' and 'ko'
 * objects in the callback
 */
require(['ojs/ojcore', 'knockout', 'jquery', 'app'], function (oj, ko)// this callback gets executed when all required modules are loaded
{
/*
    // global nav icon buttons
    $("#buttonNotifications").button( {
        icons :  {
            primary : "oj-icon-projects"
        },
        text : false
    });
    $("#buttonSearch").button( {
        icons :  {
            primary : "oj-icon-search"
        },
        text : false
    });

    // global nav sign in dropdown
    $("#buttonSignIn").button( {
        icons :  {
            secondary : "oj-icon-caret-down"
        }
    });
    $("#ulSignInMenu").hide().menu();
    $("#labelSignIn").click(function () {
        hideOpenMenus($(this).closest(".oj-dropdown-container").find(".oj-dropdown-menu"));
        toggleDropdown($(this).closest(".oj-dropdown-container"), "right");// todo: menu placements should be determined at runtime
        return false;
    });

    // Now that the menu is populated, show the containing div
    $('#ulGlobalNav').show();
    */
});
