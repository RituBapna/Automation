define(['ojs/ojcore', 'knockout', 'jquery'],
function(oj, ko, $)
{
  function AppViewModel() {
    var self = this;
    self.severityCheckboxButtonSet = [
      {id: 'fatal', label: 'Fatal'},
      {id: 'error', label: 'Error'},
      {id: 'warning', label: 'Warning'},
      {id: 'info', label: 'Info'},
      {id: 'confirmation', label: 'Confirmation'}
    ];

    // for the Buttonset:
    self.types = ko.observableArray([]);

    // for value option
    self.textValue = ko.observable('none');
    self.textAreaValue = ko.observable('none');
    self.radiosetValue = ko.observable('none');
    self.numberValue = ko.observable(0);
    self.selectValue = ko.observableArray(['none']);
    self.comboboxValue = ko.observableArray(['none']);
    self.comboboxMultiValue = ko.observableArray(['none']);
    self.checkboxsetValue = ko.observableArray(['none']);
    self.dateValue = ko.observable(null);
    self.timeValue = ko.observable(null);
    self.datetimeValue = ko.observable(null);

    // for messagesCustom option
    self.appMessages = ko.observableArray();

    // updates all value observables to new value when severity type changes
    self._update = function(newValue)
    {
      // when severity type changes, update value and messagesCustom options
      self._updateValues(newValue);
      // push new values to components first. Updating the value option clears all messages.
      oj.ComponentBinding.deliverChanges();
      // next update custom messages
      self._updateMessages(newValue);
    };

    // updates all value observables to reflect the new value. when severity type changes
    self._updateValues = function(newValue)
    {
      var maxLevel = -1, currLevel, type, index, maxLevelStr = "", values = [];
      // get max level and set that as the value
      if (typeof newValue === "object" && Array.isArray(newValue) && newValue.length > 0)
      {
        $.each(newValue, function(index, type)
        {
          if (type)
          {
            currLevel = oj.Message.getSeverityLevel(type);
          }
          maxLevel = maxLevel < currLevel ? currLevel : maxLevel;

        });
        maxLevelStr = oj.Message.getSeverityType(maxLevel);

        self.textValue(maxLevelStr);
        self.textAreaValue(maxLevelStr);
        self.radiosetValue(maxLevelStr);

        self.numberValue(maxLevel);

        self.checkboxsetValue(newValue);
        self.comboboxValue(newValue);
        self.comboboxMultiValue(newValue);
        self.selectValue(newValue);
      }
    };

    // compute messages observable based on the current value for severityType
    self._updateMessages = function()
    {
      var msgs = [], summary = null, detail = null, severity = null, selectedTypes = [],
      newValue = self.types();

      if (typeof newValue === "object" && Array.isArray(newValue))
      {
        $.each(newValue, function(index, type) {
          switch (type)
          {
            case oj.Message.SEVERITY_TYPE['FATAL']:
              summary = "Fatal Error Summary Text";
              detail = "Fatal Error Detail Text";
              break;

            case oj.Message.SEVERITY_TYPE['ERROR']:
              summary = "Error Summary Text";
              detail = "Error Detail Text";
              break;

            case oj.Message.SEVERITY_TYPE['WARNING']:
              summary = "Warning Summary Text";
              detail = "Warning Detail Text";
              break;

            case oj.Message.SEVERITY_TYPE['INFO']:
              summary = "Info Summary Text";
              detail = "Info Detail Text";
              break;

            case oj.Message.SEVERITY_TYPE['CONFIRMATION']:
              summary = "Confirmation Summary Text";
              detail = "Confirmation Detail Text";
              break;
          }
          ;

          if (summary && detail)
          {
            msgs.push({summary: summary, detail: detail, severity: type});
          }
        });
      }
      self.appMessages(msgs);
    };

    // subscribe to changes to severityType
    self.types.subscribe(self._update);

  }

  return AppViewModel;
});
