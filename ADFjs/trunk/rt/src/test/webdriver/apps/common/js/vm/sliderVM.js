define(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojslider', 'ojs/ojselectcombobox','ojs/ojbutton'],
        function(oj, ko, $)
        {
            function SliderModel()
            {
                var self = this;
                self.max = ko.observable(200);
                self.min = ko.observable(0);
                self.currentValue = ko.observable(100);
                self.currentValueArray = ko.observableArray([100, 400]);
                self.step = ko.observable(10);
                self.disabledValue = ko.observable(false);
                self.disabledOptions = ko.observableArray([
                    {id: 'true', value: 'true', label: 'true'},
                    {id: 'false', value: 'false', label: 'false'}
                ]);
                self.disabledUpdate = function(event, data) {
                    if(data.value){
                    if (data.value === "false" || data.value[0] === false)
                    {
                        self.disabledValue(false);
                    }
                    else {
                        self.disabledValue(true);
                    }
                    //return true;
                };
            };
            self.orientationValue = ko.observable('horizontal');
                self.orientationOptions = ko.observableArray([
                    {id: 'vertical', value: 'vertical', label: 'vertical'},
                    {id: 'horizontal', value: 'horizontal', label: 'horizontal'}
                ]);
                self.orientationUpdate = function(event, data) {
                    //alert();
                  if(data.value){
                    if (data.value === "vertical")
                    
                        self.orientationValue('vertical');
                   else {
                        self.orientationValue('horizontal');
                    }
                }
                    //  alert('setting data  '+self.multipleOption);
                    return true;
                };
            self.setDisplayOptions=function(){
                $("#inputslider-id").ojSlider("option", "displayOptions", {
                    'converterHint': 'notewindow',
                    'validatorHint': 'notewindow',
                    'title' : 'notewindow'
                  });
                  var definitionText = $( "#inputslider-id" ).ojSlider( "option", "displayOptions" );
                  //console.log(definitionText);
            };
            self.showHelp=function(){
                $("#inputslider-id").ojSlider("option", "help", {
                    'definition': 'Click for Help',
                    'source': 'http://www.google.com'
                  });
                  var definitionText = $( "#inputslider-id" ).ojSlider( "option", "help" );
                  console.log(definitionText);
            };
            /*self.buttonClick = function(data, event){
                $("#inputslider-id").ojSlider("option", "value", 0);
                 return true;
            };*/
             self.buttonClick = function(slider_currentValue,data, event){

                 $("#inputslider-id").ojSlider("option", "value", parseInt(slider_currentValue));
                 self.currentValue(parseInt(slider_currentValue));
                 return true;
            };
            self.valueChangeHandler=function(data, event){
                //look for the id"div#sliderOrientationOutputText"  in output.html page
                $("div#sliderOrientationOutputText").html("Orientation::" +self.orientationValue());
            };
            
            }
            return SliderModel;
        });