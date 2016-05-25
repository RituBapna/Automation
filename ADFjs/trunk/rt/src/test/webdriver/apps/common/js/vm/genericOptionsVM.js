define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'ojs/ojknockout', 'ojs/ojcomponents'],
function(oj, ko, $, bku)
{
    function GenericOptionsViewModel() {
    var self = this;
    var bk = new bku();
                           
    self.langDir = ko.observable("ltr");
    self.htmlLangDir = ko.observable("ltr");
    self.dirValues = [ "ltr", "rtl"];
    self.langValues = [ "en", "ar"];
    self.lang = ko.observable("en");
    self.css = ko.observable("alta");
    self.noTag = ko.observable(false);
    self.minCss = ko.observable(false);
    self.CssPath = ko.observable('../../common/css/libs/oj/v@jetversion@/alta/oj-alta.css');            
    self.cssNative = ko.observable("");

    self.toggleDir = function() {
        bk.cycle("dir",self.dirValues,self.langDir);
        bk.msgLog("dir = " + self.langDir());
        }
        
    self.toggleHtmlDir = function() {
        bk.cycle("dir",self.dirValues,self.htmlLangDir);
        bk.msgLog("html dir = " + self.htmlLangDir());
        $( "html" ).attr( "dir", self.htmlLangDir());        
        }

    self.toggleLang = function() {
        bk.cycle("lang",self.langValues,self.lang);
        bk.msgLog("html lang = " + self.lang());
        $( "html" ).attr( "lang", self.lang());        
        }

    self.toggleCss = function() {
        bk.cycle("css",self.cssValues,self.css);
        bk.msgLog("css = " + self.css());
        self.setCss();
        }

    self.toggleCssNative = function() {
        bk.cycle("cssNative",self.cssNativeValues,self.cssNative);
        bk.msgLog("cssNative = " + self.cssNative());
        self.setCss();
        }


    self.toggleNoTagCss = function() {
        bk.cycle("noTag",self.boolValues,self.noTag);
        bk.msgLog("noTag = " + self.noTag());
        self.setCss();
    }

    self.toggleMinCss = function() {
        bk.cycle("MinCss",self.boolValues,self.minCss);
        bk.msgLog("MinCss = " + self.minCss());
        self.setCss();        
    }
    
    self.setCss = function() {
        self.constructCssPath();
        bk.msgLog("Setting Css to " + self.CssPath());
        $("#ojcss").attr('href',self.CssPath());
    }

    self.constructCssPath = function() {
        /* To avoid lots of if-thens, just construct string
            self.CssPath('css/libs/oj/v@jetversion@/alta/oj-alta.css');        
            self.CssPath('css/libs/oj/v@jetversion@/alta/oj-alta-min.css');            
            self.CssPath('css/libs/oj/v@jetversion@/alta/oj-alta-notag.css');            
            self.CssPath('css/libs/oj/v@jetversion@/alta/oj-alta-notag-min.css');                        
        */
        self.CssPathPrefix = '../../common/css/libs/oj/v@jetversion@/';
        self.CssPath1 = self.CssPathPrefix + self.css() + self.cssNative() + '/oj-' + self.css();
        if (self.noTag()) {
            self.CssPath1 = self.CssPath1 + '-notag';
        }
        if (self.minCss()) {
            self.CssPath1 = self.CssPath1 + '-min';
        }
        self.CssPath1 = self.CssPath1 + '.css';
        self.CssPath(self.CssPath1);
                
        return;
    }
        
    self.isDisabled = ko.observable(false);
    
    self.disabled = ko.observable(false);
    self.toggleDisabled = function() {
        bk.msgLog("disabled = " + self.disabled());        
        bk.cycle("disabled",self.boolValues,self.disabled);        
    }
    
    self.clearLog = function() {
        bk.clearLog();
    }
    
    // perhaps useful?
    self.enableValues = ["enabled","disabled"];
    self.boolValues = [true, false];
    self.styleValues = ['','color : red','color : yellow'];
    self.cssValues = ['alta'];
    self.cssNativeValues = ['','-ios','-android','-windows'];
        
    oj.Logger.info("GenericOptionsViewModel created");
}

        
    
    return GenericOptionsViewModel;
});