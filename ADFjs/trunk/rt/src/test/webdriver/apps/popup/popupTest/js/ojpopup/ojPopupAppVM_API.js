define(['knockout', 'jquery', 'ojs/ojcore'], function(ko, $, oj) {
  oj.Logger.option("level", oj.Logger.LEVEL_INFO);
  var popupAppViewModel = function() {
    self = this;
    self.autoDismissValueList = ko.observableArray([{value: 'focusLoss', label: 'Focus Loss'}, {value: 'none', label: 'None'}]);
    self.autoDismissValue = ko.observable(['focusLoss']);
    self.autoDismiss = ko.computed(function() {
      return self.autoDismissValue()[0];
    });
    self.chromeValueList = ko.observableArray([{value: 'default', label: 'Default'}, {value: 'none', label: 'None'}]);
    self.chromeValue = ko.observable(['default']);
    self.chrome = ko.computed(function() {
      return self.chromeValue()[0];
    });
    self.initialFocusValueList = ko.observableArray([{value: 'firstFocusable', label: 'First Focusable'}, {value: 'none', label: 'None'}]);
    self.initialFocusValue = ko.observable(['firstFocusable']);
    self.initialFocus = ko.computed(function() {
      return self.initialFocusValue()[0];
    });
    self.tailValueList = ko.observableArray([{value: 'simple', label: 'Simple'}, {value: 'none', label: 'None'}]);
    self.tailValue = ko.observable(['none']);
    self.tail = ko.computed(function() {
      return self.tailValue()[0];
    });
    self.myValue = ko.observable('left top');
    self.atValue = ko.observable('left-400 bottom');
    self.collisionValue = ko.observable('flip');

    self.positionValue = ko.computed(function()
    {
      return {'my': self.myValue(), 'at': self.atValue(), 'collision': self.collisionValue()}
    }, self);


    self.testGetOptions = function(popupdiv, popupspan, d, e)
    {

      //  $("#"+id).ojPopup("open", 'btn1', {'my':'left top', 'at': 'right+50 bottom', 'collision': 'flip'});
      var popupdivoptions = $("#" + popupdiv).ojPopup("option");
      var popupspanoptions = $("#" + popupspan).ojPopup("option");
      //get popupdiv options one by one:
      var autodismiss = $("#" + popupdiv).ojPopup("option", "autoDismiss");
      var chrome = $("#" + popupdiv).ojPopup("option", "chrome");
      var initialFocus = $("#" + popupdiv).ojPopup("option", "initialFocus");
      var position = $("#" + popupdiv).ojPopup("option", "position");
      var tail = $("#" + popupdiv).ojPopup("option", "tail");

      //get popupspan options one by one:
      var autodismiss1 = $("#" + popupspan).ojPopup("option", "autoDismiss");
      var chrome1 = $("#" + popupspan).ojPopup("option", "chrome");
      var initialFocus1 = $("#" + popupspan).ojPopup("option", "initialFocus");
      var position1 = $("#" + popupspan).ojPopup("option", "position");
      var tail1 = $("#" + popupspan).ojPopup("option", "tail");

    }

    self.testSetOptions = function(popupdiv, popupspan, d, e)
    {

      //  $("#"+id).ojPopup("open", 'btn1', {'my':'left top', 'at': 'right+50 bottom', 'collision': 'flip'});

      //set popupdiv options one by one:
      $("#" + popupdiv).ojPopup("option", "autoDismiss", 'focusLoss');
      $("#" + popupdiv).ojPopup("option", "chrome", 'none');
      $("#" + popupdiv).ojPopup("option", "initialFocus", 'none');
      $("#" + popupdiv).ojPopup("option", "position", {'my': 'left top', 'at': 'right+50 bottom', 'collision': 'flip'});
      $("#" + popupdiv).ojPopup("option", "tail", 'simple');

      //set popupspan options one by one:
      $("#" + popupspan).ojPopup("option", "autoDismiss", 'focusLoss');
      $("#" + popupspan).ojPopup("option", "chrome", 'none');
      $("#" + popupspan).ojPopup("option", "initialFocus", 'none');
      $("#" + popupspan).ojPopup("option", "position", {'my': 'left top', 'at': 'right+50 bottom', 'collision': 'flip'});
      $("#" + popupspan).ojPopup("option", "tail", 'simple');

      //get all the options value after set is called
      var popupdivoptions = $("#" + popupdiv).ojPopup("option");
      var popupspanoptions = $("#" + popupspan).ojPopup("option");

    }

    self.toggleDivAsPopup = function(id, d, e)
    {

      //  $("#"+id).ojPopup("open", 'btn1', {'my':'left top', 'at': 'right+50 bottom', 'collision': 'flip'});
      if ($("#" + id).ojPopup("isOpen"))
        $("#" + id).ojPopup("close");
      else
        $("#" + id).ojPopup("open");
    }

    self.toggleSpanAsPopup = function(id, d, e)
    {

      //  $("#"+id).ojPopup("open", 'btn1', {'my':'left top', 'at': 'right+50 bottom', 'collision': 'flip'});
      if ($("#" + id).ojPopup("isOpen"))
        $("#" + id).ojPopup("close");
      else
        $("#" + id).ojPopup("open");
    }

    self.toggleDataGridPopup = function(id, d, e)
    {

      //  $("#"+id).ojPopup("open", 'btn1', {'my':'left top', 'at': 'right+50 bottom', 'collision': 'flip'});
      if ($("#" + id).ojPopup("isOpen"))
        $("#" + id).ojPopup("close");
      else
        $("#" + id).ojPopup("open");
    }

    self.toggleTreePopup = function(id, d, e)
    {

      //  $("#"+id).ojPopup("open", 'btn1', {'my':'left top', 'at': 'right+50 bottom', 'collision': 'flip'});
      if ($("#" + id).ojPopup("isOpen"))
        $("#" + id).ojPopup("close");
      else
        $("#" + id).ojPopup("open");
    }

    //popup
    //

    self.popupBtn1Action = function(userdata, d, e)
    {
      oj.Logger.info("user data : " + userdata);
    }

    self.popupCreateHandler = function(e, d)
    {
      oj.Logger.info("popup create handler called");
    }
    self.popupCreateHandler1 = function(e, d) {
      oj.Logger.info("popup create handler called 11111");
    }

    self.date = ko.observable();
    self.decimal = ko.observable();
    self.datetime = ko.observable();
    self.time = ko.observable();

    self.updateNumberOfMonths = function(e, d)
    {
      $("#date10").ojInputDate("option", "datePicker.numberOfMonths", 2)
      // $("#date10").ojInputDate("refresh");
    }
    // BIRTHDATE
    self.birthdate = ko.observable();
    var cf = oj.Validation.converterFactory("datetime");
    var ecmaBirthDateOptions = {year: 'numeric', month: 'long', day: '2-digit'};
    self.longDateConverter = cf.createConverter(ecmaBirthDateOptions);

    //Radio Button
    self.currentRadioSelection = ko.observable('crust21');
    self.currentRadioSelection1 = ko.observable('crust1');

    //combobox
    //
    self.val = ko.observableArray(["CA"]);
    //
    self.groupData = ko.observableArray([
      {label: "Alaskan/Hawaiian Time Zone",
        children: [
          {value: "AK", label: "Alaska"},
          {value: "HI", label: "Hawaii"}
        ]},
      {label: "Pacific Time Zone",
        children: [
          {value: "CA", label: "California"},
          {value: "NV", label: "Nevada"},
          {value: "OR", label: "Oregon"},
          {value: "WA", label: "Washington"}
        ]}
    ]);

    // select

    self.selectval = ko.observableArray(['SF']);
    self.triLevelGroupData = ko.observableArray([
      {label: "Alaskan/Hawaiian Time Zone",
        children: [
          {value: "AK", label: "Alaska",
            children: [
              {value: "AN", label: "Anchorage"}
            ]},
          {value: "HI", label: "Hawaii",
            children: [
              {value: "HO", label: "Honolulu"},
              {value: "HL", label: "Hilo"}
            ]}
        ]},
      {label: "Pacific Time Zone",
        children: [
          {value: "CA",
            label: "California",
            children: [
              {value: "SF", label: "San Francisco"},
              {value: "LA", label: "Los Angeles"}
            ]},
          {value: "NV", label: "Nevada",
            children: [
              {value: "LV", label: "Las Vegas"}
            ]},
          {value: "OR", label: "Oregon",
            children: [
              {value: "PL", label: "Portland"},
              {value: "BD", label: "Bend"}
            ]},
          {value: "WA", label: "Washington",
            children: [
              {value: "ST", label: "Seattle"},
              {value: "SK", label: "Spokane"}
            ]}
        ]}
    ]);

    ;


//Train
    self.currentStepValue = ko.observable('stp1');
    self.currentStepValue1 = ko.observable('stp1');

    self.currentStepValueText = function(e, d) {

      return ($("#train").ojTrain("getStep", self.currentStepValue())).label;
    };
    self.currentStepValueText1 = function(e, d) {

      return ($("#train1").ojTrain("getStep", self.currentStepValue1())).label;
    };
    self.nextStep = function(e, d) {
      var next = $("#train").ojTrain("nextSelectableStep");
      if (next !== null)
        $("#train").ojTrain("option", "selected", next);
      //  self.currentStepValue(next);
    };

    self.nextStep1 = function(e, d) {
      var next = $("#train1").ojTrain("nextSelectableStep");


      if (next !== null)
        $("#train1").ojTrain("option", "selected", next);
      // self.currentStepValue1(next);
    };
    self.previousStep = function() {
      var prev = $("#train").ojTrain("previousSelectableStep");
      if (prev !== null)
        $("#train").ojTrain("option", "selected", prev);
      //  self.currentStepValue(prev);
    };

    self.previousStep1 = function() {
      var prev = $("#train1").ojTrain("previousSelectableStep");
      if (prev !== null)
        $("#train1").ojTrain("option", "selected", prev);
      //  self.currentStepValue1(prev);
    };


//menu
    self.menuItemSelect = function(e, d) {
      oj.Logger.info("menu Item selected :  " + d.item[0].id);
    }
//inputtext
    self.decimal = ko.observable();
    // date
    self.dateval = ko.observable(oj.IntlConverterUtils.dateToLocalIso(new Date()));
    //text area
    self.textareaval = ko.observable("some initial value for the component");
    //password
    self.pwdval = ko.observable("Hello there!");
    self.sayHeyo = function()
    {
      oj.Logger.info("Say Heyyo!")
    }

    //menu

    self.selectedMenuItem = ko.observable("(None selected yet)");
    self.menuLauncher = ko.observable("(Not launched yet)");

    self.menuItemSelect = function(event, ui) {
      self.selectedMenuItem(ui.item.children("a").text());
    };

    self.menuOpen = function(event, ui) {
      self.menuLauncher(ui.openOptions.launcher.attr("id"));
    };

    //Table in dialog 2

    var deptArray = [{DepartmentId: 1001, DepartmentName: 'ADFPM 1001 neverending', LocationId: 200, ManagerId: 300},
      {DepartmentId: 556, DepartmentName: 'BB', LocationId: 200, ManagerId: 300},
      {DepartmentId: 10, DepartmentName: 'Administration', LocationId: 200, ManagerId: 300},
      {DepartmentId: 20, DepartmentName: 'Marketing', LocationId: 200, ManagerId: 300},
      {DepartmentId: 30, DepartmentName: 'Purchasing', LocationId: 200, ManagerId: 300},
      {DepartmentId: 40, DepartmentName: 'Human Resources1', LocationId: 200, ManagerId: 300},
      {DepartmentId: 50, DepartmentName: 'Administration2', LocationId: 200, ManagerId: 300},
      {DepartmentId: 60, DepartmentName: 'Marketing3', LocationId: 200, ManagerId: 300},
      {DepartmentId: 70, DepartmentName: 'Purchasing4', LocationId: 200, ManagerId: 300},
      {DepartmentId: 80, DepartmentName: 'Human Resources5', LocationId: 200, ManagerId: 300},
      {DepartmentId: 90, DepartmentName: 'Human Resources11', LocationId: 200, ManagerId: 300},
      {DepartmentId: 100, DepartmentName: 'Administration12', LocationId: 200, ManagerId: 300},
      {DepartmentId: 110, DepartmentName: 'Marketing13', LocationId: 200, ManagerId: 300},
      {DepartmentId: 120, DepartmentName: 'Purchasing14', LocationId: 200, ManagerId: 300},
      {DepartmentId: 130, DepartmentName: 'Human Resources15', LocationId: 200, ManagerId: 300}];
    self.datasource = new oj.ArrayTableDataSource(deptArray, {idAttribute: 'DepartmentId'});
    self.pagingDatasource = new oj.PagingTableDataSource(self.datasource);

    self.dialogVisible = ko.observable(false);
    self.beforeManagePopupOpen = function(event, ui)
    {
      self.dialogVisible(true);
    };

    /// dataGrid

    var grades =
    [
      {
        "grade": "A",
        "2008": {"male": 3447.25, "female": 2883.1},
        "2009": {"male": 11070.41, "female": 3017.5},
        "2010": {"male": 2968.3, "female": 9802.79}
      },
      {
        "grade": "B",
        "2008": {"male": 46113, "female": 32081.75},
        "2009": {"male": 48450.5, "female": 30051.25},
        "2010": {"male": 59683.51, "female": 37526.65}
      },
      {
        "grade": "C",
        "2008": {"male": 65349.75, "female": 26275},
        "2009": {"male": 71231 / 76, "female": 32045.5},
        "2010": {"male": 108397.29, "female": 35432}
      },
      {
        "grade": "D",
        "2008": {"male": 47336.5, "female": 12261.5},
        "2009": {"male": 50323, "female": 8858.5},
        "2010": {"male": 68328, "female": 13361}
      },
      {
        "grade": "E",
        "2008": {"male": 18415.5, "female": 5737},
        "2009": {"male": 18390.2, "female": 5642},
        "2010": {"male": 2179, "female": 3988}
      },
      {
        "grade": "F",
        "2008": {"male": 4277, "female": 180},
        "2009": {"male": 3558, "female": 556},
        "2010": {"male": 609, "female": 441}
      }
    ];

    self.datagriddataSource = new oj.ArrayDataGridDataSource(grades, {rowHeader: 'grade'});
    self.datagridVisible = ko.observable(false);
    self.beforeManagePopupOpen4 = function(event, ui)
    {
      self.datagridVisible(true);
    };


    /// ojTree

    self.jd = [
      {
        "title": "Home",
        "attr": {"id": "home"}
      },
      {
        "title": "News",
        "attr": {"id": "news"}
      },
      {
        "title": "Blogs",
        "attr": {"id": "blogs"},
        "children": [{"title": "Today",
            "attr": {"id": "today"}
          },
          {"title": "Yesterday",
            "attr": {"id": "yesterday"}
          },
          {"title": "2 Days Back",
            "attr": {"id": "2daysback"}
          },
          {"title": "Archive",
            "attr": {"id": "archive"}
          }
        ]
      },
      {
        "title": "Links",
        "attr": {"id": "links"},
        "children": [{"title": "Oracle",
            "attr": {"id": "oracle"}
          },
          {"title": "IBM",
            "attr": {"id": "ibm"}
          },
          {"title": "Microsoft",
            "attr": {"id": "ms"},
            "children": [{"title": "USA",
                "attr": {"id": "msusa"},
                "children": [{"title": "North",
                    "attr": {"id": "msusanorth"}
                  },
                  {"title": "South",
                    "attr": {"id": "msusasouth"}
                  },
                  {"title": "East",
                    "attr": {"id": "msusaeast"}
                  },
                  {"title": "West",
                    "attr": {"id": "msusawest"}
                  }
                ]
              },
              {"title": "Europe",
                "attr": {"id": "msuerope"}
              },
              {"title": "Asia",
                "attr": {"id": "msasia"},
                "children": [{"title": "Japan",
                    "attr": {"id": "asiajap"}
                  },
                  {"title": "China",
                    "attr": {"id": "asiachina"}
                  },
                  {"title": "India",
                    "attr": {"id": "asiaindia"}
                  }
                ]
              }
            ]
          }
        ]
      },
      {
        "title": "Sponsors",
        "attr": {"id": "sponsors"}
      },
      {
        "title": "Corporate",
        "attr": {"id": "corporate"}
      },
      {
        "title": "References",
        "attr": {"id": "references"},
        "children": [{"title": "All",
            "attr": {"id": "refsall"}
          },
          {"title": "USA",
            "attr": {"id": "refsusa"}
          },
          {"title": "Europe",
            "attr": {"id": "refseurope"}
          },
          {"title": "Asia",
            "attr": {"id": "refsasia"}
          }
        ]
      },
      {
        "title": "Suppliers",
        "attr": {"id": "sups"},
        "children": [{"title": "Gold Tier",
            "attr": {"id": "supgold"}
          },
          {"title": "Silver Tier",
            "attr": {"id": "supsilver"}
          },
          {"title": "Non-contract",
            "attr": {"id": "supcon"}
          },
          {"title": "Independent",
            "attr": {"id": "supind"}
          }
        ]
      }
    ];
    //Train
    self.currentStepValue = ko.observable('stp1');
    self.stepArray =
    ko.observableArray(
    [{label: 'Step One', id: 'stp1'},
      {label: 'Step Two', id: 'stp2'},
      {label: 'Step Three', id: 'stp3'},
      {label: 'Step Four', id: 'stp4'},
      {label: 'Step Five', id: 'stp5'}]);

    self.currentStepValueText = function() {
      return ($("#train").ojTrain("getStep", trainModel.currentStepValue())).label;
    };
  }


  return popupAppViewModel;
})

