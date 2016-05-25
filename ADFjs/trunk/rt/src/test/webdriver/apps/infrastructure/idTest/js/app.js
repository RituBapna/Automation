/**
 * A top-level require call executed by the Application.
 * Although 'ojcore' and 'knockout' would be loaded in any case (they are specified as dependencies
 * by the modules themselves), we are listing them explicitly to get the references to the 'oj' and 'ko'
 * objects in the callback
 */
require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojknockout-validation','ojs/ojcomponents','ojs/ojcomponentcore', 'app'],
        function(oj, ko, $) // this callback gets executed when all required modules are loaded
        {           
            oj.Test.ready = true;
            
            var element = $( "#spin" ).val( 70 ).ojInputNumber({
                                max: 100,
                                step: 10
                                });
        }
);




