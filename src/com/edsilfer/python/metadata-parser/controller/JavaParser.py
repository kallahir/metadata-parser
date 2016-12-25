from controller.AbstractParser import AbstractParser

class JavaParser (AbstractParser):
    def declareImports(self):
        print("Declaring Java Imports")

    def openClass(self):
        print("Openning Java Class")

    def declareMembers(self):
        print("Declaring Java Members")

    def declareMethods(self):
        print("Declaring Java Methods")

    def closeClass(self):
        print("Closing Java Class")
