System.config({
  defaultJSExtensions: true,
  transpiler: "traceur",
  paths: {
    "github:*": "lib/github/*"
  },

  map: {
    "components/jquery": "github:components/jquery@2.1.4",
    "traceur": "github:jmcriffey/bower-traceur@0.0.92",
    "traceur-runtime": "github:jmcriffey/bower-traceur-runtime@0.0.92",
    "twbs/bootstrap": "github:twbs/bootstrap@3.3.5",
    "github:twbs/bootstrap@3.3.5": {
      "jquery": "github:components/jquery@2.1.4"
    }
  }
});
