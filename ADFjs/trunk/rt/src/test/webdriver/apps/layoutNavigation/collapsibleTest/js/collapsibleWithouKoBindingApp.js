require(['ojs/ojcore', 'knockout', 'jquery','ojs/ojbutton', 'ojs/ojknockout', 'ojs/ojcollapsible'],
function(oj, ko, $)
{   
    function CollapsibleModel() {
                var self = this;

            };

              $(document).ready(function(){
                  
                 $("#collapsiblePageWithoutKO").ojCollapsible({
                       id: "collapsiblePageWithoutKO",  
                       expandArea:"header",
                       expanded:false,
                       expandOn:"mouseover"
                     
             })
                 
               
                console.log(new CollapsibleModel());
                   ko.applyBindings(new CollapsibleModel(), document.getElementById('wrapperDiv'));
                }
              );
        });  
        
        