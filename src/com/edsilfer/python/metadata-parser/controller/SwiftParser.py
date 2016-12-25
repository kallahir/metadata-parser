from controller.AbstractParser import AbstractParser

class SwiftParser (AbstractParser):
    def declareImports(self):
        print("Declaring Swift Imports")

    def openClass(self):
        print("Openning Swift Class")

    def declareMembers(self):
        print("Declaring Swift Members")

    def declareMethods(self):
        print("Declaring Swift Methods")

    def closeClass(self):
        print("Closing Swift Class")
