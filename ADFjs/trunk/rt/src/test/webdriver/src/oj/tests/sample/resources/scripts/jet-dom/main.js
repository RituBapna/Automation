/*
 *  run requireJS configuration. Specifies where
 */
 // the base url is relative to the directory from where the test was launched
 // in our case is trunk/src/build/scripts 
 // to run the test from here:
 // make run-webdriver-tests browser=firefox CLASS=os.tests.sample.TestChartSVGGenerator
require.config(
{  
       enforceDefine: true,
    baseUrl: 'built/code', 
  paths : 
  {
    'jquery' : 'js/libs/jquery/jquery-2.2.3.min', 
    'jqueryui' : 'js/libs/jquery/jquery-ui-1.11.4.custom', 
    'jqueryui-amd' : 'js/libs/jquery/jqueryui-amd-1.11.4.min', 
    //'promise' : 'js/libs/es6-promise/es6-promise',
    'ojs' : 'js/libs/oj/v@jetversion@/debug', 
    'ojL10n' : 'js/libs/oj/v@jetversion@/ojL10n', 
    'ojtranslations' : 'js/libs/oj/v@jetversion@/resources', 
    'promise' : '../test/webdriver/classes/oj/tests/sample/scripts/jet-dom/es6-promise.min',    
    'jet-dom' : '../test/webdriver/classes/oj/tests/sample/scripts/jet-dom/jet-dom'
  },
  waitSeconds : 100, locale : 'en', shim : 
  {
    'jqueryui' : 
    {
      deps : ['jquery']
    }
  }
});
// load the JET-DOM implementation and setup the "main" window object
var domwindow, document, svg, Promise;
require(['jet-dom', 'promise'], function (dom, es6promise)
{
  domwindow = dom.createWindow();
  document = domwindow.document;
  
  window.addEventListener = domwindow.addEventListener;
  window.getComputedStyle = domwindow.getComputedStyle;
  window.document = document;
  window.location = domwindow.location;
  // clearTimeout was not defined in r.js, therefore polyfill here (empty impl, since we are not using it)
  window.clearTimeout = function(){};
  Promise = es6promise.Promise;
  
});
// now call the JET api to render a chart with a given chartOptions object, 
// width and height attributes (already set in the Java code)
require(['jquery', 'ojs/ojcore', 'ojs/ojchart'], function ($, oj, ochart)
{    
  var sizing = "width:" + chartWidth + "px; height:" + chartHeight + "px;";
  document.getElementById('chart').setAttribute('style', sizing);  
  $('#chart').ojChart(chartOptions);  
  // there will be a public API that we will call here (insted of $('svg') )
  // once the ER21233846 is implemented.
  svg = $('svg').html();
});
