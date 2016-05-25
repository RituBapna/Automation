define(['knockout', 'jquery', 'ojs/ojcore'], function(ko, $, oj)
{
  oj.Logger.option("level", oj.Logger.LEVEL_INFO);

  function AppViewModel() {
    var self = this;
    self.currentValue = ko.observable(1);
  }

  return AppViewModel;
});

