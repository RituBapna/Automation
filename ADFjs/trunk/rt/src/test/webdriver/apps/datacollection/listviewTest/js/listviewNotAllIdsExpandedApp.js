require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'promise', 'ojs/ojlistview'],
function(oj, ko, $)
{     
    $(document).ready(
        function() 
        {
            ko.applyBindings({itemOnly: function(context)
                {
                    return context['leaf'];
                }
            }, document.getElementById('listview'));
        }
    );
});	
